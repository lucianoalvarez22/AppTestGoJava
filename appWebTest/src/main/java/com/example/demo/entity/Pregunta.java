package com.example.demo.entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class Pregunta {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String pregunta;
	

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

	@Override
	public String toString() {
		return "id=" + id + ", pregunta=" + pregunta;
	}

}
