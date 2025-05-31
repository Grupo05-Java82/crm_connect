package com.generation.connect_crm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping; // REMOVER OU COMENTAR
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	// BUSCAR TODOS OS USUARIOS REGISTRADOS
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	// BUSCAR USUARIO POR ID
	@GetMapping("/{id}")
	public ResponseEntity <Usuario> getById (@PathVariable Long id){
		return usuarioRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	// BUSCAR USUARIO POR NOME
	@GetMapping("/nome/{nome}")
	public ResponseEntity <List<Usuario>> getByNome(@PathVariable String nome){
		// A consulta já retorna uma lista vazia se não encontrar, não precisa verificar isEmpty e retornar NOT_FOUND
		return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	// BUSCAR USUARIO POR EMAIL (novo método de busca)
	@GetMapping("/usuario/{usuario}") // O nome do PathVariable reflete o campo 'usuario' que é o email
	public ResponseEntity<Usuario> getByUsuario(@PathVariable String usuario){
		return usuarioRepository.findByUsuario(usuario)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
		
	// CADASTRAR UM USUARIO
	@PostMapping("/cadastrar")
	public ResponseEntity<?> post(@Valid @RequestBody Usuario usuario) {
		// VERIFICA SE O EMAIL JÁ ESTÁ CADASTRADO
		Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuario(usuario.getUsuario());
		if (usuarioExistente.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Erro: este e-mail já está cadastrado.");
		}
		// SE ESTIVER TUDO NOS CONFORMES, SALVA O USUARIO
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
	}
	
	// ATUALIZAR O USUARIO ESCOLHIDO
	@PutMapping
	public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario) {
		// Verifica se o ID do usuário existe para atualização
		if (usuario.getId() == null || !usuarioRepository.existsById(usuario.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}

		// Verifica se o e-mail já está em uso por OUTRO usuário
		Optional<Usuario> usuarioComMesmoEmail = usuarioRepository.findByUsuario(usuario.getUsuario());
		if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(usuario.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro: Este e-mail já está cadastrado para outro usuário!");
		}
		
		// Se o ID existe e o e-mail é válido (não em uso por outro), atualiza
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
	}
	
	// ***** O MÉTODO DELETE PARA USUARIO FOI REMOVIDO/COMENTADO CONFORME REQUISITO DO PROFESSOR *****
	/*
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
		
		usuarioRepository.deleteById(id);
	}
	*/
}