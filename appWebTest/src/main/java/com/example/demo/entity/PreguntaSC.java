package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "preguntasc")
public class PreguntaSC extends Pregunta{
	
	@Column(name = "opciones")
	private String[] opciones;
	
	@Column(name = "respuesta_correcta")
	private String respuestaCorrecta;

	public String[] getOpciones() {
		return opciones; 
	}

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}

	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}
	
	
	
}
