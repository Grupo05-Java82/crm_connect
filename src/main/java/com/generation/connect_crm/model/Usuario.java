package com.generation.connect_crm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotBlank(message = "O Atributo Nome é Obrigatório!")
	String nome;
	
	@NotBlank(message = "O Atributo email é Obrigatório!")
	@Email(message = "O Atributo deve ser um email válido!")
	String usuario;
	
	@NotBlank(message = "O atributo senha é obrigatório")
	String senha;
	
	@Column(length = 300)
	String foto;

	/*
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	 List<Oportunidade> oportunidades;
	 */
}
