package com.example.hugh.life.commmon;

public enum SHErrorCode {
	
	// 成功
	SUCCESS(0, "成功"),
	FAILURE(1, "失败"),
	// 错误
	PARAMS_ERROR(-1, "参数错误"),
	SERVER_BUSY(-2, "系统繁忙"),
	SYSTEM_ERROR(-3, "系统错误"),
	OPERATE_DB_FAIL(-4, "数据库操作失败"),
	UNKNOWN(-5, "未知错误"),
	JSON_FAIL(-6, "JSON序列化失败"),
	NO_DATA(-7, "数据不存在"),
	PHONE_NUMBER_ILLEGAL(-8, "手机号码不合法"),
	SEND_PHONE_SMS_TOO_FREQUENTLY(-9, "发送短信太频繁"),
	NOT_APP_MANAGER(-10, "用户不是应用管理员"),
	NO_AUTH(-11, "暂未在灰度范围内,敬请期待"),
	JSON_DESERIALIZATION_FAIL(-12, "JSON反序列化失败"),
	THIRD_APPLICATION_ERROR(-13, "请求第三方响应异常"),
	EMAIL_GENERATE_FAIL(-14, "邮箱验证码生成失败"),
	VERIFICATION_CODE_ERROR(-15, "邮箱验证码不匹配"),
	LOGIN_FAIL(-16, "登陆失败"),
	SMS_GENERATE_FAIL(-17, "短信验证码生成失败"),
	//User 500 - 999
	NOT_USER(501, "找不到该用户"),

	// SearchInfo 1000 - 1249
	ADD_SEARCH_INFO_FAIL(1000, "添加查询记录失败"),
	// Clock 1250-1499
	ADD_CLOCK_FAIL(1250, "添加闹钟记录失败"),
	// Location 1500-2000
	ADD_LOCATION_FAIL(1500, "添加坐标失败"),
	//message 2000 - 2499
	ADD_MESSAGE_FAIL(2000, "添加信息失败"),

	//
	ADD_BURY_FAIL(2500, "添加买点失败"),


	// 图片
	TRANSFER_TPATH_FAIL(2800,"获取TPath失败"),
	TRANSFER_FPATH_FAIL(2801,"获取FPath失败"),

	// 翻译
	TRANSLATE_FAIL(3000,"翻译失败");

	SHErrorCode(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	private int errorCode;
	private String errorMessage;
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public static SHErrorCode getByCode(int code) {
		for (SHErrorCode shErrorCode : values()) {
			if (shErrorCode.getErrorCode() == code) {
				return shErrorCode;
			}
		}
		return null;
	}
}
