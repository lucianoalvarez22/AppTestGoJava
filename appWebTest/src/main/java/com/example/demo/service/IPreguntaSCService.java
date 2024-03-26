package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.PreguntaSC;

public interface IPreguntaSCService {
	
	List<PreguntaSC> getListadoCompletoSC();
	Optional<PreguntaSC> getPreguntaByIdSC(Long id);
	PreguntaSC getMuchasPreguntasSC();
	void clearSet();
	void clearSetId(Long id);
	void deletePreguntaIDSC(Long id);
	void deletePreguntaSC();
	PreguntaSC guardarPreguntaSC(PreguntaSC pregunta);
	Page<PreguntaSC> listarPaginadoSC(Pageable pageable);
	String[] getOpcionesBarajeadas(PreguntaSC pregunta);

}
