cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
  {
    "id": "cordova-plugin-device.device",
    "file": "plugins/cordova-plugin-device/www/device.js",
    "pluginId": "cordova-plugin-device",
    "clobbers": [
      "device"
    ]
  },
  {
    "id": "cordova-plugin-splashscreen.SplashScreen",
    "file": "plugins/cordova-plugin-splashscreen/www/splashscreen.js",
    "pluginId": "cordova-plugin-splashscreen",
    "clobbers": [
      "navigator.splashscreen"
    ]
  },
  {
    "id": "cordova-plugin-statusbar.statusbar",
    "file": "plugins/cordova-plugin-statusbar/www/statusbar.js",
    "pluginId": "cordova-plugin-statusbar",
    "clobbers": [
      "window.StatusBar"
    ]
  },
  {
    "id": "cordova-plugin-wkwebview-engine.ios-wkwebview-exec",
    "file": "plugins/cordova-plugin-wkwebview-engine/src/www/ios/ios-wkwebview-exec.js",
    "pluginId": "cordova-plugin-wkwebview-engine",
    "clobbers": [
      "cordova.exec"
    ]
  },
  {
    "id": "cordova-plugin-wkwebview-engine.ios-wkwebview",
    "file": "plugins/cordova-plugin-wkwebview-engine/src/www/ios/ios-wkwebview.js",
    "pluginId": "cordova-plugin-wkwebview-engine",
    "clobbers": [
      "window.WkWebView"
    ]
  },
  {
    "id": "cordova-plugin-dialogs.notification",
    "file": "plugins/cordova-plugin-dialogs/www/notification.js",
    "pluginId": "cordova-plugin-dialogs",
    "merges": [
      "navigator.notification"
    ]
  },
  {
    "id": "cordova-plugin-save-image-gallery.saveImageGallery",
    "file": "plugins/cordova-plugin-save-image-gallery/www/saveImageGallery.js",
    "pluginId": "cordova-plugin-save-image-gallery",
    "clobbers": [
      "cordova.saveImageGallery"
    ]
  }
];
module.exports.metadata = 
// TOP OF METADATA
{
  "cordova-plugin-whitelist": "1.3.3",
  "cordova-plugin-device": "2.0.2",
  "cordova-plugin-splashscreen": "5.0.2",
  "cordova-plugin-statusbar": "2.4.2",
  "cordova-plugin-wkwebview-engine": "1.1.4",
  "cordova-plugin-dialogs": "2.0.1",
  "cordova-plugin-save-image-gallery": "0.0.26"
};
// BOTTOM OF METADATA
});