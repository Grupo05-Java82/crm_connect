package com.generation.connect_crm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_clientes")
public class Cliente{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=255)
	@NotBlank(message = "O Atributo Nome é Obrigatório!")
	private String nome;
	
	@Size(max=255)
	@Email
	@NotBlank(message = "O Atributo Email é obrigatorio para comunicação com o cliente")
	private String email;
	
	@Size(max=13)
	private String telefone;
	
	@Size(max=500)
	@NotBlank(message = "O Atributo Interesse é obrigatorio para sabermos qual o produto que o cliente compra nosso")
	private String interesse;
	
//	@oneToMany(fetch = FetchType.EAGER, mappedBy = "id_cliente", cascade = CascadeType.REMOVE)
	//@JsonIgnoreProperties("id_cliente")
//	private List<Oportunidade> oportunidade;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getInteresse() {
		return interesse;
	}
	public void setInteresse(String interesse) {
		this.interesse = interesse;
	}
	

}
