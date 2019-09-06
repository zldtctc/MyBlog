package com.home.myblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.home.myblog.service.exception.ArticleNotFoundException;
import com.home.myblog.service.exception.InsertException;
import com.home.myblog.service.exception.PasswordNotMatchException;
import com.home.myblog.service.exception.ServiceException;
import com.home.myblog.service.exception.UpdateException;
import com.home.myblog.service.exception.UploadException;
import com.home.myblog.service.exception.UserNotFoundException;
import com.home.myblog.service.exception.UsernameConflictException;
import com.home.myblog.util.JsonResult;
import com.home.myblog.util.StateCode;

/**
 * 前端控制器基类
 * @author Thinkpad
 *
 */
public class BaseController {
	
	/**
	 * 处理子类控制器所出现的异常
	 */
	@ExceptionHandler(ServiceException.class)
	public JsonResult<Void> handlerException(Exception e){
		JsonResult<Void> jr = new JsonResult<Void>(e);
		if(e instanceof UserNotFoundException) {
			jr.setState(StateCode.STATE_CODE_ERR);
		}else if(e instanceof PasswordNotMatchException) {
			jr.setState(StateCode.STATE_CODE_ERR);
		}else if(e instanceof UsernameConflictException) {
			jr.setState(StateCode.STATE_CODE_ERR);
		}else if(e instanceof InsertException) {
			jr.setState(StateCode.STATE_CODE_SQL);
		}else if(e instanceof UploadException) {
			jr.setState(StateCode.STATE_CODE_SQL);
		}else if(e instanceof UpdateException) {
			jr.setState(StateCode.STATE_CODE_SQL);
		}else if(e instanceof ArticleNotFoundException) {
			jr.setState(StateCode.STATE_CODE_ERR);
		}
		return jr;
	}
	
	public Integer getIdBySession(HttpSession session) {
		Integer id = Integer.valueOf(session.getAttribute("id").toString());
		return id;
	}
	
	public String getUsernameBySession(HttpSession session) {
		String username = session.getAttribute("username").toString();
		return username;
	}
}
