package com.example.demo.apiPreguntaVF;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfigVF {
	
	 	@Bean
	    public RestTemplate restTemplateVF() {
	        return new RestTemplate();
	    }

}
