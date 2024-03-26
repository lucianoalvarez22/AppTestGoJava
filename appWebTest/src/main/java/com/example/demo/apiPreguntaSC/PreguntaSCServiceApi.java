package com.example.demo.apiPreguntaSC;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.apiPreguntaVF.ApiPreguntaVF;

@Service
public class PreguntaSCServiceApi implements IPreguntaSCApi {
	
	@Autowired
	private RestTemplate restTemplateSC;

	@Override
	public List<ApiPreguntaSC> getAllAskSC() {
		String url = "http://localhost:8080/api/preguntasSC";

		ResponseEntity<ApiPreguntaSC[]> responseEntity = restTemplateSC.getForEntity(url, ApiPreguntaSC[].class);
		List<ApiPreguntaSC> listapi = new ArrayList<>();

		System.out.println(responseEntity);
		System.out.println("----------------------------");
		System.out.println(responseEntity.getBody());

		System.out.println("--------------------------");

		if (responseEntity.getStatusCodeValue() == 200) {

			for (ApiPreguntaSC ap : responseEntity.getBody()) {
				System.out.println(ap);
				listapi.add(ap);
			}
		}

		return listapi;
	}
	
	
	

	@Override
	public boolean saveAskFormSC(String p, String[] op, String res) {
		String urlSave = "http://localhost:8080/api/preguntaSC";
		ApiPreguntaSC apSC = new ApiPreguntaSC(p, op, res);
		ResponseEntity<ApiPreguntaSC> responseEntity = restTemplateSC.postForEntity(urlSave, apSC, ApiPreguntaSC.class);

		if (responseEntity.getStatusCodeValue() == 200) {
			return true;
		} else {
			return false;
		}
	}
	
	
	@Override
	public boolean updateAskFormSC(ApiPreguntaSC p) {
		String urlSave = "http://localhost:8080/api/preguntaSC";

		HttpEntity<ApiPreguntaSC> request = new HttpEntity<ApiPreguntaSC>(p);
		ResponseEntity<ApiPreguntaSC> responseEntity = restTemplateSC.exchange(urlSave, HttpMethod.PUT, request,
				ApiPreguntaSC.class); 

		if (responseEntity.getStatusCodeValue() == 200) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ApiPreguntaSC getAllByIdSC(Long id) {
		String urlEdit = "http://localhost:8080/api/preguntaSC/" + id;
		
		
		ResponseEntity<ApiPreguntaSC> responseEntity = restTemplateSC.getForEntity(urlEdit, ApiPreguntaSC.class);
//		ResponseEntity<String> response = restTemplate.exchange(urlEdit, HttpMethod.DELETE, null, String.class);
		System.out.println("-------------------------");

		if (responseEntity.getStatusCodeValue() == 200) {
			// La solicitud fue exitosa, devolver la pregunta
			System.out.println(responseEntity.getBody());
			return responseEntity.getBody();
		} else {

			return null;
		}
	}


	@Override
	public boolean deleteByIdSC(Long id) {
		String urlDelete = "http://localhost:8080/api/preguntaSC/" + id;
		
		ResponseEntity<String> response = restTemplateSC.exchange(urlDelete, HttpMethod.DELETE, null, String.class);
		System.out.println("-------------------------");

		 if (response.getStatusCode().is2xxSuccessful()) {
	            return true;
	        } else {
	           return false;
	        }
	}

}
