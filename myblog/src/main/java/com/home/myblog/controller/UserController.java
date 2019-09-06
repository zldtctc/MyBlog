package com.home.myblog.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.home.myblog.entity.Article;
import com.home.myblog.entity.User;
import com.home.myblog.intercepors.SessionMapping;
import com.home.myblog.service.IUserService;
import com.home.myblog.util.JsonResult;
import com.home.myblog.util.StateCode;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {

	@Autowired
	private IUserService service;
	
	//登录请求
	@PostMapping("login")
	public JsonResult<Void> login(String username, String password, HttpSession session) {
		User result = service.login(username, password);
		String id = "id"+result.getId();
		session.setAttribute("id", result.getId());
		session.setAttribute("username", result.getUsername());
		return new JsonResult<Void>(StateCode.STATE_CODE_OK);
	}
	
	//注册请求
	@RequestMapping("reg")
	public JsonResult<Void> reg(User user){
		service.reg(user);
		JsonResult<Void> jr = new JsonResult<Void>(StateCode.STATE_CODE_OK);	
		return jr;
	}
	
	//修改用户密码(控制器层)
	@RequestMapping("change-password")
	public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
		Integer id = getIdBySession(session);
		service.changePssword(oldPassword, newPassword, id);
		return new JsonResult<Void>(StateCode.STATE_CODE_OK);
	}
	
	//展示用户的个别信息
	@RequestMapping("showMsg")
	public JsonResult<User> showMsg(HttpSession session) {
		//从Session对象中获取用户id
		Integer id = getIdBySession(session);
		//根据id查找是否存在该用户
		User user = service.getUserMessageById(id);
		return new JsonResult<User>(StateCode.STATE_CODE_OK,user);
	}
	
	//获取用户的昵称和个人简介
	@RequestMapping("title-message")
	public JsonResult<User> getTitleMessage(HttpSession session){
		System.err.println("getTitleMessage()");
		Integer id = getIdBySession(session);
		User user = service.getTitleMessageById(id);
		return new JsonResult<User>(StateCode.STATE_CODE_OK,user);
	}
	
	//修改用户信息
	@RequestMapping("modifyMsg")
	public JsonResult<User> modifyMsg(User user,HttpSession session){
		//从session中获取用户信息,判断是否仍处于登录状态。
		Integer id = getIdBySession(session);
		service.modify(user, id);
		return new JsonResult<User>(StateCode.STATE_CODE_OK,user);
	}
	
	//上传用户发布的信息
	/*
	 * @RequestMapping("publish") public JsonResult<Void> publishMsg(Article
	 * article,HttpSession session){ JsonResult<Void> jr = new JsonResult<Void>();
	 * service.publish(article,getIdBySession(session));
	 * jr.setState(StateCode.STATE_CODE_OK);
	 * 
	 * return jr; }
	 */
	
	//上传头像文件请求
	@PostMapping("change_avatar")
	public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file,HttpSession session){
		System.err.println( file.getOriginalFilename());
		//判断上传的文件是否为空
		if(file.isEmpty()) {
			//说明该文件已被删除或移动，否则就是空文件
			System.err.println("文件为空");
		}
		
		//获取文件全名
		String originalName = file.getOriginalFilename();
		//获取文件全名里的后缀名
		int index = originalName.lastIndexOf(".");
		String suffix ="";
		if(index>0) {
			suffix = originalName.substring(index);
		}
		//文件的全名
		String filename = UUID.randomUUID()+suffix;
		String username = getUsernameBySession(session);
		//获取文件存储路径
		String path = session.getServletContext().getRealPath("avatar")+"\\"+username;
		System.err.println(path);
		File dirpath = new File(path);
		if(!dirpath.exists()) {
			//若该文件不存在，则创建一个
			dirpath.mkdirs();
		}
		
		//上传文件
		File dest = new File(dirpath,filename);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String avatar = "avatar\\"+username+"\\"+filename;
		System.err.println("avatar:"+avatar);
		Integer id = getIdBySession(session);
		//该文件的存储路径
		service.refreshAvatar(id, avatar);
		return new JsonResult<String>(StateCode.STATE_CODE_OK,avatar);
	}
}











