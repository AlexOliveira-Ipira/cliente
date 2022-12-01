package br.dev.oliveiratec.client.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.oliveiratec.client.dto.ClientDTO;
import br.dev.oliveiratec.client.entities.Client;
import br.dev.oliveiratec.client.repositories.ClientRepository;
import br.dev.oliveiratec.client.services.exceptions.DadosNotFoundException;

@Service
public class ClientSerivce {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDTO(x));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new DadosNotFoundException("Dados não encotrados"));
		return new ClientDTO(entity);
	}
}