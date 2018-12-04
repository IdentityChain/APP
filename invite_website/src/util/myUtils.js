function checkEmail (str) {
  const re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
  if (re.test(str)) {
    return true
  } else {
    return false
  }
}

export {
  checkEmail
}
