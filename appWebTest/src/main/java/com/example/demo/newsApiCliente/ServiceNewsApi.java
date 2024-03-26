package com.example.demo.newsApiCliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ServiceNewsApi {
	
	@Autowired
	private RestTemplate restTemplateNews; 
	
	@Value("${news.api.key}")
    private String apiKey;

	public List<NewsArticles> getNewsByKeyword(String keyword) {
        String apiUrl = "https://newsapi.org/v2/everything?q=" + keyword + "&apiKey=8fcfa55642eb4c468086bb3284ac0ae9";

       
        String jsonResponse = restTemplateNews.getForObject(apiUrl, String.class);

        // Imprimir el JSON para debug
        System.out.println("JSON Response: " + jsonResponse);

        // Convertir el JSON directamente a una lista de NewsArticle
        ObjectMapper objectMapper = new ObjectMapper();
        List<NewsArticles> articles = new ArrayList<>();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode articlesNode = rootNode.get("articles");

            if (articlesNode != null && articlesNode.isArray()) {
                try {
					articles = objectMapper.readValue(articlesNode.traverse(), new TypeReference<List<NewsArticles>>() {});
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return articles;
    }
}
