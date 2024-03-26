package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PreguntaSC;
import com.example.demo.entity.PreguntaVF;
import com.example.demo.service.IPreguntaSCService;
import com.example.demo.service.IPreguntaVFService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class ApiRestPreguntasController {
	
	@Autowired
	private IPreguntaVFService preguntaVFServ; 
	
	@Autowired
	private IPreguntaSCService preguntaSCServ;
	
	@Operation(summary = "Listar TODAS las preguntas del TEST verdadero y falso")
		@GetMapping("/preguntasVF")
		public List <PreguntaVF> listarPreguntasRestVF(Model model){
			
			return preguntaVFServ.getListadoCompletoVF();
		}
		
		@Operation(summary = "Listar TODAS las preguntas del TEST SingleChoice")
		@GetMapping("/preguntasSC")
		public List <PreguntaSC> listarPreguntasRestSC(Model model){
			
			return preguntaSCServ.getListadoCompletoSC();
		}
		
		@Operation(summary = "Listar preguntas del TEST verdadero y falso buscando por ID")
		@GetMapping("/preguntaVF/{id}")
		public Optional<PreguntaVF> listaRestIdVF(@PathVariable (name = "id") Long idPregunta){
			
			return preguntaVFServ.getPreguntaByIdVF(idPregunta);     
			
		}
		
		@Operation(summary = "Listar preguntas del TEST SingleChoice buscando por ID")
		@GetMapping("/preguntaSC/{id}")
		public Optional<PreguntaSC> listaRestIdSC(@PathVariable (name = "id") Long idPregunta){
			
			return preguntaSCServ.getPreguntaByIdSC(idPregunta);     
			
		}
		
		
		//BORRAR PREGUNTA CON UN ID
		
		@Operation(summary = "Borrar preguntas verdadero y falso buscando por ID")
		@DeleteMapping("/preguntaVF/{id}") 
		public void deletePreguntaIDVF(@PathVariable Long id) {
			preguntaVFServ.deletePreguntaIDVF(id);
			
		}
		
		@Operation(summary = "Borrar preguntas SingleChoice buscando por ID")
		@DeleteMapping("/preguntaSC/{id}") 
		public void deletePreguntaIDSC(@PathVariable Long id) {
			preguntaSCServ.deletePreguntaIDSC(id);
			
		}
		
		
		//HACER METODO PARA BORRAR TODAS LAS PREGUNTAS
		
		
		
		//PARA GUARDAR NO HACE FALTA EN POSTMAN PONER EL ID
		
		@Operation(summary = "Guardar pregunta nueva en verdadero y falso ")
		@PostMapping("/preguntaVF") 
		public PreguntaVF guardarPreguntaVF(@RequestBody PreguntaVF pregunta) {
			return preguntaVFServ.guardarPreguntaVF(pregunta); 
		}
		
		@Operation(summary = "Guardar pregunta nueva en SingleChoice")
		@PostMapping("/preguntaSC") 
		public PreguntaSC guardarPreguntaSC(@RequestBody PreguntaSC pregunta) {
			return preguntaSCServ.guardarPreguntaSC(pregunta); 
		}
		
		
		
		
		
		
		//PARA ACTUALIZAR ES NECESARIO PONER EL ID
		
		@Operation(summary = "Actualizar pregunta en verdadero y falso")
		@PutMapping("/preguntaVF")
		public PreguntaVF actualizarPreguntaVF(@RequestBody PreguntaVF pregunta) {
			return preguntaVFServ.guardarPreguntaVF(pregunta);  
		}
		
		@Operation(summary = "Actualizar pregunta en SingleChoice")
		@PutMapping("/preguntaSC")
		public PreguntaSC actualizarPreguntaSC(@RequestBody PreguntaSC pregunta) {
			return preguntaSCServ.guardarPreguntaSC(pregunta);
		}
	

}
