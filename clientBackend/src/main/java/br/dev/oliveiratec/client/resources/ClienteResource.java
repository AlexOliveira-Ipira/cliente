package br.dev.oliveiratec.client.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
