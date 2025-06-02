package com.generation.connect_crm.repository;

import java.math.BigDecimal; 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.connect_crm.model.Oportunidade;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long>{
	
	List<Oportunidade> findAllByStatusContainingIgnoreCase(String status);
	
	List<Oportunidade> findAllByReceita(BigDecimal receita);
	
	
	boolean existsByClienteIdAndStatusIgnoreCase(Long clienteId, String status);

	
	public List<Oportunidade> findByReceitaGreaterThanEqual(BigDecimal receita);


	public List<Oportunidade> findByReceitaLessThanEqual(BigDecimal receita);

}