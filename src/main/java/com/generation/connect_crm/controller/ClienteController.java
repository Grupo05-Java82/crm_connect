package com.generation.connect_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.connect_crm.model.Cliente;
import com.generation.connect_crm.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes") // determina que essa classe trata de todas requisições que tenha /postagens no http
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getAll(){
		return ResponseEntity.ok(clienteRepository.findAll());
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Cliente> getById(@PathVariable Long id){
//		return clienteRepository.findById(id)
//			.map(resposta -> ResponseEntity.ok(resposta))
//			.orElse(ResponseEntity.notFound().build());
//	}
	
	/*
	@PostMapping
	public ResponseEntity<Cliente> postProduto(@Valid @RequestBody Cliente cliente){
		return clienteRepository.findById(cliente.getOportunidade().getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.badRequest().build());
	}*/
	
	/*
	@PutMapping
	public ResponseEntity<Cliente> putProduto(@Valid @RequestBody Cliente cliente) {
					
		if (clienteRepository.existsById(cliente.getId())){

			return oportunidadeRepository.findById(cliente.getOportunidade().getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente)))
					.orElse(ResponseEntity.badRequest().build());
		}		
		
		return ResponseEntity.notFound().build();

	}*/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
		
		return clienteRepository.findById(id)
				.map(resposta -> {
					clienteRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
