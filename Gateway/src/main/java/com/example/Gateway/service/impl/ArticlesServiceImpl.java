package com.example.Gateway.service.impl;

import com.example.Gateway.domain.Response;
import com.example.Gateway.service.ArticlesService;
import org.springframework.stereotype.Service;

@Service
public class ArticlesServiceImpl implements ArticlesService {

    @Override
    public Response getArticles() {
        String[] articles = { "Article 1", "Article 2", "Article 3" };
        return new Response("SUCCESS", 200, articles);
    }

}
