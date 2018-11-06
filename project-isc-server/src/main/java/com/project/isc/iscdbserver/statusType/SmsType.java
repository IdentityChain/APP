package com.project.isc.iscdbserver.statusType;

public class SmsType {
//    public static final  String INIT_USER_INFO="用户完善资料";

    //短信接口-写入到redis
    //使用手机号登录或注册验证码
    public static final String SMS_CODE_TYPE_PHONELOGIN = "PHONELOGIN";
    public static final String SMS_CODE_TYPE_PHONELOGIN_KEY = "SMS_147195175";
    //修改密码短信验证码
    public static final String SMS_CODE_TYPE_PHONECHANGEPWD = "PHONECHANGEPWD";
    public static final String SMS_CODE_TYPE_PHONECHANGEPWD_KEY = "SMS_147195177";
    //修改支付密码短信验证码
    public static final String SMS_CODE_TYPE_PHONECHANGEPAYPWD = "PHONECHANGEPAYPWD";
    public static final String SMS_CODE_TYPE_PHONECHANGEPAYPWD_KEY = "SMS_147195178";
    //default
    public static final String SMS_CODE_TYPE_DEFAULT = "SMS_DEFAULT";
    public static final String SMS_CODE_TYPE_DEFAULT_KEY = "SMS_150174205";

    public static String getCodeType(String phone,String codetype){
        return phone+codetype;
    }
}
