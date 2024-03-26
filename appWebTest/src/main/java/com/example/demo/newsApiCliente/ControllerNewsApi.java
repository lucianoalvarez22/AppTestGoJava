package com.example.demo.newsApiCliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ControllerNewsApi {
	    
	    
	    @Autowired
	    private ServiceNewsApi newsService;

	    @GetMapping("/news/{keyword}")
	    public String getNewsByKeyword(@PathVariable String keyword, Model model) {
	    	keyword = "sports";
	        List<NewsArticles> articlesSerializado = newsService.getNewsByKeyword(keyword);
	        model.addAttribute("sports", "Noticias Sports");
	        model.addAttribute("newsJson", articlesSerializado);
	        return "apinews/new";
	    }
	    

}
