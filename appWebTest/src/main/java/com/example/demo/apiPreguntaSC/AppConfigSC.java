package com.example.demo.apiPreguntaSC;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfigSC {
	
	 	@Bean
	    public RestTemplate restTemplateSC() {
	        return new RestTemplate();
	    }

}
