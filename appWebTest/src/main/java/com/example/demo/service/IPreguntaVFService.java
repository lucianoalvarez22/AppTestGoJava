package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.PreguntaVF;

public interface IPreguntaVFService {

	List<PreguntaVF> getListadoCompletoVF();
	Optional<PreguntaVF> getPreguntaByIdVF(Long id);
	PreguntaVF getMuchasPreguntasVF(); 
	void clearSet();
	void clearSetId(Long id);
	void deletePreguntaIDVF(Long id);
	void deletePreguntaVF();
	PreguntaVF guardarPreguntaVF(PreguntaVF pregunta);
	Page<PreguntaVF> listarPaginadoVF(Pageable pageable);

}
