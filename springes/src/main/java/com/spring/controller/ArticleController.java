package com.spring.controller;

import com.spring.domain.Article;
import com.spring.repository.ArticleRepository;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("save")
    public Object save(){
        Article article=new Article();
        article.setId(1L);
        article.setPv(123);
        article.setContent("SpringBoot整合ES");
        article.setTitle("ES SpringBoot");
        article.setSummary("SpringBooe整合ES开发");
        articleRepository.save(article);
        return "200";
    }

    @GetMapping("search")
    public Object search(String title){
//        MatchAllQueryBuilder queryBuilder= QueryBuilders.matchAllQuery();   //搜素全部文档
        MatchQueryBuilder queryBuilder=QueryBuilders.matchQuery("title",title);
        Iterable<Article> list=articleRepository.search(queryBuilder);
        return list;
    }
}
