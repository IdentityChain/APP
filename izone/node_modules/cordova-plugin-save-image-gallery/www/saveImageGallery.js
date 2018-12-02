/**
 * @file saveImageGallery PhoneGap/Cordova plugin
 * @author Alejandro Gómez <agommor@gmail.com>
 * @author Tommy-Carlos Williams
 * @author Simba Zhang <solderzzc@gmail.com>
 * @author StefanoMagrassi <stefano.magrassi@gmail.com>
 * @copyright Alejandro Gómez - 2016. All rights reserved.
 * @license MIT
 */

var exec = require('cordova/exec');

// Consts
var SERVICE = 'SaveImageGallery';
var SAVE_BASE64_IMAGE_ACTION = 'saveImageDataToLibrary';
var REMOVE_IMAGE_ACTION = 'removeImageFromLibrary';
var DEFAULTS = {prefix: 'img_', mediaScanner: true, format: 'JPG', quality: 100};


var ImageSaver = function () {

};


/**
 * Constants.
 */
ImageSaver.FORMAT_JPG = 'jpg';
ImageSaver.FORMAT_PNG = 'png';

/**
 * Saves base64 data as image.
 * @public
 * @param  {string}   [options]
 * JSON information of the image:
 *  data: Base64 img.
 *  prefix: String
 *  format: 'JPG' or 'PNG' - defaults to 'JPG'
 *  mediaScanner: BOOLEAN. If true, the image will be created in the temporal directory. Otherwise the image will
 *    be created in the photo album. - defaults to true
 *  quality: FLOAT (from 0 to 100). - defaults to 100
 * @param  {function} [success]
 * @param  {function} [fail]
 * @return The path of the file.
 */

ImageSaver.prototype.saveBase64Image = function (options, success, fail) {
  var actionArgs = prepareSaveBase64Args(options);

  return exec(ok(success), error(fail), SERVICE, SAVE_BASE64_IMAGE_ACTION, actionArgs);
};

/**
 * Remove an image from a given path.
 * @public
 * @param  {string}   [options]
 * JSON information of the image:
 *  data: Image location.
 * @param  {function} [success]
 * @param  {function} [fail]
 * @return
 */

ImageSaver.prototype.removeImage = function (options, success, fail) {
  var actionArgs = prepareRemoveImageArgs(options);

  return exec(ok(success), error(fail), SERVICE, REMOVE_IMAGE_ACTION, actionArgs);
};

/**
 * Gets success callback if it is defined and not null.
 * Otherwise returns a simple console.log.
 * @private
 * @param  {[function|undefined|null]} success
 * @return {function}
 */
function ok(success) {
  if (typeof success !== 'function') {
    return console.log;
  }

  return success;
}

/**
 * Gets fail callback if it is defined and not null.
 * Otherwise returns a simple console.error.
 * @private
 * @param  {[function|undefined|null]} fail
 * @return {function}
 */
function error(fail) {
  if (typeof fail !== 'function') {
    return console.error;
  }

  return fail;
}

/**
 * Prepares parameters to pass to Service's Action.<br/>
 * Sort options value in order to match "arguments" proto.
 * @private
 * @param  {object} options - Options object
 * @return {array}  Arguments array. The elements will be ordered: [filePath]
 */
function prepareRemoveImageArgs(options) {
  var args = [];

  // checking options
  if (!options) {
    options = {};
  }

  // preparing data information
  if (!options.data) {
    options.data = '';
  }
  // preparing base64 string
  options.data = options.data.replace('file://', '').replace('file:/', '/');
  args.push(options.data);

  return args;
}

/**
 * Prepares parameters to pass to Service's Action.<br/>
 * Sort options value in order to match "arguments" proto.
 * @private
 * @param  {object} options - Options object
 * @return {array}  Arguments array. The elements will be ordered: [base64String, filePrefix, mediaScannerEnabled, format, quality]
 */
function prepareSaveBase64Args(options) {
  var args = [];

  // checking options
  if (!options) {
    options = {};
  }

  // preparing data information
  if (!options.data) {
    options.data = '';
  }
  // preparing base64 string
  options.data = options.data.replace(/data:image\/png;base64,/, '').replace(/data:image\/jpeg;base64,/, '').replace(
    /data:image\/jpg;base64,/, '');
  args.push(options.data);

  // preparing prefix
  if (!options.prefix) {
    options.prefix = DEFAULTS.prefix;
  }
  args.push(options.prefix);

  // preparing mediaScanner
  if (options.mediaScanner === undefined || options.mediaScanner === null) {
    options.mediaScanner = DEFAULTS.mediaScanner;
  }
  args.push(options.mediaScanner);

  // preparing format
  if (!options.format) {
    options.format = DEFAULTS.format;
  }
  args.push(options.format);

  // preparing quality
  if (!options.quality || (!isNaN(options.quality) && options.quality <= 0)) {
    options.quality = DEFAULTS.quality;
  }
  args.push(options.quality);

  return args;
}


window.ImageSaver = ImageSaver;
window.imageSaver = new ImageSaver();
module.exports = new ImageSaver();
