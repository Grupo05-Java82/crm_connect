package com.generation.connect_crm.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn; // Adicionar para JoinColumn
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size; // Adicionar para Size

@Entity
@Table(name = "tb_oportunidade")
public class Oportunidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O status é obrigatório")
	@Size(max = 50, message = "O status não pode exceder 50 caracteres.") // Adicionado Size
	// @Pattern(regexp = "^[^0-9].*", message = "O status não pode ser numérico") // Mantido, mas reavaliar em refatoração
	private String status;
	
	@Column(precision = 10, scale = 2) // Garante 10 dígitos no total, 2 após a vírgula
	@NotNull(message = "A receita é obrigatória")
	@DecimalMin(value = "0.0", inclusive = false, message = "A receita deve ser maior que zero")
	private BigDecimal receita;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id") // Nome da coluna da chave estrangeira
	@JsonIgnoreProperties("oportunidade")
	private Cliente cliente; // Renomeado para 'cliente' (anteriormente id_cliente)
	
	@ManyToOne
	@JoinColumn(name = "usuario_id") // Nome da coluna da chave estrangeira
	@JsonIgnoreProperties("oportunidades") // Corrigido para corresponder à lista em Usuario
	private Usuario usuario;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getReceita() {
		return receita;
	}

	public void setReceita(BigDecimal receita) {
		this.receita = receita;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}