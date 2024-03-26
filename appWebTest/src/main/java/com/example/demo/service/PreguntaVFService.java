package com.example.demo.service;

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

import com.example.demo.entity.PreguntaVF;
import com.example.demo.repository.IPreguntaVFDAO;


@Service
public class PreguntaVFService implements IPreguntaVFService {
	
	@Autowired 
	private IPreguntaVFDAO preguntaVFDao; 
	
	Random random = new Random();
	Set<Long> idPreguntasGuardadasVF = new HashSet<Long>();
	

	@Override
	public List<PreguntaVF> getListadoCompletoVF() {	
		return (List<PreguntaVF>) preguntaVFDao.findAll(); 
	}
	
	
	@Override
	public Optional<PreguntaVF> getPreguntaByIdVF(Long id){
		return preguntaVFDao.findById(id);  
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<PreguntaVF> listarPaginadoVF(Pageable pageable) {
		return preguntaVFDao.findAll(pageable);
	}
	
	//BORRANDO PREGUNTAS DE BBDD POR ID
	@Override
	public void deletePreguntaIDVF(Long id) {
		preguntaVFDao.deleteById(id);
	}
	
	//BORRAR TODAS LAS PREGUNTAS DE LA BBDD 
	public void deletePreguntaVF() {
		preguntaVFDao.deleteAll();
	}
	
	//METODO PARA GUARDAR Y ACTUALIZAR PREGUNTA
	@Override
	public PreguntaVF guardarPreguntaVF(PreguntaVF pregunta) {
		return preguntaVFDao.save(pregunta); 
	}
	
	//LIMPIANDO SET
	@Override
	public void clearSet() {
		idPreguntasGuardadasVF.clear();
	}
	
	//LIMPIANDO PREGUNTA DE SET POR ID
	@Override
	public void clearSetId(Long id) {
		idPreguntasGuardadasVF.remove(id);
	}
	
	
	@Override
	public PreguntaVF getMuchasPreguntasVF(){
		
		//Obtengo la lista de preguntas
		List<PreguntaVF> listaVF = getListadoCompletoVF();
		
		System.out.println("TAMAÑO LISTA: " + listaVF.size());
		System.out.println("TAMAÑO SET IDPREGUN: " + idPreguntasGuardadasVF.size());
		
		if(listaVF.size() == 0) {
			PreguntaVF pNull = new PreguntaVF();
			pNull.setId((long) 0);
			return pNull;
		}
		
		if(idPreguntasGuardadasVF.size() == listaVF.size()) {
			PreguntaVF p = new PreguntaVF();
			p.setId((long) -1);
			clearSet();
			return p;
		}
		
		PreguntaVF preguntaVF; 
		//Saco una pregunta
		preguntaVF = listaVF.get(random.nextInt(listaVF.size()));
		
		//Guarda el ID de la pregunta en una variable
		Long preguntaVFId = preguntaVF.getId();
		
		//Mientras el ID exista dentro del set, sacame otra pregunta para volver a comprobar lo mismo
		while(idPreguntasGuardadasVF.contains(preguntaVFId)) {
			preguntaVF = listaVF.get(random.nextInt(listaVF.size())); 
			preguntaVFId = preguntaVF.getId();
			
		}
		
		//Se sale del bucle cuando el ID NO existe en el set
		//Para posteriormente actualizar el set y añadirlo
		idPreguntasGuardadasVF.add(preguntaVFId);
		
		
		return preguntaVF; 
		
	}
}
