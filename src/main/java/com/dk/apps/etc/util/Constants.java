package com.dk.apps.etc.util;

public class Constants {
	public static final String HOME_URL = "http://cloud.croplandconsulting.com:8678/projectx";
	public static final String WEIXIN_LOGIN_REDIRECT_URL = HOME_URL+"/login/callback.action";
	public static final String WEIXIN_LOGIN_APPID = "wxbd930b17e237dac7";
	public final static String WEIXIN_LOGIN_APPSECRET = "71c661ff53b8b3bef8f29b63e4c92093";
	public static final String WEIXIN_LOGIN_STATE = "";
	public static final String WEIXIN_LOGIN_CSS_FILE = HOME_URL+"/css/wechat-signin.css";
	public static final String USER_LOGIN = "_USER_LOGIN_";
	public static final String ORIGINAL_URL = "_ORIGINAL_URL_";
	public static final String SESSION_ID = "_SESSION_ID_";
	public static final String ERROR_MSG1 = "帐号或密码错误！";
    public static final String ERROR_MSG2 = "帐号为无效帐号！";
    public static final String ERROR_MSG3 = "密码错误！";
    public static final String ERROR_MSG4 = "账号已失效！";
    public static final String ERROR_ACCOUNT_EXISTED = "账号已存在！";
    public static final String ERROR_WEIXIN_LOGIN_FAIL = "登入失败，请重试！";
    public static final String ERROR_NO_EMAIL = "该账号未维护邮箱地址，无法自动重置密码，请联系管理员！";
    public static final String ERROR_EMAIL = "邮箱地址错误！";
    public static final String ERROR_DUPLICATE_ACCOUNT = "操作失败！该账号已存在！";
    public static final String ERROR_WX_NO_OPEN_ID = "无法获取到微信信息，请重试！";
    public static final String ROLE_ADMIN = "管理员";
    
    public static final String SYSTEM_NAME_PROJECTX = "projectx";
    public static final String CREATE_MENU_URL = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=AGENTID";
    public final static String BUTTON_CLICK = "click";
    public final static String BUTTON_VIEW = "view";
    public final static String ALREADY_REGISTERED = "已报名";
    public final static String REGISTERED = "报名";
    public final static String NO_REGISTERED = "未报名";
    public final static String NO_START = "未开始";
    public final static String COMPLETE = "已结束";
    public final static String DOING = "进行中";
    public static final String HTTPS_HOME_URL = "http://localhost:8080//////";
}
