package com.home.myblog.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.home.myblog.entity.Article;
import com.home.myblog.entity.User;
import com.home.myblog.mapper.IArticleMapper;
import com.home.myblog.mapper.IUserMapper;
import com.home.myblog.service.IUserService;
import com.home.myblog.service.exception.InsertException;
import com.home.myblog.service.exception.PasswordNotMatchException;
import com.home.myblog.service.exception.UpdateException;
import com.home.myblog.service.exception.UploadException;
import com.home.myblog.service.exception.UserNotFoundException;
import com.home.myblog.service.exception.UsernameConflictException;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserMapper mapper;
	
	
	//根据提供的用户名查找用户其他信息
	@Override
	public User findUserByUsername(String username) {
		User result = mapper.findByUsername(username);
		if(result == null) {
			throw new UserNotFoundException("该用户名不存在");
		}
		return result;
	}
	
	//根据用户id查找用户信息
	public User getUserMessageById(Integer id) {
		User result = mapper.findByUserId(id);
		if(result == null) {
			throw new UserNotFoundException("无此用户");
		}
		result.setIdentity(null);
		result.setSalt(null);
		result.setPassword(null);
		return result;
	}
	
	/**
	 * 获取个人首页头部的信息(头像路径，用户名，个人简介)
	 */
	public User getTitleMessageById(Integer id) {
		User result = mapper.findByUserId(id);
		if(result == null) {
			throw new UserNotFoundException("无此用户");
		}
		//去除一些不必要的信息
		result.setPassword(null);
		result.setSalt(null);
		result.setUsername(null);
		result.setIdentity(null);
		return result;
	}
	
	/**
	 * 登录业务
	 */
	public User login(String username, String password) {
		User result = mapper.findByUsername(username);
		if(result == null) {
			throw new UserNotFoundException("该用户名不存在");
		}
		String salt = result.getSalt();
		String enPssword = encryption(password, salt);
		if(!result.getPassword().equals(enPssword)) {
			throw new PasswordNotMatchException("密码错误");
		}
		return result;
	}
	
	/**
	 * 注册业务
	 */
	public void reg(User user) {
		//插入数之前，先判断用户输入的用户名是否已存在
		if(mapper.findByUsername(user.getUsername()) != null) {
			//不等于null，说明用户名已存在，抛出用户名冲突异常
			throw new UsernameConflictException("'"+user.getUsername()+"'"+"该用户名已存在");
		}
		//没进if语句，说明为null，用户名不存在
		//先对密码进行加密,UUID值作为salt值
		String salt = UUID.randomUUID().toString();
		String password = user.getPassword();
		
		//用加密后的新密码替换用户输入的密码。
		//在盐值不变的情况下，用户输入的密码是
		//注册时用户自己输入的密码，那么就能获得加密后的密码
		user.setPassword(encryption(password, salt));
		//设置盐值
		user.setSalt(salt);
		
		
		//最后插入该条用户数据
		Integer row = mapper.insert(user);
		if(row != 1) {
			//进入if语句，说明数据插入失败，抛出异常
			throw new InsertException("插入数据异常");
		}
	}
	
	/**
	 * 修改用户的密码
	 */
	public void changePssword(String oldPassword,String newPassword, Integer id) {
		//先根据id检查是否存在该用户
		User user = mapper.findByUserId(id);
		if(user == null) {
			throw new UserNotFoundException("未找到该用户");
		}
		//若用户存在，就可以从该用户对象中获取盐值(加密处理用)
		//获取盐值
		String salt = user.getSalt();
		//先判断用户输入的旧密码(加密后),与数据库中的原密码(已经是加密状态)是否一致
		//加密旧密码
		oldPassword = encryption(oldPassword, salt);
		//获取数据库中的原密码
		String password = user.getPassword();
		//比较两者是否一致
		if(!password.equals(oldPassword)) {
			//不一致
			throw new PasswordNotMatchException("旧密码错误");
		}
		//旧密码与原密码一致的情况下,
		//将新密码进行加密处理,获得可以放进数据可以的密码
		String enPassword = encryption(newPassword, salt);
		System.err.println("password: "+enPassword);
		//存储加密后的新密码
		Integer rows = mapper.modifyPasswordById(id, enPassword);
		if(rows != 1) {
			//更新数据失败
			throw new UpdateException("更新数据失败");
		}
	}
	
	/**
	 * 修改用户数据(数据由用户输入)
	 */
	@Override
	public void modify(User user,Integer id) {
		User result = mapper.findByUserId(id);
		if(result == null) {
			throw new UserNotFoundException("无此用户");
		}
		user.setId(id);
		Integer row = mapper.modifyById(user);
		if(row != 1) {
			//进入if语句，说明数据修改失败，抛出异常
			throw new InsertException("修改数据异常");
		}
	}
	
	//更新用户头像
	@Override
	public void refreshAvatar(Integer id, String avatar) {
		//先查询该id用户是否存在
		User result = mapper.findByUserId(id);
		if(result == null) {
			throw new UserNotFoundException("该用户不存在");
		}
		
		//上传头像信息
		Integer rows = mapper.uploadAvatar(id, avatar);
		if(rows != 1) {
			throw new UploadException("更新数据失败");
		}
	}
	
	/**
	 * 密码加密
	 * @param password 用户输入的密码
	 * @param salt 盐值
	 * @return 经过加密后的密码
	 */
	private String encryption(String password,String salt) {
		String str = salt+password+salt;
		for(int i = 0;i<5;i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes());
		}
		return str;
	}
}
