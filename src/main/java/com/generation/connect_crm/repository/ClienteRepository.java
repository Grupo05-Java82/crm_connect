package com.generation.connect_crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.connect_crm.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente>findAllByEmailContainingIgnoreCase(String email );
	
	
	//Evitar a repetição de email apenas verificando sem o seneitive case 
	boolean existsByEmailIgnoreCase(String email);
	//Telefone também não será repetido no cliente
	boolean existsByTelefone(String telefone);
	
	
}
