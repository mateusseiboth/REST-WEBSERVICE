package com.seisbot.prova.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RepetilCreateDTO {
	
	@NotBlank(message = "O tamanho deve ser informado")
	@Size(min = 2, message = "O tamanho deve ter no mínimo 2 caracteres")
	private String tamanho;

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	

}
