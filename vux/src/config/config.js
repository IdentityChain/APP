// const deployAPP = {
//   dev: false,
//   product: true
// }
// const buildMode = {
//   web: false,
//   app: true,
//   dev: false
// }
// module.exports = {
//   // deployAPP: false,
//   deployAPP: process.env.NODE_ENV === 'development' ? deployAPP.dev : deployAPP.product,
//   useAuth: true
// }
//
//
var deployAPP = false
var apiServer = ''
var useAuth = true
var imgServer = 'http://webapp.identitychain.vip/'
switch (process.env.NODE_ENV) {
  case 'development':
    deployAPP = false
    apiServer = 'http://localhost:9088'
    // apiServer = 'http://identitychain.vip:9088'
    break
  case 'production':
    deployAPP = true
    apiServer = 'http://identitychain.vip:9088'
    break
  case 'web':
    deployAPP = false
    apiServer = 'http://identitychain.vip:9088'
    break
  default:
    deployAPP = false
}
export {deployAPP, useAuth, apiServer, imgServer}
