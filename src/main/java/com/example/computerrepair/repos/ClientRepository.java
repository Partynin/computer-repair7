package com.example.computerrepair.repos;

// This will be AUTO IMPLEMENTED by Spring into a Bean called clientRepository
// CRUD refers Create, Read, Update, Delete

import com.example.computerrepair.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findByNameAndLastname(String name, String lastname);

    Optional<Client> findById(Long id);
}
