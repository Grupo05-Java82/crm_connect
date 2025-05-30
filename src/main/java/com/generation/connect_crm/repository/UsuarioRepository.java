
package com.generation.connect_crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.connect_crm.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	 Optional<Usuario>findByUsuario(String usuario);

    List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
   
    boolean existsByUsuario(String usuario);
	
    
	
}

