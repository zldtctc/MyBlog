package com.home.myblog.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.home.myblog.entity.Apply;
import com.home.myblog.entity.Friends;
import com.home.myblog.entity.User;

public interface IFriendsMapper {
	
	/**
	 * 根据昵称搜索用户id
	 * @param nickname
	 * @return
	 */
	User findByNickname(String nickname);
	
	/**
	 * 添加好友申请表数据
	 * @param apply 申请信息的封装
	 * @return
	 */
	Integer insertApply(Apply apply);
	
	/**
	 * 通过好友申请，添加好友关系数据
	 * @param friends
	 * @return
	 */
	Integer insertFriends(Friends friends);
	
	/**
	 * 根据发起者和接收者的id查询申请信息
	 * @param uid 发起者id
	 * @param fid 接收者id
	 * @return
	 */
	Apply findByUidAndFid(@Param("uid") Integer uid,@Param("fid") Integer fid);
	
	/**
	 * 当发起者重复对接收者发起申请时，只需更新申请发起时间即可
	 * @param now 当前时间
	 * @return
	 */
	Integer updateApplyTime(@Param("now")Date now,@Param("id") Integer id);
	
	/**
	 * 根据接收者的id查询申请表中未被接受者处理的信息
	 * @param fid 接收申请者的id
	 * @return
	 */
	List<Apply> findByFid(Integer fid);
	
	/**
	 * 查询两者之间是否好友关系
	 * 根据两者id查找好友表中的信息
	 * @param uid 
	 * @param fid
	 * @return
	 */
	Friends findFriendByUidAndFid(@Param("uid") Integer uid,@Param("fid") Integer fid);
	
	/**
	 * 修改申请表的状态信息，通过apply里的uid和fid
	 * @param apply
	 * @return
	 */
	Integer updateApplyStatus(Apply apply);
}
