package com.example.demo.apiPreguntaVF;

import java.util.Arrays;

public class ApiPreguntaVF {
	
	private Long id;
	private String pregunta;
	private String[] opciones;
	private String respuestaCorrecta;
	
	
	
	public ApiPreguntaVF() {
    }
	
	public ApiPreguntaVF(String pregunta, String[] opciones, String respuestaCorrecta) {
		this.pregunta = pregunta;
		this.opciones = opciones;
		this.respuestaCorrecta = respuestaCorrecta;
	}
	
	public ApiPreguntaVF(Long id, String pregunta, String[] opciones, String respuestaCorrecta) {
		this.id = id;
		this.pregunta = pregunta;
		this.opciones = opciones;
		this.respuestaCorrecta = respuestaCorrecta;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
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
	
	
	@Override
	public String toString() {
		return "ApiPreguntaVF [id=" + id + ", pregunta=" + pregunta + ", opciones=" + Arrays.toString(opciones)
				+ ", respuestaCorrecta=" + respuestaCorrecta + "]";
	}
	
	
	
	
	
	
}
