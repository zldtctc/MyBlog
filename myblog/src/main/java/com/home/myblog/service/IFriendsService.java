package com.home.myblog.service;

import java.util.List;

import com.home.myblog.entity.Apply;
import com.home.myblog.entity.User;

public interface IFriendsService {
	
	/**
	 * 搜索指定昵称的用户
	 * @param username
	 * @return
	 */
	User searchName(String nickname);
	
	/**
	 * 发送添加好友请求
	 * @param uid 发起者id
	 * @param fid 接收者id
	 */
	void sendApply(Integer uid,Integer fid);
	
	/**
	 * 通过好友请求
	 * @param uid 发起者id
	 * @param fid 接收者id
	 */
	void addFriends(Integer uid,Integer fid);
	
	/**
	 * 获取指定用户的消息集合
	 * @param fid
	 * @return
	 */
	List<User> getApplys(Integer fid);
}
