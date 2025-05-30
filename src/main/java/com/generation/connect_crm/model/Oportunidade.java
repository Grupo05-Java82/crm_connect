package com.generation.connect_crm.model;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tb_oportunidade")
public class Oportunidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255)
	@NotBlank(message = "O status é obrigatório")
	@Pattern(regexp = "^[^0-9].*", message = "O status não pode ser numérico")
	private String status;
	
	@Column(precision = 10, scale = 2)
    @NotNull(message = "A receita é obrigatória")
    @DecimalMin(value = "0.0", inclusive = false, message = "A receita deve ser maior que zero")
    private BigDecimal receita;
	
	//@ManyToOne
    //@JsonIgnoreProprites("oportunidade")
    //private Cliente cliente;

    //@ManyToOne
    //@JsonIgnoreProprites("oportunidade")
    //private Usuario usuario;
	
	
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
	
	

}
