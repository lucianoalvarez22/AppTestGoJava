package com.example.demo.entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "preguntavf")
public class PreguntaVF extends Pregunta {

	
	@Column(name = "respuesta_correcta")
	private String respuestaCorrecta;
	
	@Column(name = "opciones")
	private String[] opciones; 
	





	public String[] getOpciones() {
		return opciones;
	}
	
	

	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}
	
	
	

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}



	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}



	@Override
	public String toString() {
		return "PreguntaVF [opciones=" + Arrays.toString(opciones) + ", respuestaCorrecta=" + respuestaCorrecta
				+ ", getId()=" + getId() + ", getPregunta()=" + getPregunta() + "]";
	}
	
	
	

	


	
	
	
	
	
	
}
