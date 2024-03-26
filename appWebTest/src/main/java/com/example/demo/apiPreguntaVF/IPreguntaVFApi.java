package com.example.demo.apiPreguntaVF;

import java.util.List;




public interface IPreguntaVFApi {
	
	List<ApiPreguntaVF> getAllAskVF();
	boolean saveAskFormVF(String p, String op[], String res);
//	List<ApiPreguntaVF> getAllById(Long id);
	ApiPreguntaVF getAllByIdVF(Long id);
	boolean updateAskFormVF(ApiPreguntaVF p);
	boolean deleteByIdVF(Long id);
}
