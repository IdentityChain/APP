function getYear (Number) {
  let now = new Date()
  now.setMonth(now.getMonth() + Number * 12 + 1)
  return now.getFullYear() + '-' + (now.getMonth() < 10 ? '0' + now.getMonth() : now.getMonth()) + '-' + (now.getDate() < 10 ? '0' + now.getDate() : now.getDate())
}

function isBigThanToday (date) {
  const oDate1 = new Date()
  const oDate2 = new Date(date)
  if (oDate1.getTime() > oDate2.getTime()) {
    return false
  } else {
    return true
  }
}
export {
  getYear,
  isBigThanToday
}
