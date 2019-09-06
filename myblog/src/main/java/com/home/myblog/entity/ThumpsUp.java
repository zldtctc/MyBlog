package com.home.myblog.entity;

import java.util.Date;

public class ThumpsUp {

	private Integer articleId;
	private Integer goodId;
	private Date createdTime;

	@Override
	public String toString() {
		return "ThumpsUp [articleId=" + articleId + ", goodId=" + goodId + ", createdTime=" + createdTime + "]";
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
