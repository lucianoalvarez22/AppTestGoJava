package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PreguntaSC;
import com.example.demo.repository.IPreguntaSCDAO;

@Service
public class PreguntaSCService implements IPreguntaSCService { 
	
	@Autowired
	private IPreguntaSCDAO preguntaSCDao;
	
	Random random = new Random();
	Set<Long> idPreguntasGuardadasSC = new HashSet<Long>(); 

	@Override
	public List<PreguntaSC> getListadoCompletoSC() {
		return (List<PreguntaSC>) preguntaSCDao.findAll();
	}

	@Override
	public Optional<PreguntaSC> getPreguntaByIdSC(Long id) {
		return preguntaSCDao.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<PreguntaSC> listarPaginadoSC(Pageable pageable) {
		return preguntaSCDao.findAll(pageable);
	}
	
	//BORRANDO PREGUNTAS DE BBDD POR ID
		@Override
		public void deletePreguntaIDSC(Long id) {
			preguntaSCDao.deleteById(id);
		}
		
		//BORRAR TODAS LAS PREGUNTAS DE LA BBDD 
		public void deletePreguntaSC() {
			preguntaSCDao.deleteAll(); 
		}
		
		//METODO PARA GUARDAR Y ACTUALIZAR PREGUNTA
		@Override
		public PreguntaSC guardarPreguntaSC(PreguntaSC pregunta) {
			return preguntaSCDao.save(pregunta); 
		}
		
		@Override
		public void clearSet() {
			idPreguntasGuardadasSC.clear();
		}

		@Override
		public void clearSetId(Long id) {
			idPreguntasGuardadasSC.remove(id);	
		}
	
	//METODO OPCIONES DE LAS PREGUNTAS BARAJEADAS
	@Override
	public String[] getOpcionesBarajeadas(PreguntaSC pregunta){
		String[] opciones = pregunta.getOpciones();
		
		 List<String> listaOpcionesStrings = new ArrayList<>(Arrays.asList(opciones));
		 Collections.shuffle(listaOpcionesStrings);
		 
		 String[] opcionesBarajadas = listaOpcionesStrings.toArray(new String[0]);
		 
		 return opcionesBarajadas;
		
	}

	@Override
	public PreguntaSC getMuchasPreguntasSC() {
		
		List<PreguntaSC> listaSC = getListadoCompletoSC();
		
		//PROBANDO
		System.out.println("TAMAÑO LISTA SC: " + listaSC.size());
		System.out.println("TAMAÑO SET SC ID: " + idPreguntasGuardadasSC.size());
		
		if(listaSC.size() == 0) {
			PreguntaSC pNull = new PreguntaSC();
			pNull.setId((long) 0);
			return pNull;
		}
		
		if(listaSC.size() == idPreguntasGuardadasSC.size()) {
			PreguntaSC psc = new PreguntaSC();
			psc.setId((long) -1);
			clearSet();
			return psc;
		}
		 
		PreguntaSC preguntaSC;
		preguntaSC = listaSC.get(random.nextInt(listaSC.size()));
		preguntaSC.setOpciones(getOpcionesBarajeadas(preguntaSC));
		
		
		
		Long preguntaSCId = preguntaSC.getId();
		
		while(idPreguntasGuardadasSC.contains(preguntaSCId)) {
			preguntaSC = listaSC.get(random.nextInt(listaSC.size()));
			preguntaSCId = preguntaSC.getId();
		}
		
		idPreguntasGuardadasSC.add(preguntaSCId);
		
		return preguntaSC;
	}

	

}
