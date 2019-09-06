package com.home.myblog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.myblog.entity.User;
import com.home.myblog.service.IFriendsService;
import com.home.myblog.util.JsonResult;
import com.home.myblog.util.StateCode;

@RestController
@RequestMapping("friends")
public class FriendsController extends BaseController {
	
	@Autowired
	private IFriendsService service;
	
	@RequestMapping("s-friends")
	public JsonResult<User> searchName(String nickname){
		User user = service.searchName(nickname);
		return new JsonResult<User>(StateCode.STATE_CODE_OK,user);
	}
	
	@RequestMapping("add-friends")
	public JsonResult<Void> sendApply(HttpSession session,Integer fid){
		service.sendApply(getIdBySession(session), fid);
		return new JsonResult<Void>(StateCode.STATE_CODE_OK) ;
	}
	
	@RequestMapping("get-applys")
	public JsonResult<List<User>> getApplys(HttpSession session){
		List<User> list = service.getApplys(getIdBySession(session));
		return new JsonResult<List<User>>(StateCode.STATE_CODE_OK,list);
	}
	
	@RequestMapping("agree-applys")
	public JsonResult<Void> agreeFriendsApply(HttpSession session,Integer uid){
		Integer fid =getIdBySession(session);
		service.addFriends(uid, fid);
		return new JsonResult<Void>(StateCode.STATE_CODE_OK);
	}
}
