package com.example.Gateway.controller;

import com.example.Gateway.domain.Response;
import org.springframework.http.ResponseEntity;

public interface ArticlesController {

    ResponseEntity<Response> getArticles();

}
