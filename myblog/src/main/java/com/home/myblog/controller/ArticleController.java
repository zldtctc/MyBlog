package com.home.myblog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.myblog.entity.Article;
import com.home.myblog.service.IArticleService;
import com.home.myblog.util.JsonResult;
import com.home.myblog.util.StateCode;

@RestController
@RequestMapping("article")
public class ArticleController extends BaseController {
	
	@Autowired
	private IArticleService articleService;
	
	@PostMapping("publish")
	public JsonResult<Void> saveContent(Article article,HttpSession session){
		articleService.saveContent(article, getIdBySession(session),getUsernameBySession(session));
		return new JsonResult<Void>(StateCode.STATE_CODE_OK);
	}
	
	@GetMapping("/")
	public JsonResult<List<Article>> getContentById(HttpSession session){
		List<Article> list = articleService.getContentById(getIdBySession(session));
		return new JsonResult<List<Article>>(StateCode.STATE_CODE_OK,list);
	}
	
	@PostMapping("{articleId}/thumps_up")
	public JsonResult<Integer> clickGood(HttpSession session,@PathVariable("articleId") Integer articleId){
		System.err.println("clickGood-->articleId:"+articleId);
		Integer goodCount = articleService.clickGood(articleId, getIdBySession(session));
		return new JsonResult<Integer>(StateCode.STATE_CODE_OK,goodCount);
	}
	
}
