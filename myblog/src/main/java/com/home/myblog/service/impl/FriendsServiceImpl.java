package com.home.myblog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.myblog.entity.Apply;
import com.home.myblog.entity.Friends;
import com.home.myblog.entity.User;
import com.home.myblog.mapper.IFriendsMapper;
import com.home.myblog.mapper.IUserMapper;
import com.home.myblog.service.IFriendsService;
import com.home.myblog.service.IUserService;
import com.home.myblog.service.exception.ApplyNotFoundException;
import com.home.myblog.service.exception.InsertException;
import com.home.myblog.service.exception.ReadyFriendException;
import com.home.myblog.service.exception.UpdateException;
import com.home.myblog.service.exception.UserNotFoundException;

@Service
public class FriendsServiceImpl implements IFriendsService {
	
	@Autowired
	private IFriendsMapper friendMapper;
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 搜索指定昵称的用户
	 */
	public User searchName(String nickname) {
		//查询是否有指定昵称的用户
		User user = friendMapper.findByNickname(nickname);
		if(user == null) {
			throw new UserNotFoundException("未找到该用户");
		}
		//可以将id,username,nickname,avatar返回，其他信息设为null
		user.setEmail(null);
		user.setIdentity(null);
		user.setPassword(null);
		user.setSalt(null);
		user.setResume(null);
		return user;
	}

	/**
	 * 发送添加好友请求
	 */
	public void sendApply(Integer uid, Integer fid) {
		//先确认两者之间是否已经是好友
		//若已是好友，抛出ReadyFriendException
		Friends friends = friendMapper.findFriendByUidAndFid(uid, fid);
		if(friends != null) {
			//两者已是好友关系
			throw new ReadyFriendException("你们已经是好友,不需要再次申请");
		}
		//再查询uid用户是否已对fid用户发起过请求
		Apply apply = friendMapper.findByUidAndFid(uid, fid);
		//此刻时间
		Date now = new Date();
		if(apply != null) {
			//已存在该条申请，就不要再往表中插入新数据
			//更新申请时间即可
			 Integer rows = friendMapper.updateApplyTime(now, apply.getId());
			 if(rows != 1) {
				 throw new UpdateException("重复申请数据有误");
			 }
			 return;
		}
		//null,未对接收者发起过好友申请
		//补全信息并插入申请信息
		Apply newApply = new Apply();
		newApply.setApplyTime(now);
		newApply.setStatusTime(now);
		newApply.setFid(fid);
		newApply.setUid(uid);
		newApply.setStatus(0);//状态0，该条信息处于未处理状态(未被同意或拒绝)
		Integer rows = friendMapper.insertApply(newApply);
		if(rows != 1) {
			throw new InsertException("好友申请异常");
		}
	}
	
	/**
	 * 通过好友请求
	 * 这里的参数fid为当前用户id，uid为前端页面传来的id参数.
	 * uid也是发起好友申请的用户
	 */
	@Transactional
	public void addFriends(Integer uid, Integer fid) {
		//这里的参数fid为当前用户id，uid为前端页面传来的id参数.
		//uid也是发起好友申请的用户
		
		//先查询好友申请表
		Apply apply = friendMapper.findByUidAndFid(uid, fid);
		if(apply == null) {
			//没有申请信息
			throw new ApplyNotFoundException("未找到该好友申请信息");
		}
		//找到好友申请的信息，对该条信息的status状态改为1(通过申请)
		//并修改通过申请的时间
		apply.setStatus(1);
		apply.setStatusTime(new Date());
		//然后持久化
		Integer rows = friendMapper.updateApplyStatus(apply);
		if(rows != 1) {
			throw new UpdateException("通过好友信息异常");
		}
		//调换角色，如果当前用户也对另一个用户发起过好友申请的话，
		//也应该将这条申请信息的状态改为通过。
		//所以，将uid作为fid,将fid作为uid，再次查找好友申请表
		Apply otherApply = friendMapper.findByUidAndFid(fid, uid);
		if(otherApply != null) {
			//说明也有申请信息，再次进行修改并持久化
			//若为null的话，就不用进行其他处理
			otherApply.setStatus(1);
			otherApply.setStatusTime(new Date());
			rows = friendMapper.updateApplyStatus(otherApply);
			if(rows != 1) {
				throw new UpdateException("通过好友信息异常");
			}
		}
		
		//通过申请状态修改，接下来进行对好友表的插入操作。
		//也是两次插入数据
		
		//将信息封装到Friends类
		Friends friends = new Friends();
		friends.setUid(uid);
		friends.setFid(fid);
		//当前时间
		Date now = new Date();
		friends.setCreatedTime(now);
		//进行添加好友操作
		rows = friendMapper.insertFriends(friends);
		if(rows != 1) {
			throw new InsertException("添加好友信息异常");
		}
		//对于uid的用户来说,fid是他的好友
		//但是对于fid来说,uid也是他的好友。
		//所以当通过两者之间的好友申请，应当添加两条数据
		//1.当前用户为uid,另一个用户为fid，添加进好友表
		//2.另一个用户作为uid,当前用户作为fid,也添加进好友表
		//对friends重新设置
		friends.setUid(fid);
		friends.setFid(uid);
		//时间相同，不用重新设置.
		//friends里的id因之前的信息插入，已将id值返回并赋值，所以需要重新设置为null
		friends.setId(null);
		//再次进行好友添加操作
		rows = friendMapper.insertFriends(friends);
		if(rows != 1) {
			throw new InsertException("通过好友信息异常");
		}
	}
	
	/**
	 * 获取指定用户的未被处理的消息集合
	 */
	public List<User> getApplys(Integer fid) {
		//获取指定用户的未被处理的消息集合
		List<Apply> list = friendMapper.findByFid(fid);
		//需要返回的用户信息结合
		List<User> userList = new ArrayList<User>();
		//根据Apply中的uid(申请者id),获取该uid的信息
		if(list.size()>0) {
			for (Apply apply : list) {
				Integer uid = apply.getUid();
				User user = userService.getUserMessageById(uid);
				if(user != null) {
					userList.add(user);
				}
			}
		}
		return userList;
	}
}
