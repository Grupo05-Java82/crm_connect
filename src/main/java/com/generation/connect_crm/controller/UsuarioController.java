package com.generation.connect_crm.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.connect_crm.model.Usuario;
import com.generation.connect_crm.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	// BUSCAR TODOS OS COLABORADORES REGISTRADOS
		@GetMapping
		public ResponseEntity<List<Usuario>> getAll(){
			return ResponseEntity.ok(usuarioRepository.findAll());
		}
		
		// BUSCAR COLABORADOR POR ID EM TABELA
		@GetMapping("/{id}")
		public ResponseEntity <Usuario> getById (@PathVariable Long id){
			return usuarioRepository.findById(id)
					.map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		
		
		// BUSCAR COLABORADOR POR NOME
		@GetMapping("/nome/{nome}")
		public ResponseEntity <List<Usuario>> getByNome(@PathVariable String nome){
			
			// CONSULTA SE O COLABORADOR NÃO EXISTE
			List<Usuario> produtos = usuarioRepository.findAllByNomeContainingIgnoreCase(nome);
			if (produtos.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
			//CASO ELE EXISTA, SERA MOSTRADO A LISTA
			return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
		}
		
		//CADASTRAR UM USUARIO
		 @PostMapping("/cadastrar")
		    public ResponseEntity<?> post(@Valid @RequestBody Usuario usuario) {

		        // VERIFICA SE O EMAIL ESTA CADASTRADO
		        // USANDO OPTIONAL PARA VERIFICAR A PRESENÇA
		        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuario(usuario.getUsuario());
		        if (usuarioExistente.isPresent()) {
		            return ResponseEntity
		                    .status(HttpStatus.BAD_REQUEST)
		                    .body("Erro:este e-mail já esta cadastrado.");
		        }

		        // SE ESTIVER TUDO NOS CONFORMES, SALVA O COLABORADOR
		        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
		    }
		
		 //ATUALIZAR O COLABORADOR ESCOLHIDO
		@PutMapping
		public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario) {
			
			// VERIFICA SE O EMAIL ESTA CADASTRADO
			// USANDO OPTIONAL PARA VERIFICAR A PRESENÇA
			 Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuario(usuario.getUsuario());
		     if (usuarioExistente.isPresent()) {
		    	 
		    	 //CASO JA EXISTA, ENVIA UMA EXEÇÃO 
		    	 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ja existente!");
		     }
		     
		     //VERIFICA SE ESTA O ID DIGITADO ESTA VAZIO
			if (usuario.getId() == null)
				return ResponseEntity.badRequest().build();
	 
			//SE TIVER TUDO CERTO, ATUALIZA
			if (usuarioRepository.existsById(usuario.getId()))
				return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
		
			//CASO DIGITE UM ID QUE N ESTEJA CADASTRADO, RETORNA NOT FOUND
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		//DELETE POR ID
		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void delete(@PathVariable Long id) {
			
			//VERIFICA SE O COLABORADOR EXISTE PELO ID
			Optional<Usuario> usuario = usuarioRepository.findById(id);
			if(usuario.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
			//CASO EXISTA, DELETE
			usuarioRepository.deleteById(id);
		}
	}
