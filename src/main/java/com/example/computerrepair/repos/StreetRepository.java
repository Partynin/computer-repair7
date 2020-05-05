package com.example.computerrepair.repos;

import com.example.computerrepair.domain.Street;
import org.springframework.data.repository.CrudRepository;

public interface StreetRepository extends CrudRepository<Street, Long> {

    Street findByNameStreet(String nameStreet);
}
