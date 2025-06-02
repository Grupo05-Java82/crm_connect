package com.generation.connect_crm.model;

import java.util.List;

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
import jakarta.validation.constraints.Size; // Adicionar para Size

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotBlank(message = "O Atributo Nome é Obrigatório!")
	@Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.") // Adicionado Size
	String nome;
	
	@NotBlank(message = "O Atributo email é Obrigatório!")
	@Email(message = "O Atributo deve ser um email válido!")
	@Size(max = 255, message = "O e-mail não pode exceder 255 caracteres.") // Adicionado Size
	String usuario; // Campo para o e-mail (login)
	
	@NotBlank(message = "O atributo senha é obrigatório")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.") // Adicionado Size
	String senha;
	
	@Column(length = 300)
	@Size(max = 300, message = "A URL da foto não pode exceder 300 caracteres.") // Adicionado Size
	String foto;
	
	// Renomeado para 'oportunidades' no plural para clareza
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE) // FetchType LAZY é mais performático
	@JsonIgnoreProperties("usuario")
	List<Oportunidade> oportunidades; // Getter/Setter serão atualizados
	
	
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	// Adicionar getter e setter para a lista de oportunidades
	public List<Oportunidade> getOportunidades() {
		return oportunidades;
	}

	public void setOportunidades(List<Oportunidade> oportunidades) {
		this.oportunidades = oportunidades;
	}
}