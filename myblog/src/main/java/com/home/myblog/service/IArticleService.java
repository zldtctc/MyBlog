package com.home.myblog.service;

import java.util.List;

import com.home.myblog.entity.Article;

/**
 * 关于用户发布的动态信息的业务接口
 * @author Thinkpad
 *
 */
public interface IArticleService {
	
	/**
	 * 保存用户发布信息的业务
	 * @param article
	 */
	void saveContent(Article article,Integer userId,String username);
	
	/**
	 * 查找用户发布的信息
	 * @param id 当前用户的id
	 * @return
	 */
	List<Article> getContentById(Integer userId);
	
	/**
	 * 点赞
	 * @param articleId 被点赞的文章id
	 * @param userId 文章所属的用户id
	 */
	Integer clickGood(Integer articleId,Integer userId);
	
	/**
	 * 根据当前用户的id，查找当前用户好友的文章
	 * @param uid
	 * @return
	 */
	List<Article> getFrindsArticles(Integer uid,Integer pass);
}
