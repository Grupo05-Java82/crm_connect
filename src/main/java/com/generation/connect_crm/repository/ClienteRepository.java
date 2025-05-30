package com.generation.connect_crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.connect_crm.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente>findAllByEmailContainingIgnoreCase(String email);
}
