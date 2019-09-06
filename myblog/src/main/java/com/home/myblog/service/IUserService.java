package com.home.myblog.service;

import com.home.myblog.entity.User;

public interface IUserService {
	
	//登陆
	User login(String username,String password);
	
	//注册
	void reg(User user);
	
	//修改用户信息
	void modify(User user,Integer id);
	
	//修改用户密码
	void changePssword(String oldPassword,String newPassword,Integer id);
	
	//查找(根据用户名查找用户其他信息)
	User findUserByUsername(String username);
	
	//查找(根据用户id查找用户其他信息)
	User getUserMessageById(Integer id);
	
	//查找（根据用户id查找用户的昵称和个人简介）
	User getTitleMessageById(Integer id);
	
	//更新用户头像
	void refreshAvatar(Integer id ,String avatar);
}
