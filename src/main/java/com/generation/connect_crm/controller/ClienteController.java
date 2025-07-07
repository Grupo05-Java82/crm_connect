package com.generation.connect_crm.controller;

import java.util.List;
import java.util.Optional; // Importar Optional

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

import com.generation.connect_crm.model.Cliente;
import com.generation.connect_crm.model.Usuario;
import com.generation.connect_crm.repository.ClienteRepository;

import jakarta.validation.Valid; 

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getAll(){
		return ResponseEntity.ok(clienteRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Long id){
		return clienteRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<List<Cliente>> getByEmail(@PathVariable String email){
		return ResponseEntity.ok(clienteRepository.findAllByEmailContainingIgnoreCase(email));
	}
	/*
	@PostMapping Não entendi porque cliente busca produto.
	public ResponseEntity<Cliente> postProduto(@Valid @RequestBody Cliente cliente){
		return clienteRepository.findById(cliente.getOportunidade().getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.badRequest().build());
	}*/
	
	@PostMapping
	public ResponseEntity<?> post(@Valid @RequestBody Cliente cliente, String email) {
		
		if (clienteRepository.existsByEmailIgnoreCase(cliente.getEmail())) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Erro: este e-mail já está cadastrado.");
		}
		if (clienteRepository.existsByTelefone(cliente.getTelefone())) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("Erro: este Telefone já está cadastrado.");
		}
	    // Ver se o cliente com esse ID já existe
	    if (cliente.getId() != null && clienteRepository.existsById(cliente.getId())) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("Já existe um cliente com o ID: " + cliente.getId());
	        
	    }

	    Cliente clienteSalvo = clienteRepository.save(cliente);
	    return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	/*
	@PutMapping novamente o produto, não saquei?
	<troquei para <?> que é um coringa para retornar o que estiver no optmal independente do que seja.
	public ResponseEntity<Cliente> putProduto(@Valid @RequestBody Cliente cliente) {
					
		if (clienteRepository.existsById(cliente.getId())){

			return oportunidadeRepository.findById(cliente.getOportunidade().getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente)))
					.orElse(ResponseEntity.badRequest().build());
		}		
		
		return ResponseEntity.notFound().build();

	}*/
	
	// No seu ClienteController.java

@PutMapping
public ResponseEntity<?> put(@Valid @RequestBody Cliente cliente) {
    // 1. Verifica se o cliente existe pelo ID antes de tentar atualizar
    if (clienteRepository.existsById(cliente.getId())){
        // Busca o cliente existente no banco de dados para comparar o email atual
        Cliente clienteExistente = clienteRepository.findById(cliente.getId()).get();

        // Validação de Email:
        // Só verifica se o email já existe para OUTRO cliente se o email foi alterado
        if (!clienteExistente.getEmail().equalsIgnoreCase(cliente.getEmail())) { // Se o email NOVO é diferente do email ANTIGO
            if (clienteRepository.existsByEmailIgnoreCase(cliente.getEmail())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Erro: este e-mail já está cadastrado.");
            }
        }

        // Validação de Telefone:
        if (!clienteExistente.getTelefone().equals(cliente.getTelefone())) { // Se o telefone NOVO é diferente do telefone ANTIGO
             if (clienteRepository.existsByTelefone(cliente.getTelefone())) {
                 return ResponseEntity
                         .status(HttpStatus.BAD_REQUEST)
                         .body("Erro: este Telefone já está cadastrado.");
             }
        }

        // Se passou nas validações, salva o cliente atualizado
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente));

    }

    // Se o ID não existir, retorna NOT_FOUND
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // Retorna 204 se a exclusão for bem-sucedida
	public void delete(@PathVariable Long id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		// Se o cliente não existir, lança uma exceção 404
		if(cliente.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
		
		clienteRepository.deleteById(id);
	}
}
