package br.com.fiap.ez.fastfood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDTO {
	
	@JsonProperty("cpf")
	private String cpf;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("email")
    private String email;
    
	public CustomerDTO(String cpf, String name, String email) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.email = email;
	}

}
