package com.home.myblog.mapper;

import org.apache.ibatis.annotations.Param;

import com.home.myblog.entity.User;

public interface IUserMapper {
	
	/**
	 * 登陆操作，根据用户名查询用户数据
	 * @param username 用户输入的用户名
	 * @return 返回用户数据
	 */
	User findByUsername(String username);
	
	/**
	 * 根据用户ID查找数据
	 */
	User findByUserId(Integer id);
	
	/**
	 * 注册操作
	 * @param user 用户输入的各种注册信息
	 * @return	有效行数，1为成功插入数据
	 */
	Integer insert(User user);
	
	/**
	 * 修改数据
	 * @param user 用户输入的修改信息
	 * @return 有效行数，1为成功修改数据
	 */
	Integer modifyById(User user);
	
	/**
	 * 上传头像
	 * @param id 当前用户id
	 * @param avatar 头像的存储路径
	 * @return
	 */
	Integer uploadAvatar(@Param("id") Integer id,@Param("avatar") String avatar);
	
	/**
	 *  修改用户密码
	 * @param id 当前修改密码业务的用户id
	 * @param password 新密码
	 * @return 有效行数
	 */
	Integer modifyPasswordById(@Param("id") Integer id,@Param("password") String password);
}
