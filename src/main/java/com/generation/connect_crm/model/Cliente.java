package com.generation.connect_crm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres.") // Adicionado min
	@NotBlank(message = "O Atributo Nome é Obrigatório!")
	private String nome;
	
	@Size(max=255)
	@Email(message = "O Atributo Email deve ser um email válido!") // Adicionado mensagem de erro
	@NotBlank(message = "O Atributo Email é obrigatório para comunicação com o cliente")
	private String email;
	
	@Size(max=20, message = "O telefone não pode exceder 20 caracteres.") // Ajustado tamanho para mais flexibilidade
	private String telefone;
	
	@Size(min = 5, max=500, message = "O interesse deve ter entre 5 e 500 caracteres.") // Adicionado min
	@NotBlank(message = "O Atributo Interesse é obrigatório para sabermos qual o produto que o cliente compra nosso")
	private String interesse;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.REMOVE) // Corrigido mappedBy e FetchType
	@JsonIgnoreProperties("cliente") // Corrigido JsonIgnoreProperties
	private List<Oportunidade> oportunidade; // Renomeado para 'oportunidades' para melhor clareza (opcional)
	
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
	//Dica para evitar caracteres indesejados
	public void setTelefone(String telefone) {
	    if (telefone != null) {
	        this.telefone = telefone.replaceAll("[^\\d]", "");
	    } else {
	        this.telefone = null;
	    }
	}

	public String getInteresse() {
		return interesse;
	}
	public void setInteresse(String interesse) {
		this.interesse = interesse;
	}
	
	// Adicionar getter e setter para a lista de oportunidades
	public List<Oportunidade> getOportunidade() { 
// manter nome "oportunidade" para compatibilidade com o que já foi feito, mas "oportunidades" seria mais correto para listas
		return oportunidade;
	}

	public void setOportunidade(List<Oportunidade> oportunidade) {
		this.oportunidade = oportunidade;
	}
}