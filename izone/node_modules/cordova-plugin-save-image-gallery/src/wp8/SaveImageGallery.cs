using Microsoft.Xna.Framework.Media;
using System;
using System.IO;
using System.Text;
using WPCordovaClassLib.Cordova;
using WPCordovaClassLib.Cordova.Commands;
using WPCordovaClassLib.Cordova.JSON;

/**
 * SaveImageGallery.cs
 *
 * Extended WP8 of StefanoMagrassi's code.
 * Inspirated by StefanoMagrassi's code.
 * https://github.com/Nexxa/cordova-base64-to-gallery
 *
 * @author Alejandro Gomez <agommor@gmail.com>
 */
public class SaveImageGallery : BaseCommand
{
    public SaveImageGallery()
	{
	}

    public void saveImageDataToLibrary(string jsonArgs)
    {
        try
        {
            var options = JsonHelper.Deserialize<string[]>(jsonArgs);

            string imageData  = options[0];
            string prefix     = options[1];
            byte[] imageBytes = Convert.FromBase64String(imageData);
            // the following parameters are not used
            // WP8 doesn't allow you to save different images than JPG, cannot reduce quality and cannot storage
            // in another place different than the MediaLibrary
            boolean mediaScannerEnabled = options[2];
            string format = options[3];
            int quality = options[4];

            using (var imageStream = new MemoryStream(imageBytes))
            {
                imageStream.Seek(0, SeekOrigin.Begin);

                string fileName = prefix + String.Format("{0:yyyyMMddHHmmss}", DateTime.Now);
                var library     = new MediaLibrary();
                // WP8 doesn't allow you to save other format different than jpg
                var picture     = library.SavePicture(fileName, imageStream);

                if (picture.Name.Contains(fileName))
                {
                    DispatchCommandResult(new PluginResult(PluginResult.Status.OK));
                }
                else
                {
                    DispatchCommandResult(new PluginResult(PluginResult.Status.ERROR, "Failed to save image: " + picture.Name));
                }
            }
        }
        catch (Exception ex)
        {
            DispatchCommandResult(new PluginResult(PluginResult.Status.ERROR, ex.Message));
        }
    }

    public void removeImageFromLibrary(string jsonArgs)
    {
      /*
       * "There is no way to delete any content from the user's library. Once you've saved a picture,
       * the user will always have that picture unless they personally go delete it."
       * (Source Delete image from MediaLibrary.SavePictures).
       */
      DispatchCommandResult(new PluginResult(PluginResult.Status.ERROR, "Not supported operation"));
    }


}
