package com.agomezmoron.saveImageGallery;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Arrays;
import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PermissionHelper;

import org.json.JSONArray;
import org.json.JSONException;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

/**
 * SaveImageGallery.java
 *
 * Extended Android implementation of the Base64ToGallery for iOS.
 * Inspirated by StefanoMagrassi's code
 * https://github.com/Nexxa/cordova-base64-to-gallery
 *
 * @author Alejandro Gomez <agommor@gmail.com>
 */
public class SaveImageGallery extends CordovaPlugin {

    // Consts
    public static final String EMPTY_STR = "";

    public static final String JPG_FORMAT = "JPG";
    public static final String PNG_FORMAT = "PNG";

    // actions constants
    public static final String SAVE_BASE64_ACTION = "saveImageDataToLibrary";
    public static final String REMOVE_IMAGE_ACTION = "removeImageFromLibrary";

    public static final int WRITE_PERM_REQUEST_CODE = 1;
    private final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    private JSONArray _args;
    private CallbackContext _callback;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if(action.equals(REMOVE_IMAGE_ACTION)) {
            this.removeImage(args, callbackContext);
        }
        else {
            this._args = args;
            this._callback = callbackContext;

            if (PermissionHelper.hasPermission(this, WRITE_EXTERNAL_STORAGE)) {
                Log.d("SaveImageGallery", "Permissions already granted, or Android version is lower than 6");
                saveBase64Image(this._args, this._callback);
            } else {
                Log.d("SaveImageGallery", "Requesting permissions for WRITE_EXTERNAL_STORAGE");
                PermissionHelper.requestPermission(this, WRITE_PERM_REQUEST_CODE, WRITE_EXTERNAL_STORAGE);
            } 
        }

        return true;
    }

    /**
     * It deletes an image from the given path.
     */
    private void removeImage(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String filename = args.optString(0);

        // isEmpty() requires API level 9
        if (filename.equals(EMPTY_STR)) {
            callbackContext.error("Missing filename string");
        }

        File file = new File(filename);
        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception ex) {
                callbackContext.error(ex.getMessage());
            }
        }

        callbackContext.success(filename);

    }

    /**
     * It saves a Base64 String into an image.
     */
    private void saveBase64Image(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String base64 = args.optString(0);
        String filePrefix = args.optString(1);
        boolean mediaScannerEnabled = args.optBoolean(2);
        String format = args.optString(3);
        int quality = args.optInt(4);

        List<String> allowedFormats = Arrays.asList(new String[] { JPG_FORMAT, PNG_FORMAT });

        // isEmpty() requires API level 9
        if (base64.equals(EMPTY_STR)) {
            callbackContext.error("Missing base64 string");
        }

        // isEmpty() requires API level 9
        if (format.equals(EMPTY_STR) || !allowedFormats.contains(format.toUpperCase())) {
            format = JPG_FORMAT;
        }

        if (quality <= 0) {
            quality = 100;
        }

        // Create the bitmap from the base64 string
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        if (bmp == null) {
            callbackContext.error("The image could not be decoded");

        } else {

            // Save the image
            File imageFile = savePhoto(bmp, filePrefix, format, quality);

            if (imageFile == null) {
                callbackContext.error("Error while saving image");
            }

            // Update image gallery
            if (mediaScannerEnabled) {
                scanPhoto(imageFile);
            }

            String path = imageFile.toString();

            if (!path.startsWith("file://")) {
                path = "file://" + path;
            }

            callbackContext.success(path);
        }
    }

    /**
     * Private method to save a {@link Bitmap} into the photo library/temp folder with a format, a prefix and with the given quality.
     */
    private File savePhoto(Bitmap bmp, String prefix, String format, int quality) {
        File retVal = null;

        try {
            String deviceVersion = Build.VERSION.RELEASE;
            Calendar c = Calendar.getInstance();
            String date = EMPTY_STR + c.get(Calendar.YEAR) + c.get(Calendar.MONTH) + c.get(Calendar.DAY_OF_MONTH)
                    + c.get(Calendar.HOUR_OF_DAY) + c.get(Calendar.MINUTE) + c.get(Calendar.SECOND);

            int check = deviceVersion.compareTo("2.3.3");

            File folder;

            /*
             * File path = Environment.getExternalStoragePublicDirectory(
             * Environment.DIRECTORY_PICTURES ); //this throws error in Android
             * 2.2
             */
            if (check >= 1) {
                folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                if (!folder.exists()) {
                    folder.mkdirs();
                }

            } else {
                folder = Environment.getExternalStorageDirectory();
            }

            // building the filename
            String fileName = prefix + date;
            Bitmap.CompressFormat compressFormat = null;
            // switch for String is not valid for java < 1.6, so we avoid it
            if (format.equalsIgnoreCase(JPG_FORMAT)) {
                fileName += ".jpeg";
                compressFormat = Bitmap.CompressFormat.JPEG;
            } else if (format.equalsIgnoreCase(PNG_FORMAT)) {
                fileName += ".png";
                compressFormat = Bitmap.CompressFormat.PNG;
            } else {
                // default case
                fileName += ".jpeg";
                compressFormat = Bitmap.CompressFormat.JPEG;
            }

            // now we create the image in the folder
            File imageFile = new File(folder, fileName);
            FileOutputStream out = new FileOutputStream(imageFile);
            // compress it
            bmp.compress(compressFormat, quality, out);
            out.flush();
            out.close();

            retVal = imageFile;

        } catch (Exception e) {
            Log.e("SaveImageToGallery", "An exception occured while saving image: " + e.toString());
        }

        return retVal;
    }

    /**
     * Invoke the system's media scanner to add your photo to the Media Provider's database,
     * making it available in the Android Gallery application and to other apps.
     */
    private void scanPhoto(File imageFile) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(imageFile);

        mediaScanIntent.setData(contentUri);

        cordova.getActivity().sendBroadcast(mediaScanIntent);
    }

    /**
     * Callback from PermissionHelper.requestPermission method
     */
	public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {
		for (int r : grantResults) {
			if (r == PackageManager.PERMISSION_DENIED) {
				Log.d("SaveImageGallery", "Permission not granted by the user");
				_callback.error("Permissions denied");
				return;
			}
		}
		
		switch (requestCode) {
		case WRITE_PERM_REQUEST_CODE:
			Log.d("SaveImageGallery", "User granted the permission for WRITE_EXTERNAL_STORAGE");
            saveBase64Image(this._args, this._callback);
			break;
		}
	}
}