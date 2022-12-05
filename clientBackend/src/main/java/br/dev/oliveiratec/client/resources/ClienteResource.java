package br.dev.oliveiratec.client.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.dev.oliveiratec.client.dto.ClientDTO;
import br.dev.oliveiratec.client.services.ClientSerivce;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClientSerivce service;

	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer numeroDeLinhasPorPagina,
			@RequestParam(value = "direction", defaultValue = "ASC") String ordem,
			@RequestParam(value = "orderBy", defaultValue = "name") String ordenarPor) {
		PageRequest pageRequest = PageRequest.of(pagina, numeroDeLinhasPorPagina, Direction.valueOf(ordem), ordenarPor);
		Page<ClientDTO> list = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> incluir(@RequestBody ClientDTO dtoInclud){
		dtoInclud = service.inset(dtoInclud);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dtoInclud.getId()).toUri();			
		return ResponseEntity.ok().body(dtoInclud);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dotUpdate ){
		dotUpdate = service.update(id, dotUpdate);
		return ResponseEntity.ok().body(dotUpdate);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}