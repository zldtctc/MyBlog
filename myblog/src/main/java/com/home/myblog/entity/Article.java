package com.home.myblog.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {

	private Integer articleId;// 文章ID
	private String articleTitle;// 文章标题
	private String articleText;// 文章正文
	private Date articlePublishTime;// 发布时间
	private Date articleModifyTime;// 修改时间
	private Integer articleGoodAmount;// 点赞数量
	private Integer articleTransmitAmount;// 转发数量
	private Integer articleCommentAmount;// 评论数量
	private Integer userId;// 发布该信息的用户id

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", articleTitle=" + articleTitle + ", articleText=" + articleText
				+ ", articlePublishTime=" + articlePublishTime + ", articleModifyTime=" + articleModifyTime
				+ ", articleGoodAmount=" + articleGoodAmount + ", articleTransmitAmount=" + articleTransmitAmount
				+ ", articleCommentAmount=" + articleCommentAmount + ", userId=" + userId + "]";
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleText() {
		return articleText;
	}

	public void setArticleText(String articleText) {
		this.articleText = articleText;
	}

	public Date getArticlePublishTime() {
		return articlePublishTime;
	}

	public void setArticlePublishTime(Date articlePublishTime) {
		this.articlePublishTime = articlePublishTime;
	}

	public Date getArticleModifyTime() {
		return articleModifyTime;
	}

	public void setArticleModifyTime(Date articleModifyTime) {
		this.articleModifyTime = articleModifyTime;
	}

	public Integer getArticleGoodAmount() {
		return articleGoodAmount;
	}

	public void setArticleGoodAmount(Integer articleGoodAmount) {
		this.articleGoodAmount = articleGoodAmount;
	}

	public Integer getArticleTransmitAmount() {
		return articleTransmitAmount;
	}

	public void setArticleTransmitAmount(Integer articleTransmitAmount) {
		this.articleTransmitAmount = articleTransmitAmount;
	}

	public Integer getArticleCommentAmount() {
		return articleCommentAmount;
	}

	public void setArticleCommentAmount(Integer articleCommentAmount) {
		this.articleCommentAmount = articleCommentAmount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
