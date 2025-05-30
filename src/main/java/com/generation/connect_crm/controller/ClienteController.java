package com.generation.connect_crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.connect_crm.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes") // determina que essa classe trata de todas requisições que tenha /postagens no http
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
}
