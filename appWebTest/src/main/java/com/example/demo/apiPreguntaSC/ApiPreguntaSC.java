package com.example.demo.apiPreguntaSC;


public class ApiPreguntaSC {
	
	private Long id;
	private String pregunta;
	private String opciones[];
	private String respuestaCorrecta;
	
	
	public ApiPreguntaSC(Long id, String pregunta, String[] opciones, String respuestaCorrecta) {
		this.id = id;
		this.pregunta = pregunta;
		this.opciones = opciones;
		this.respuestaCorrecta = respuestaCorrecta;
	}
	
	
	public ApiPreguntaSC(String pregunta, String opciones[], String respuestaCorrecta) {
		this.pregunta = pregunta;
		this.opciones = opciones;
		this.respuestaCorrecta = respuestaCorrecta;
	}
	
	
	public ApiPreguntaSC() {
		
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
	
	
	
	

}
