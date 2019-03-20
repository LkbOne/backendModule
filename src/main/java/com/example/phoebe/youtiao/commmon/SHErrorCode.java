package com.example.phoebe.youtiao.commmon;

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
	
	// 微信(1000-1999)
	REQUEST_WX_FAILED(1000, "获取微信后台数据失败"),
	WX_RESPONSE_DATA_ERROR(1001, "微信后台数据错误"),
	DECRYPT_FAILED(1002, "解密失败"),
	WX_USER_NOFOUND(1003, "相关微信用户数据获取失败"),
	QRCODE_NOFOUND(1004, "找不到该小程序二维码"),
	
	// 小程序登录(2000-2999)
	LOGIN_TOKEN_INVALID(2000, "token无效"),
	LOGIN_NEED_RELOGIN(2001, "需要重新登录"),
	CHECK_LOGIN_PHONE_MESSAGE_FAILED(2002, "验证码错误"),
	LOGIN_BLACK_LIST(2003, "本账号已被禁用"),
	
	// 登录(3000-3999)
	FS_SEND_SMCODE_FAIL(3000, "获取纷享验证码失败"),
	FS_VERITY_SMCODE_FAIL(3001, "验证纷享验证码失败"),
	FS_GET_ENTERPRISEUSER_LIST_FAIL(3002, "获取企业列表失败"),
	FS_COOKIE_INVALID(3003, "cookie失效"),
	FS_COOKIE_NOT_FOUND(3004, "找不到cookie"),
	USER_ACCOUNT_NOT_EXIST(3005, "用户帐号不存在"),
	NEED_PHOTO_CHECK_CODE(3006, "需要图形验证码"),
	SEND_PHONE_CHECK_ONE_MINUTE_LATER(3007, "发送验证码太频繁，请一分钟后再次获取"),
	ENTERPRSE_ACCOUNT_STOP(3008, "企业帐号已终止"),
	PHOTO_CHECK_CODE_ERROR(3009, "图形验证码错误"),
	PHOTO_NOT_LOGIN_FS(3010, "未注册手机号"),
	ENTERPRISE_ACCOUNT_INVALID(3011, "纷享企业账号无效"),
	FS_ACCOUNT_FORBIDDEN(3012, "纷享账号无效禁止登录"),
	FS_ACCOUNT_STOPPED(3013, "纷享账号被停用"),
	
	LOGINFS_NEED_SCAN_QR_CODE(3005, "需要强制扫码登录,请登录网页端操作"),
	LOGINFS_INITIAL_PASSWORD_WITH_MOBILE(3006, "是初始化密码并且绑定了手机号,请登录网页端操作"),
	LOGINFS_INITIAL_PASSWORD_WITHOUT_MOBILE(3007, "初始化密码却没有绑定手机号,请登录网页端操作"),
	LOGINFS_SMS_CODE_NEVER_SET_PASSWORD(3008, "用动态密码登录且从未设置密码,请登录网页端操作"),
	LOGINFS_SMS_CODE_INITIAL_PASSWORD(3009, "用动态密码登录且未初始密码,请登录网页端操作"),
	LOGINFS_WEAK_PASSWORD_WITH_MOBILE(3010, "弱密码并且绑定手机号,请登录网页端操作"),
	LOGINFS_WEAK_PASSWORD_WITHOUT_MOBILE(3011, "弱密码却没有绑定手机号,请登录网页端操作"),
	LOGINFS_NEED_SMCODE_AUTHORIZE(3012, "需要短信验证码授权,请登录网页端操作"),
	LOGINFS_NEED_SMCODE_DEVICE_AUTHORIZE(3013, "需要短信验证码或者其他设备授权"),
	
	// 小程序注册(4000-4999)
	ACCOUNT_PHONE_NOT_FOUND(4000, "该手机号未注册"),
	ACCOUNT_PHONE_USED(4001, "该手机号已注册"),
	ACCOUNT_NOT_FOUND(4002, "找不到该注册用户"),
	ACCOUNT_EXIST(4003, "该用户已存在"),
	ACCOUNT_FSAPPLYINFOKEY_INVALID(4004, "FSApplyInfoKey无效"),
	ACCOUNT_PHONE_OTHER_USED(4005, "该手机号已被其他微信号所注册"),
	ACCOUNT_PHONE_RESET_PHONE_CANT_BE_SAME(4006, "新手机号不能与旧手机号相同"),
	ACCOUNT_PHONE_UPDATE_FAIL(4007, "更换手机号失败"),
	ACCOUNT_COOKIEKEY_INVALID(4008, "CookieKey无效"),
	ACCOUNT_EXPERIENCE_FS_REFUSE(4009, "暂不支持纷享体验账号注册"),
	ACCOUNT_PHONE_NOT_EXIST(4010,"该账号暂未绑定手机号"),
	
	// 小程序访客(5000-5999)
	USER_NOT_FOUND(5000, "找不到该访客"),
	USER_ADD_FAIL(5001, "用户创建失败"),
	USER_DEL_ILLEGAL(5002, "该用户不在自杀授权范围内"),

	
	//小程序绑定(6000-6999)
	FSBIND_USER_EXIST(6000, "该手机号已被绑定"),
	FSBIND_USER_NOFOUND(6001, "该用户未绑定纷享企业"),
	FSBIND_USER_FAIL(6002, "绑定纷享企业失败"),
	FSBIND_OTHER_COMPANY(6003, "绑定其他纷享企业"),
	FSBIND_PHONE_NULL(6004, "绑定手机号不能为空"),
	GET_ENTERPRISE_BY_PHONE_FAILED(6005, "通过纷享注册手机号获取注册公司信息失败"),
	GET_ENTERPRISE_NAME_FAILED(6006, "查询公司名称失败"),
	PHONE_NOT_LOGIN_ENTERPRISE(6007, "手机号未注册纷享帐号"),
	FSBIND_DISSOCIATE_FAIL(6008, "解除绑定失败"),
	FSBIND_FSUSER_EXIST(6000, "该纷享账号已被绑定"),
	FSBIND_CANT_BIND_SAME(6001, "当前已绑定该企业,不能重复绑定"),
	
	//以下为业务错误码,从10000起累加,每个业务预设区间100
	
	// BusinessCard(10000-10099)
	BUSINESSCARD_USER_NOFOUND(10000, "找不到该名片"),
	BUSINESSCARD_ADD_FAIL(10001, "名片创建失败"),
	BUSINESSCARD_UPDATE_FAIL(10002, "名片更新失败"),
	BUSINESSCARD_QRURL_CREATE_FAIL(10003, "专属码创建失败"),
	BUSINESSCARD_QRURL_NOFOUND(10004, "未创建专属码"),
	CARDFRIENDRELATION_SEEN_ADD(10005, "创建我看过的名片关系失败"),
	CARDFRIENDRELATION_SEEN_UPDATE(10006, "更新我看过的名片关系失败"),
	CARDFRIENDRELATION_EXCHANGE_ADD(10007, "创建交换名片关系失败"),
	CARDFRIENDRELATION_EXCHANGE_UPDATE(10008, "更新交换名片关系失败"),
	BUSINESSCARD_PREPARETOSWAP_MYCARD_NOFOUND(10009, "找不到自己的名片"),
	BUSINESSCARD_PREPARETOSWAP_TARGETCARD_NOFOUND(10010, "找不到目标名片"),
	BUSINESSCARD_PREPARETOSWAP_CANTSWAPOWNS(10011, "不能交换自己的名片"),
	BUSINESSCARD_SERVICER_NOFOUND(10012, "客脉小秘书名片不存在"),
	BUSINESSCARD_CANT_EDIT_OTHERS(10013, "不能编辑非自己的名片"),
	CARDFRIENDRELATION_SEEN_QUERY_FAILED(10014, "查询我看过的名片失败"),
	CARDFRIENDRELATION_EXCHANGE_QUERY_FAILED(10015, "查询交换名片失败"),
	CARDFRIENDRELATION_SWAP_ACCEPTED(10016, "该交换名片已被同意过"),
	CARDFRIENDRELATION_SWAP_REFUSED(10017, "该交换名片已被拒绝过"),
	CARDFRIENDRELATION_SWAP_WAITTING(10018, "该交换名片等待对方处理中"),
	CARDFRIENDRELATION_TOTAL_QUERY_FAILED(10019, "查询名片列表失败"),
	
	BUSINESSCARD_PRIVACY_KEY_EMAIL_NOFOUND(10019, "找不到邮箱隐私字段"),
	BUSINESSCARD_PRIVACY_KEY_PHONE_NOFOUND(10020, "找不到手机号隐私字段"),
	BUSINESSCARD_PRIVACY_KEY_WECHAT_NOFOUND(10021, "找不到微信号隐私字段"),
	BUSINESSCARD_PRIVACY_VALUE_INVALID(10022, "非法的隐私枚举值"),
	BUSINESSCARD_USER_EXIST(10023, "名片已存在"),
	BUSINESSCARD_UPDATE_PRIVACY_FAILED(10024, "名片隐私字段更新失败"),

	CARDFRIENDRELATION_SWAP_HIS_WAIT(10025, "对方已邀请过交换名片,请直接同意"),
	
	// Product(10100-10199)
	PRODUCT_CREATE_FAIL(10100, "产品创建失败"),
	PRODUCT_UPDATE_FAIL(10101, "产品更新失败"),
	PRODUCT_LIST_FAIL(10102, "获取产品列表失败"),
	PRODUCT_DETAIL_FAIL(10103, "获取产品详情失败"),
	PRODUCT_USER_ADD_FAIL(10104, "关联用户产品失败"),
	PRODUCT_USER_DEL_FAIL(10105, "解除关联用户产品失败"),
	PRODUCT_RECOMMEND_FAIL(10106, "推荐产品失败"),
	PRODUCT_CAN_NOT_DELETE(10107, "产品不可删除"),
	PRODUCT_USER_LIST_NOT_FOUND(10108, "该用户暂未关联任何产品"),
	PRODUCT_MORE_THAN_ONE_RECOMMEND(10109, "推荐产品超过一个"),
	PRODUCT_CORPORATE_DEL_FAIL(10110, "公司产品解除关联失败"),
	PRODUCT_ID_NOT_NULL(10111, "产品ID为空"),
	// 文件系统(10300-10399)
	FILE_UPLOAD_FAILED(10300, "文件保存失败"),
	FILE_PREVIEW_URL_GET_FAILED(10301, "文件预览连接获取失败"),
	FILE_NOT_FOUND(10302, "文件未找到"),
	FILE_FORMAT_ERROR(10303, "文件格式错误"),
	PIC_CONVERT_FAILED(10304, "图片上传失败"),
	
	// 模板消息(10500-10599)
	SEND_FAILED(10500, "发送模板消息失败"),
	
	// 用户产品关联(10600-10699)
	USERPRODUCT_ADD_FAIL(10600, "用户产品创建关联失败"),
	USERPRODUCT_DEL_FAIL(10601, "用户产品解除关联失败"),
	USERPRODUCT_SELECT_FAIL(10602, "用户产品选择关联失败"),
	
	// 标签(10700-10799)
	TAG_NOFOUND(10700, "标签不存在"),
	TAG_UPDATE_FAIL(10701, "标签更新失败"),
	TAG_DEL_FAIL(10702, "删除标签失败"),
	
	// 群(10800-10899)
	GROUP_BINDUSER_FAIL(10800, "绑定群用户失败"),
	GROUP_ADDCARD_FAIL(10800, "添加群卡片失败"),
	GROUP_ADD_FAIL(10801, "添加群失败"),
	GROUP_QUERY_FAILED(10802, "查询群失败"),
	
	// 埋点(10900-10999)
	TRACE_OBJTTYPE_ERROR(10900, "埋点对象类型错误"),
	TRACE_REJECTED(10901, "该用户被禁止上报埋点"),
	
	// 纷享相关(11000-11999)
	EMPLOYEE_NOT_FOUND(11000, "员工信息不存在"),
	ENTERPRISE_NOT_FOUND(11001, "员工企业信息不存在"),
	DEPARTMENT_NOT_FOUND(11002, "员工部门信息不存在"),
	PHONE_NOT_ACCOUNT_BIND(11003, "手机号未绑定"),
	USER_NOT_BIND_EA(11004, "未绑定企业"),
	EMPLOYEE_MOBILE_NOT_FOUND(11005, "员工手机号信息不存在"),
	FS_COOKIE_LOGIN_FAILED(11006, "纷享cookie登录失败"),
	
	// 文章(12000-12999)
	ADD_FAIL(12000, "新建失败"),
	UPDATE_FAIL(12001, "修改失败"),
	DEL_FAIL(12002, "删除失败"),
	DEL_ARTICLE_FAIL_TIP(12003, "已启用的文章不能删除，请先停用!"),
	ARTICLE_NOT_EXIST(12004, "该文章不存在"),
	ARTICLE_EXIST(12010, "文章已存在"),
	ADD_PHOTO_FAIL(12005, "新建动态分享图片失败"),
	FEED_NOT_EXIST(12006, "动态不存在"),
	WEB_CRAWLER_FAIL(12007, "网络异常，请重试!"),
	ARTICLE_NOT_EXIST_FEED(12008, "文章没有存动态"),
	ARTICLE_CAN_NOT_DELETE(12009, "文章不可删除"),
	UID_QUERY_BY_FEED_ID_FAIL(12010, "通过feedId查询redis获取uid失败"),
	ARTICLE_INNER_HTML_NOT_EXIST(12011, "html不存在"),
	ARTICLE_DELETED(12012, "文章已被删除"),
	
	// 客脉web（13000-13999）
	COOKIE_NOT_FOUND(13000, "cookie 不存在"),
	
	// 参数校验错误码，名片、产品以及客脉（14000-14999）
	CARD_NAME_INVALID(14000, "姓名不能为空且不能超过20个字"),
	CARD_PHONE_INVALID(14001, "手机不能为空且为11位数字"),
	CARD_AVATAR_INVALID(14002, "头像不能为空"),
	CARD_COMPANY_INVALID(14003, "公司名字不能为空且不能超过50个字"),
	CARD_ADDRESS_INVALID(14004, "公司地址不能超过100个字"),
	CARD_SUMMARY_INVALID(14005, "个人介绍不能超过1000个字"),
	CARD_VACATION_INVALID(14006, "职位不能超过20个字"),
	CARD_DEPARTMENT_INVALID(14007, "部门不能超过20个字"),
	CARD_WECHAT_INVALID(14008, "微信号不能超过20个字"),
	CARD_EMAIL_INVALID(14009, "邮箱不能超过50个字"),
	CARD_PIC_INVALID(14010, "照片不能超过9张"),
	PRODUCT_SUMMARY_INVALID(14011, "产品介绍不能为空且不能超过140个字"),
	PRODUCT_HEAD_INVALID(14012, "产品封面不能为空且不超过5张"),
	PRODUCT_DETAIL_INVALID(14013, "产品详情不能为空且不超过9张"),
	PRODUCT_NAME_INVALID(14014, "产品名字不能为空且不能超过50个字"),
	MANKEEP_COMPANY_INVALID(14015, "公司名字不能为空且不能超过50个字"),
	MANKEEP_PHONE_INVALID(14016, "手机不能为空且为11位数字"),
	MANKEEP_EMAIL_INVALID(14017, "邮箱不能超过50个字"),
	MANKEEP_WECHAT_INVALID(14018, "微信号不能超过20个字"),
	MANKEEP_QQ_INVALID(14019, "qq号不能超过20个字"),
	MANKEEP_ADDRESS_INVALID(14020, "公司地址不能超过100个字"),
	MANKEEP_VACATION_INVALID(14021, "职位不能超过20个字"),
	MANKEEP_DEPARTMENT_INVALID(14022, "部门不能超过20个字"),
	MANKEEP_NAME_INVALID(14023, "客户名字不能为空且不能超过20个字"),
	TAG_NAME_INVALID(14024, "标签不能超过10个字"),
	INTERACT_CONTENT_INVALID(14025, "互动不能超过1024个字"),
	LEAD_REMARK_INVALID(14026, "备注不能超过20个字"),
	FEED_SUMMARY_INVALID(14027, "文章摘要长度超过限制"),
	FEED_RECONMENDATION_INVALID(14028, "推荐语长度超过限制"),

	// customer(15000-15999)
	CUSTOMER_ADD_EXIST(15000, "该客脉已存在"),
	CUSTOMER_ADD_FAILED(15001, "客脉创建失败"),
	CUSTOMER_NOT_FOUND(15002, "该客脉不存在"),
	CUSTOMER_UPDATE_FAILED(15003, "客脉更新失败"),
	CUSTOMER_UPGRADE_CANT_DOWNGRADE(15004, "客脉暂不允许降级"),
	CUSTOMER_FOLLOWUP_ADD_FAILED(15005, "客脉添加跟进失败"),
	CUSTOMER_UPGRADE_CANT_SKIPGRADE(15006, "客脉暂不允许跨越升级"),

	// feed_like(16000-16999)
	FEED_LIKE_FEED_NOT_EXISTS(16000,"找不到feed"),
	FEED_LIKE_HAS_LIKED(16001,"已经感谢过该文章了"),

	// 灰度(17000-17999)
	GRAY_LUCKYMONEY_IS_NOT_FSBIND(17000, "该账号未绑定纷享企业, 暂不在客脉红包灰度范围内"),
	GRAY_LUCKYMONEY_COMPANY_REFUSED(17001, "该企业暂不在客脉红包灰度范围内"),

	//notice (18000 - 18999)
	NOTICE_NOT_EXIST(18000, "推广通知不存在"),
	NOTICE_RELATE_CONTENT_NOT_EXIST(18001, "推广通知关联对象不存在"),
	
	// 保存数据到CRM异常 (20000 - 21000)
	CRM_BUSINESS_ERROR(20000, "CRM业务异常"),
	FIELD_MAPPING_VERIFY_ERROR(20001, "字段映射校验错误"),
	NOT_NULL_CRM_FIELD_NOT_MAPPED(20002, "CRM必填字段没有正确映射"),
	CUSTOMER_FIELD_SHOULD_NOT_BE_NULL(20003, "客脉字段不能为空"),
	SAVE_CUSTOMER_TO_CRM_DISABLED(20004, "暂未开启客户保存到CRM，请联系客脉通管理员"),
	CUSTOMER_HAS_EXIST_IN_CRM(20005, "该客户在CRM中已存在"),
	NOT_A_MANKEEP(20006, "不是人脉"),
	NOT_A_CUSTOMER(20007, "不是客户"),
	LEAD_HAS_EXIST_IN_CRM(20008, "该线索在CRM中已存在"),
	CUSTOMER_SAVED(20009, "客户已保存，无需重复保存"),
	MANKEEP_SAVED(20010, "人脉已保存，无需重复保存"),
	SAVE_MANKEEP_TO_CRM_DISABLED(20011, "暂未开启人脉保存到CRM，请联系客脉通管理员"),

	// 活动邀请相关错误码 (21001 - 22000)
	ACTIVITY_DISABLE(21001, "活动已停用"),
	ENROLL_STOPPED(21002, "感谢您的访问，本次活动已截止报名"),
	ACTIVITY_NOT_EXIST(21003, "活动不存在"),

	// 六度人脉 (22001 - 23000)
	SPREAD_NOT_FOUND(22001, "六度人脉数据不存在"),
	SPREAD_READ_FAILED(22002, "六度人脉数据更新阅读量失败"),
	SPREAD_FORWARD_FAILED(22003, "六度人脉数据更新转发量量失败"),
	SPREAD_ADD_FAILED(22003, "六度人脉数据更新转发量量失败"),

	// 视频上传转码 (24001 - 25000)
	VIDEO_UPLOAD_FAILED(24001, "视频上传失败"),
	VIDEO_STATUS_FAILED(24002, "更新视频失败"),
	VIDEO_TRANS_FAILED(24003, "视频转码失败"),
	VIDEO_DELETE_FAILED(24004, "视频删除失败"),
	VIDEO_CANCLED_FAILED(24005, "视频取消上传失败"),
	VIDEO_EXIST(24006, "视频已存在，不应重复提交"),


    // 营销通相关的错误码（50000 - 51000）
    INVALID_FS_LOGIN(50000, "纷享用户登录状态错误"),
    USER_IS_NOT_MARKETING_APP_MANAGER(50001, "该用户不是营销通应用管理员"),
	USER_IS_NOT_FSBIND(50002, "该账号未绑定纷享企业"),
	USER_HAS_ENROLL(50003, "该用户已经报名此活动"),
	USER_NOT_ENROLL(50004, "该用户已经未此活动"),
	USER_HAS_SIGNED(50005, "该用户已经签到了"),

	// 自动回复错误码(23001 - 24000)
	SETTING_NOT_DATA(23001, "相关项无数据"),
	SETTING_NOT_SET_ALL(23002, "相关项没有设置可见"),

	// 微信公众号相关错误码(25001 - 26000)
	NOT_PERSONAL_WX_OFFICIAL_ACCOUNTS(25001, "个人公众号列表无数据"),
	NOT_CHOOSE_WX_OFFICIAL_ACCOUNTS(25002, "没有设置个人账号"),

	// 群空间相关错误码(26001 - 27000)
	GROUP_MANAGER_TRANSFER_FAIL(26001, "群空间管理员转让失败"),
	GROUP_MANAGER_TRANSFER_EA_ERROR(26002, "群空间管理员只能转让给群空间所属的企业员工"),
	GROUP_MEMBER_NOT_FOUND(26003, "找不到该群成员"),
	GROUP_MANAGER_EXIST(26004, "该成员已经为群管理员"),

	FORM_TEMPLATE_NOT_EXIST(30000, "模板不存在"),

	OBJECT_DESCRIPTION_NOT_EXIST(40000, "查询对象详情不存在"),

	// 分销相关错误码(27001 - 28000)
	DISTRIBUTOR_IS_BIND(27001, "该账号已绑定分销员"),
	OPERATOR_NOT_FOUND(27002, "无法查找到该运营人员"),
	DISTRIBUTOR_IS_NOT_FOUND(27003, "分销员不存在"),
	CLUE_SUBMIT_FAIL(27004, "线索提交失败"),
	CLUE_NOT_FOUND(27005, "线索不存在"),
	DISTRIBUTOR_GROUP_NOT_FOUND(27006, "该群不是分销群"),
	CLUE_IS_EXIST(27007, "此线索已由他人报备，如有疑问，请联系对接人进行沟通"),
	GRAY_DISTRIBUTION_REFUSED(27008, "分销模块暂停使用"),
	DISTRIBUTE_PLAN_ONT_FOUND(27009, "未找到分销计划"),
	DISTRIBUTE_TO_BE_PROCESSED(27010, "分销人员正在审核中"),
	USER_WITHOUT_IDENTITY(27011, "用户无身份"),
	DISTRIBUTE_PLAN_NOT_FOUND(27012, "分销计划不存在"),
	SAVE_CRM_LEAD_ERROR(27013, "保存crm线索失败"),
	OPERATOR_IS_NOT_FOUND(27014, "运营人员不存在"),
	DISTRIBUTOR_STATUS_ERROR(27015, "分销员状态错误"),

	// 预设产品相关错误码(28001 - 29000)
	PRODUCT_IS_ENTERPRISE_DEFAULT_FAIL(28001, "预设产品失败"),
	UPDATE_PRODUCT_IS_ENTERPRISE_DEFAULT_FAIL(28002, "更新预设产品失败"),
	NO_DATA_FOR_ENTERPRISE_DEFAULT(28003, "该企业未批量预置企业员工名片"),
	NO_DATA_FOR_ENTERPRISE_PRODUCT(28004, "该企业未批量预置企业产品"),
	CREATE_FOR_DEFAULT_PRODUCT_FAIL(28005, "预设批量预置企业员工名片失败"),
	UPDATE_FOR_DEFAULT_PRODUCT_FAIL(28006, "更新批量预置企业员工名片失败"),
	ENTERPRISE_SETTING_ICON_NOT_EXIST(28007, "公司没有预设企业图标"),

	// 公司信息设置错误码(29001 - 29500)
	SET_ENTERPRISE_INFO_FAILED(70001, "设置公司信息失败"),
	QUERY_ENTERPRISE_INFO_FAILED(70002, "查询公司信息失败"),
	;


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
