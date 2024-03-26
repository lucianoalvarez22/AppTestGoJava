package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mododark")
public class ModoOscuroPreference {
	
	 	@Id
	 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 	
	 	@Column(name = "activate")
	    private boolean activado;

	    // Getters y Setters
	 	
	 	

	    public boolean isActivado() {
	        return activado;
	    }

	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setActivado(boolean activado) {
	        this.activado = activado;
	    }

}
