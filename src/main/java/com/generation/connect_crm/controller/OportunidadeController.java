package com.generation.connect_crm.controller;

import java.math.BigDecimal; // Importar BigDecimal
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

import com.generation.connect_crm.model.Oportunidade;
import com.generation.connect_crm.repository.OportunidadeRepository;
import com.generation.connect_crm.repository.ClienteRepository; // Adicionar se for validar cliente
import com.generation.connect_crm.repository.UsuarioRepository;  // Adicionar se for validar usuario

import jakarta.validation.Valid;

@RestController
@RequestMapping("/oportunidades")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OportunidadeController {
	
	@Autowired
	private OportunidadeRepository oportunidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository; // Para validar a existência do Cliente

	@Autowired
	private UsuarioRepository usuarioRepository; // Para validar a existência do Usuario

	@GetMapping
	public ResponseEntity<List<Oportunidade>> getAll() {
		return ResponseEntity.ok(oportunidadeRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> getById(@PathVariable Long id) {
		return oportunidadeRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/status/{status}")
	//Logica para verificar se o status existe
	public ResponseEntity<List<Oportunidade>> getAllByStatus(@PathVariable String status) {
	    List<Oportunidade> oportunidades = oportunidadeRepository.findAllByStatusContainingIgnoreCase(status);

	    if (oportunidades.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }

	    return ResponseEntity.ok(oportunidades);
	}

	
	// Corrigido para BigDecimal
	@GetMapping("/receita/{receita}")
	public ResponseEntity<List<Oportunidade>> getByReceita(@PathVariable BigDecimal receita) {
	    return ResponseEntity.ok(oportunidadeRepository.findAllByReceita(receita));
	}
	
	// Corrigido para BigDecimal
	@GetMapping("/receita_maior/{receita}")
	public ResponseEntity<List<Oportunidade>> getByReceitaMaior(@PathVariable BigDecimal receita){
	    return ResponseEntity.ok(oportunidadeRepository.findByReceitaGreaterThanEqual(receita));
	}
	
	// Corrigido para BigDecimal
	@GetMapping("/receita_menor/{receita}")
	public ResponseEntity<List<Oportunidade>> getByReceitaMenor(@PathVariable BigDecimal receita){
	    return ResponseEntity.ok(oportunidadeRepository.findByReceitaLessThanEqual(receita));
	}

	@PostMapping
	public ResponseEntity<?> post(@Valid @RequestBody Oportunidade oportunidade) {

	    // Verifica se o cliente e o usuário existem
	    if (oportunidade.getCliente() == null || !clienteRepository.existsById(oportunidade.getCliente().getId())
	        || oportunidade.getUsuario() == null || !usuarioRepository.existsById(oportunidade.getUsuario().getId())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente ou usuário inválido.");
	    }

	    // Verifica se o cliente já tem uma oportunidade com o mesmo status (ignorar maiúsculas/minúsculas)
	    boolean statusDuplicado = oportunidadeRepository
	        .existsByClienteIdAndStatusIgnoreCase(oportunidade.getCliente().getId(), oportunidade.getStatus());

	    if (statusDuplicado) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	            .body("Erro: Este cliente já possui uma oportunidade com o status '" + oportunidade.getStatus() + "'.");
	    }

	    // Salva a oportunidade
	    Oportunidade salva = oportunidadeRepository.save(oportunidade);
	    return ResponseEntity.status(HttpStatus.CREATED).body(salva);
	}


	@PutMapping
	public ResponseEntity<Oportunidade> put(@Valid @RequestBody Oportunidade oportunidade) {
		// Verifica se a Oportunidade existe pelo ID
		if (oportunidadeRepository.existsById(oportunidade.getId())) {
			// Verifica se os IDs de Cliente e Usuario existem, se forem fornecidos
			if (oportunidade.getCliente() != null && !clienteRepository.existsById(oportunidade.getCliente().getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado!");
			}
			if (oportunidade.getUsuario() != null && !usuarioRepository.existsById(oportunidade.getUsuario().getId())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!");
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(oportunidadeRepository.save(oportunidade));
		}
		// Se a Oportunidade não existir, retorna NOT_FOUND
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
		
		if(oportunidade.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Oportunidade não encontrada!");
		
		oportunidadeRepository.deleteById(id);
	}
}