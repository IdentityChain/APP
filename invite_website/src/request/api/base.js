const url = {
  dev: 'http://localhost:9088',
  product: 'http://identitychain.vip:9088'
}
const base = process.env.NODE_ENV === 'development' ? url.dev : url.product
export default base
