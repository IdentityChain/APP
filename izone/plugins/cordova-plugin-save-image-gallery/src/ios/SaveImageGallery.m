//
//  SaveImageGallery.m
//  SaveImageGallery PhoneGap/Cordova plugin
//
//  Copyright (c) 2016 Alejandro Gomez <agommor@gmail.com>
//
//  Based on StefanoMagrassi's "Base64ToGallery.m"
//
//	MIT Licensed
//

#import "SaveImageGallery.h"
#import <Cordova/CDV.h>

@implementation SaveImageGallery

- (void) saveImageDataToLibrary : (CDVInvokedUrlCommand*) command {
    [self.commandDelegate runInBackground : ^{
        self.callbackId = command.callbackId;
        self.result = nil;
        
        NSString *base64String = [command.arguments objectAtIndex : 0];
        NSString *prefix = [command.arguments objectAtIndex : 1];
        bool cameraRoll = [[command.arguments objectAtIndex : 2] boolValue];
        NSString *format = [command.arguments objectAtIndex : 3];
        NSInteger quality = [[command.arguments objectAtIndex : 4] integerValue];
        
        if (base64String != nil && [base64String length] > 0) {
            
            NSData *imageData = [[[NSData alloc] initWithBase64EncodedString : base64String options : 0] autorelease];
            UIImage *image = [[[UIImage alloc] initWithData : imageData] autorelease];
            
            // image data
            NSData *imageDataObject = nil;
            // image extension
            NSString *imageExtension = @".jpg";
            // converts the UIImage to NSData (JPG by default)
            if ([format isEqualToString : @"PNG"]) {
                imageDataObject = UIImagePNGRepresentation(image);
                imageExtension = @".png";
            } else {
                imageDataObject = UIImageJPEGRepresentation(image, (quality / 100.f));
                imageExtension = @".jpg";
            }
            
            // get Timestamp
            double currentTime = CACurrentMediaTime();
            
            // set fileName
            NSString *timeString = [NSString stringWithFormat : @"%f", currentTime];
            timeString = [timeString stringByReplacingOccurrencesOfString : @"." withString : @""];
            NSString *fileName = [prefix stringByAppendingString : timeString];
            fileName = [fileName stringByAppendingString : imageExtension];
            
            NSString *libPath = NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES)[0];
            if (cameraRoll) {
                libPath = NSSearchPathForDirectoriesInDomains(NSLibraryDirectory, NSUserDomainMask, YES)[0];
            }
            NSString *libPathNoSync = [libPath stringByAppendingPathComponent : @"NoCloud"];
            NSFileManager *fileManager = [NSFileManager defaultManager]; //create instance of NSFileManager
            
            // Create the directory if necessary.
            [fileManager createDirectoryAtPath : libPathNoSync withIntermediateDirectories : YES attributes : nil error : nil];
            
            NSString *imagePath = [libPathNoSync stringByAppendingPathComponent : fileName];
            
            // writeToFile
            bool success = [fileManager createFileAtPath : imagePath contents : imageDataObject attributes : nil];
            
            if (success) {
                // write to documents folder was successfull
                if (cameraRoll) {
                    // add the image to camera roll
                    UIImage * savedImage = [UIImage imageWithContentsOfFile : imagePath];
                    UIImageWriteToSavedPhotosAlbum(savedImage, nil, nil, nil);
                }
                // adding file:// prefix
                NSString *fullImagePath = [@"file://" stringByAppendingPathComponent : imagePath];
                CDVPluginResult * pluginResult = [CDVPluginResult resultWithStatus : CDVCommandStatus_OK messageAsString : fullImagePath];
                [pluginResult setKeepCallbackAsBool:YES];
                [self.commandDelegate sendPluginResult : pluginResult callbackId : command.callbackId];
                
            } else {
                imagePath = [imagePath stringByAppendingString : @" - error writing image to documents folder"];
                CDVPluginResult * pluginResult = [CDVPluginResult resultWithStatus : CDVCommandStatus_ERROR messageAsString : imagePath];
                [pluginResult setKeepCallbackAsBool:YES];
                [self.commandDelegate sendPluginResult : pluginResult callbackId : command.callbackId];
            }
            
        } else {
            CDVPluginResult * pluginResult = [CDVPluginResult resultWithStatus : CDVCommandStatus_ERROR messageAsString : @"no valid base64 image data was passed"];
            [pluginResult setKeepCallbackAsBool:YES];
            [self.commandDelegate sendPluginResult : pluginResult callbackId : command.callbackId];
        }
        
    }
     ];
}

-(void) removeImageFromLibrary : (CDVInvokedUrlCommand*) command {
    [self.commandDelegate runInBackground : ^{
        self.callbackId = command.callbackId;
        self.result = nil;
        
        NSString *filepath = [command.arguments objectAtIndex : 0];
        
        
        if (filepath != nil && [filepath length] > 0) {
            NSFileManager *fileManager = [NSFileManager defaultManager]; //create instance of NSFileManager
            NSError *error = nil;
            // removeFile
            bool success = [fileManager removeItemAtPath : filepath error : &error];
            
            if (success) {
                CDVPluginResult * pluginResult = [CDVPluginResult resultWithStatus : CDVCommandStatus_OK messageAsString : filepath];
                [pluginResult setKeepCallbackAsBool:YES];
                [self.commandDelegate sendPluginResult : pluginResult callbackId : command.callbackId];
                
            } else {
                filepath = [filepath stringByAppendingString : @" - error removing the file"];
                CDVPluginResult * pluginResult = [CDVPluginResult resultWithStatus : CDVCommandStatus_ERROR messageAsString : filepath];
                [pluginResult setKeepCallbackAsBool:YES];
                [self.commandDelegate sendPluginResult : pluginResult callbackId : command.callbackId];
            }
        } else {
            CDVPluginResult * pluginResult = [CDVPluginResult resultWithStatus : CDVCommandStatus_ERROR messageAsString : @"not valid filepath"];
            [pluginResult setKeepCallbackAsBool:YES];
            [self.commandDelegate sendPluginResult : pluginResult callbackId : command.callbackId];
        }
        
    }
     ];
}
@end
