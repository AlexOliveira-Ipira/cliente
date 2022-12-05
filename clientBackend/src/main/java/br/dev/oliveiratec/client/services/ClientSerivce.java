package br.dev.oliveiratec.client.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

	@Transactional
	public ClientDTO inset(ClientDTO dtoInclud) {
		Client entity = new Client();
		copyDtoEntity(dtoInclud, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);		
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dtoInclud) {
		try {		
			Client entity = repository.getReferenceById(id);
			copyDtoEntity(dtoInclud, entity);
			entity = repository.save(entity);
			return new ClientDTO(entity);				
		}
		catch(EntityNotFoundException e) {
			throw new DadosNotFoundException("Id não encontrado " + id );
		}		
	}
	
	public void deletar(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new DadosNotFoundException("Dados não encontrado, objeto não pode ser deletado! ");
		}
	}		
	
	private void copyDtoEntity(ClientDTO dto , Client entity) {		
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
}