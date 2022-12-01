package br.dev.oliveiratec.client.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.oliveiratec.client.entities.Client;
import br.dev.oliveiratec.client.repositories.ClientRepository;

@Service
public class ClientSerivce {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly =true)
	public List<Client> findAll(){
		return repository.findAll();
	}	
}