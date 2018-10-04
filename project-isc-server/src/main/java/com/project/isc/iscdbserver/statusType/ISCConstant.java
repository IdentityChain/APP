package com.project.isc.iscdbserver.statusType;

public class ISCConstant {

	//未知
	public static String ISC_LOG_OTHER = "ISC_LOG_OTHER";
	public static String ISC_LOG_OTHER_ZH = "未知";
	//新建 
	public static String ISC_LOG_NEW="ISC_LOG_NEW";
	public static String ISC_LOG_NEW_ZH="可采集";
	//过期 
	public static String ISC_LOG_OVER="ISC_LOG_OVER";
	public static String ISC_LOG_OVER_ZH="已过期";
	//点击
	public static String ISC_LOG_CHECK="ISC_LOG_CHECK";
	public static String ISC_LOG_CHECK_ZH="已采集";


	//给未来的一封信常量
	//区块链状态常量
	public static String ISC_FL_STATUS_NEW="NEW";				//新建
	public static String ISC_FL_STATUS_ONGOING="ONGOING";		//写入到区块链中
	public static String ISC_FL_STATUS_FAILURE="FAILURE";		//写入到区块链中失败
	public static String ISC_FL_STATUS_COMPLETE="COMPLETE";		//写入到区块链完成
	//是否公开信件
	public static String ISC_FL_IS_PUBLIC_TRUE="PUBLIC";		//公开
	public static String ISC_FL_IS_PUBLIC_FALSE="PRIVATE";		//私有的
	//是否已发送信件到邮箱
	public static String ISC_FL_SEND_LETTER_ISSEND="ISSEND";	//已发送到邮箱
	public static String ISC_FL_SEND_LETTER_NOTSEND="NOTSEND";	//未发送到邮箱
	//加密方式
	public static String ISC_FL_ENCRYPTION_NOT="NOT";			//不加密
	public static String ISC_FL_ENCRYPTION_MD5="MD5";			//MD5加密


}
