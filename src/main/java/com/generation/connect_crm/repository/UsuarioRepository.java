package com.generation.connect_crm.repository;

import java.math.BigDecimal;
import java.util.List;

import com.generation.connect_crm.model.Usuario;

public class UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	 Optional<Usuario> findByUsuario(String usuario);

    List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
   
    boolean existsByUsuario(String usuario);
	
    
	
}
