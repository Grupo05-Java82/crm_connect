package com.generation.connect_crm.controller;

import java.math.BigDecimal;
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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/oportunidades")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OportunidadeController {
	
	@Autowired
	private OportunidadeRepository oportunidadeRepository;

	@GetMapping
	public ResponseEntity<List<Oportunidade>> getAll() {

		return ResponseEntity.ok(oportunidadeRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> getById(@PathVariable Long id) {
		return oportunidadeRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<Oportunidade>> getAllByStatus(@PathVariable String status) {
		return ResponseEntity.ok(oportunidadeRepository.findAllByStatusContainingIgnoreCase(status));

	}
	
	@GetMapping("/receita/{receita}")
	public ResponseEntity<List<Oportunidade>> getByReceita(@PathVariable BigDecimal receita) {
	    return ResponseEntity.ok(oportunidadeRepository.findAllByReceita(receita));
	}
	
	 @GetMapping("/receita_maior/{receita}")
	    public ResponseEntity<List<Oportunidade>> getByReceitaMaior(@PathVariable Double receita){
	        return ResponseEntity.ok(oportunidadeRepository.findByReceitaGreaterThanEqual(receita));
	    }

	    
	 @GetMapping("/receita_menor/{receita}")
	    public ResponseEntity<List<Oportunidade>> getByReceitaMenor(@PathVariable Double receita){
	        return ResponseEntity.ok(oportunidadeRepository.findByReceitaLessThanEqual(receita));
	    }

	@PostMapping
	public ResponseEntity<Oportunidade> post(@Valid @RequestBody Oportunidade oportunidade) {
		return ResponseEntity.status(HttpStatus.CREATED).body(oportunidadeRepository.save(oportunidade));
	}

	@PutMapping
	public ResponseEntity<Oportunidade> put(@Valid @RequestBody Oportunidade oportunidade) {

		if(oportunidade.getId() == null)
			return ResponseEntity.badRequest().build();
		
		if (oportunidadeRepository.existsById(oportunidade.getId())) 
			
			return ResponseEntity.status(HttpStatus.OK).body(oportunidadeRepository.save(oportunidade));
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
		
		if(oportunidade.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		oportunidadeRepository.deleteById(id);
		
	}

}
