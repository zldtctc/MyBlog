package com.home.myblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.home.myblog.entity.Article;
import com.home.myblog.entity.ThumpsUp;

public interface IArticleMapper {
	
	/**
	 * 保存用户发布的信息
	 * @param article 信息的封装
	 * @return 有效行数
	 */
	Integer publish(Article article);
	
	/**
	 * 查找用户发布的信息
	 * @param userId 当前用户id
	 * @param skip 跳过的条数
	 * @param quantity 显示的条数
	 * @return 返回前10条用户发布的动态信息(最后一页不足十条，根据剩余数显示)
	 */
	List<Article> findContextByUserId(Integer userId,Integer skip,Integer quantity);
	
	/**
	 * 点赞数量增+1
	 * @param articleId 需要赠加点赞数量的文章id
	 * @return 有效行数
	 */
	Integer thumpsUpById(Integer articleId);
	
	/**
	 * 对另一张表article_good进行插入操作
	 * @param thumpsUp 点赞信息的封装类，包含文章id，点赞用户id，点赞时间
	 * @return
	 */
	Integer insertThumpsUp(ThumpsUp thumpsUp);
	
	/**
	 * 对另一张表article_good进行操作，查询当前用户是否对当前文章点过赞
	 * @param articleId 当前文章id
	 * @param goodId 当前点赞用户id
	 * @return
	 */
	ThumpsUp findThumpsUp(@Param("articleId") Integer articleId,@Param("goodId") Integer goodId);
	/**
	 * 查找指定文章id的文章数据
	 * @param articleId 指定的文章id
	 * @return 返回整个文章数据，否则为null
	 */
	Article findArticleByArticleId(Integer articleId);
	
	/**
	 * 找到当前用户好友的文章
	 * @return
	 */
	List<Article> findFriendsArticle(@Param("uid") Integer uid,@Param("pass") Integer pass,@Param("show") Integer show);
}
