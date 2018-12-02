# Cordova saveImageGallery Plugin

[![Build Status](https://travis-ci.org/agomezmoron/cordova-save-image-gallery.svg?branch=master)](https://travis-ci.org/agomezmoron/cordova-save-image-gallery)

This plugin (based on [devgeeks/Canvas2ImagePlugin](http://github.com/devgeeks/Canvas2ImagePlugin)) allows you to save base64 data as a png/jpg image into the device (iOS Photo Library, Android Gallery or WindowsPhone 8 Photo Album).

The plugin is a kind of fork of the [Nexxa/cordova-base64-to-gallery/](http://github.com/Nexxa/cordova-base64-to-gallery) but with a cleaner history and some extra features - like supporting saving into JPG, sending parameters in an easier way, etc.

## Alerts

### WP8 limitations
In this fork, the Android and iOS implementation were adapted but the WP8 cannot save images in PNG or reducing the quality of the images.

## Usage (saveBase64Image)
Call the `window.imageSaver.saveBase64Image()` method using success and error callbacks and the passing the image's base64 in the options JSON:

### Methods
#### `window.imageSaver.saveBase64Image(options [,success, fail])`

Param       | Type       | Default           | Description
----------- | ---------- | ----------------- | ------------------
**options** | *object*   | \*see below       | options
**success** | *function* | **console.log**   | success callback
**fail**    | *function* | **console.error** | fail callback

#### Available options

##### `data`
Base64 input String.

##### `prefix`
Saved file name prefix. Only works in Android and WP8.

**Default**: "img_"

##### `mediaScanner`
Android Media Scanner after file creation enabled or not. Only works in Android!

**Default**: true

##### `format`
Format to save the image. Allowed values 'JPG' and 'PNG'. Only supported for Android and iOS. Currently this option is ignored on Windows implementation.

**Default**: JPG

##### `quality`
Quality of the saved image. If you want to reduce the quality of the image, you are allow to do it. The value should be a number from 1 to 100. Only supported for Android and iOS. Currently this option is ignored on Windows implementation.

**Default**: 100

### Example

```javascript
function onDeviceReady() {
    var params = {data: base64String, prefix: 'myPrefix_', format: 'JPG', quality: 80, mediaScanner: true};
    window.imageSaver.saveBase64Image(params,
        function (filePath) {
          console.log('File saved on ' + filePath);
        },
        function (msg) {
          console.error(msg);
        }
      );
}
```

## Usage (removeImageFromLibrary)
Call the `window.imageSaver.removeImageFromLibrary()` method using success and error callbacks and the passing the image URL in the options JSON:

### Methods
#### `window.imageSaver.removeImageFromLibrary(options [,success, fail])`

Param       | Type       | Default           | Description
----------- | ---------- | ----------------- | ------------------
**options** | *object*   | \*see below       | options
**success** | *function* | **console.log**   | success callback
**fail**    | *function* | **console.error** | fail callback

#### Available options

##### `data`
File path input String.

### Example

```javascript
function onDeviceReady() {
    var params = {data: "/data/data/test.png"};
    window.imageSaver.removeImageFromLibrary(params,
        function (filePath) {
          console.log('File removed from ' + filePath);
        },
        function (msg) {
          console.error(msg);
        }
      );
}
```

## Authors and contributors
- [Tommy-Carlos Williams](http://github.com/devgeeks)
- [Simba Zhang](http://github.com/solderzzc)
- [StefanoMagrassi](http://github.com/StefanoMagrassi)
- [Alejandro Gomez](http://github.com/agomezmoron)
- [Bastian Meier](http://github.com/bastian-meier)
