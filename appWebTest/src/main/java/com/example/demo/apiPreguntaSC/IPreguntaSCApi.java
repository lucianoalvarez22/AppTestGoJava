package com.example.demo.apiPreguntaSC;

import java.util.List;



public interface IPreguntaSCApi {
	
	List<ApiPreguntaSC> getAllAskSC();
	boolean saveAskFormSC(String p, String op[], String res);
//	List<ApiPreguntaVF> getAllById(Long id);
	ApiPreguntaSC getAllByIdSC(Long id);
	boolean updateAskFormSC(ApiPreguntaSC p);
	boolean deleteByIdSC(Long id); 

}
