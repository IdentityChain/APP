//
//  SaveImageGallery.h
//  SaveImageGallery PhoneGap/Cordova plugin
//
//	Copyright (c) 2016 Alejandro Gomez <agommor@gmail.com>
//
//  Based on StefanoMagrassi's "Base64ToGallery.h"
//
//	MIT Licensed
//

#import <Cordova/CDV.h>

@interface SaveImageGallery : CDVPlugin

@property(nonatomic, copy) NSString* callbackId;
@property(nonatomic, assign) CDVPluginResult* result;

-(void) saveImageDataToLibrary : (CDVInvokedUrlCommand*) command;
-(void) removeImageFromLibrary : (CDVInvokedUrlCommand*) command;

@end
