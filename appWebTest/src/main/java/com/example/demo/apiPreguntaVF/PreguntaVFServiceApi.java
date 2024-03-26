package com.example.demo.apiPreguntaVF;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PreguntaVFServiceApi implements IPreguntaVFApi {

	@Autowired
	private RestTemplate restTemplateVF;

	@Override
	public List<ApiPreguntaVF> getAllAskVF() {
		String url = "http://localhost:8080/api/preguntasVF";

		ResponseEntity<ApiPreguntaVF[]> responseEntity = restTemplateVF.getForEntity(url, ApiPreguntaVF[].class);
		List<ApiPreguntaVF> listapi = new ArrayList<>();

		System.out.println(responseEntity);
		System.out.println("----------------------------");
		System.out.println(responseEntity.getBody());

		System.out.println("--------------------------");

		if (responseEntity.getStatusCodeValue() == 200) {

			for (ApiPreguntaVF ap : responseEntity.getBody()) {
				System.out.println(ap);
				listapi.add(ap);
			}
		}

		return listapi;

	}

	@Override
	public boolean saveAskFormVF(String p, String[] op, String res) {
		String urlSave = "http://localhost:8080/api/preguntaVF";
		ApiPreguntaVF ap = new ApiPreguntaVF(p, op, res);
		ResponseEntity<ApiPreguntaVF> responseEntity = restTemplateVF.postForEntity(urlSave, ap, ApiPreguntaVF.class);

		if (responseEntity.getStatusCodeValue() == 200) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean updateAskFormVF(ApiPreguntaVF p) {
		String urlSave = "http://localhost:8080/api/preguntaVF";

		HttpEntity<ApiPreguntaVF> request = new HttpEntity<ApiPreguntaVF>(p);
		ResponseEntity<ApiPreguntaVF> responseEntity = restTemplateVF.exchange(urlSave, HttpMethod.PUT, request,
				ApiPreguntaVF.class);

		if (responseEntity.getStatusCodeValue() == 200) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public ApiPreguntaVF getAllByIdVF(Long id) {
		String urlEdit = "http://localhost:8080/api/preguntaVF/" + id;
		
		
		ResponseEntity<ApiPreguntaVF> responseEntity = restTemplateVF.getForEntity(urlEdit, ApiPreguntaVF.class);
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
	public boolean deleteByIdVF(Long id) {
		String urlDelete = "http://localhost:8080/api/preguntaVF/" + id;
		
		ResponseEntity<String> response = restTemplateVF.exchange(urlDelete, HttpMethod.DELETE, null, String.class);
		System.out.println("-------------------------");

		 if (response.getStatusCode().is2xxSuccessful()) {
	            return true;
	        } else {
	           return false;
	        }
	    }


//	@Override
//	public List<ApiPreguntaVF> getAllById(Long id) {
//	    String urlEdit = "http://localhost:8080/api/pregunta/" + id;
//	    ResponseEntity<ApiPreguntaVF> responseEntity = restTemplate.getForEntity(urlEdit, ApiPreguntaVF.class);
//
//	    System.out.println("-------------------------");
//
//	    List<ApiPreguntaVF> resultList = new ArrayList<>();
//
//	    if (responseEntity.getStatusCodeValue() == 200) {
//	        // La solicitud fue exitosa, agregar la pregunta a la lista
//	        System.out.println(responseEntity.getBody());
//	        resultList.add(responseEntity.getBody());
//	    }
//
//	    // Devolver la lista, que puede contener la pregunta o estar vacía
//	    return resultList;
//	}

}
