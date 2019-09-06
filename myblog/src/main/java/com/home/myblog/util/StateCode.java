package com.home.myblog.util;

/**
 * 该接口主要作用就是设置常量提供给
 * JsonResult类中state属性
 * @author Thinkpad
 *
 */
public interface StateCode {
	
	//正常，如登陆或注册成功
	public static final int STATE_CODE_OK = 1;
	
	//错误，代表用户输入的数据有误。如登陆时输入的账户或密码不匹配。
	//如注册时用户名重复等等
	public static final int STATE_CODE_ERR=2;
	
	//数据库操作时没生效。如插入，修改失败等。
	public static final int STATE_CODE_SQL=3;
}
