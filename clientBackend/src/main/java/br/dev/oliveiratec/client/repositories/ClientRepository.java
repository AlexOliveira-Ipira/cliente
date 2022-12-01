package br.dev.oliveiratec.client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.oliveiratec.client.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
