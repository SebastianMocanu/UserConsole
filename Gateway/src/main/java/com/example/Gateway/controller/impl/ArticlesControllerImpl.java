package com.example.Gateway.controller.impl;

import com.example.Gateway.controller.ArticlesController;
import com.example.Gateway.domain.Response;
import com.example.Gateway.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
public class ArticlesControllerImpl implements ArticlesController {

    @Autowired
    private ArticlesService articlesService;

    @Autowired
    private WebClient webClient;

    @GetMapping("/articles")
    public ResponseEntity<Response> getArticles() {
        return ResponseEntity.status(200).body(new Response("SUCCESS", 200, articlesService.getArticles()));
    }

    @GetMapping(value = "/articles")
    public String[] getArticles(
            @RegisteredOAuth2AuthorizedClient("articles-client-authorization-code") OAuth2AuthorizedClient authorizedClient
    ) {
        return this.webClient
                .get()
                .uri("http://127.0.0.1:8090/articles")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
    }

}
