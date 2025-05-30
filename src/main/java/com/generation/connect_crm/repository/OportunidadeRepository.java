package com.generation.connect_crm.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.connect_crm.model.Oportunidade;



public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long>{
	
	List<Oportunidade> findAllByStatusContainingIgnoreCase(String status);
	
	List<Oportunidade> findAllByReceita(BigDecimal receita);
	
	public List<Oportunidade> findByReceitaGreaterThanEqual(@Param("receita") Double receita);

    public List<Oportunidade> findByReceitaLessThanEqual(@Param("receita") Double receita);

}
