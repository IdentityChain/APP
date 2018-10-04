package com.project.isc.iscdbserver.statusType;

public class SmsType {
    public static final  String INIT_USER_INFO="用户完善资料";

    //短信接口-写入到redis
    //使用手机号登录或注册验证码
    public static final String SMS_CODE_TYPE_PHONELOGIN = "PHONELOGIN";
    //修改密码短信验证码
    public static final String SMS_CODE_TYPE_PHONECHANGEPWD = "PHONECHANGEPWD";
    //修改支付密码短信验证码
    public static final String SMS_CODE_TYPE_PHONECHANGEPAYPWD = "PHONECHANGEPAYPWD";
}
