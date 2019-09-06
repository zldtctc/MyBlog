package com.home.myblog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.myblog.entity.Article;
import com.home.myblog.entity.ThumpsUp;
import com.home.myblog.mapper.IArticleMapper;
import com.home.myblog.service.IArticleService;
import com.home.myblog.service.exception.AlreadyUpException;
import com.home.myblog.service.exception.ArticleNotFoundException;
import com.home.myblog.service.exception.InsertException;
import com.home.myblog.service.exception.UpdateException;

@Service
public class ArticleServiceImpl implements IArticleService {
	
	@Autowired
	private IArticleMapper mapper;
	
	@Override
	public void saveContent(Article article,Integer userId,String username) {
		//补全信息
		//发布信息的用户id
		article.setUserId(userId);
		//补全发布与修改信息
		Date date = new Date();
		article.setArticlePublishTime(date);
		article.setArticleModifyTime(date);
		//补全阅读，转发，评论数量
		article.setArticleGoodAmount(0);
		article.setArticleCommentAmount(0);
		article.setArticleTransmitAmount(0);
		//补全标题
		article.setArticleTitle(username+"发布");
		//持久化数据
		Integer row = mapper.publish(article);
		if(row != 1) {
			throw new InsertException("插入数据异常");
		}
		
	}
	
	/**
	 * 查找用户发布的信息
	 */
	public List<Article> getContentById(Integer userId) {
		List<Article> list = mapper.findContextByUserId(userId, 0, 10);
		System.err.println("ArticleServiceImpl-->getContentById方法,测试LIST长度:"+list.size());
		return list;
	}
	
	/**
	 * 点赞
	 */
	@Transactional
	public Integer clickGood(Integer articleId, Integer userId) {
		//先检查被点赞的文章是否存在
		Article article = mapper.findArticleByArticleId(articleId);
		if(article == null) {
			throw new ArticleNotFoundException("找不到该文章");
		}
		//检查点赞的用户是否已经点过当前文件的赞
		ThumpsUp thumpsUp = mapper.findThumpsUp(articleId, userId);
		if(thumpsUp != null) {
			throw new AlreadyUpException("不可重复点赞");
		}
		//将点赞数据持久化
		thumpsUp = new ThumpsUp();
		thumpsUp.setArticleId(articleId);
		thumpsUp.setGoodId(userId);
		thumpsUp.setCreatedTime(new Date());
		Integer rows = mapper.insertThumpsUp(thumpsUp);
		if(rows != 1) {
			throw new InsertException("插入数据异常");
		}
		//执行点赞sql
		rows = mapper.thumpsUpById(articleId);
		if(rows != 1) {
			throw new UpdateException("点赞数据更新异常");
		}
		//将文章点赞数加1后返回
		Integer num = article.getArticleGoodAmount()+1;
		return num;
	}
	
	/**
	 * 根据当前用户的id，查找当前用户好友的文章
	 * 分页，每页选取10条
	 */
	public List<Article> getFrindsArticles(Integer uid,Integer pass) {
		//根据前端传来的页数参数，来决定limt需要跳过多少条信息
		Integer show = 4;//选取4条
		//limit page*show,show
		pass = (pass-1)*show;
		List<Article> articles = mapper.findFriendsArticle(uid,pass,show);
		
		return articles;
	}
	
}
