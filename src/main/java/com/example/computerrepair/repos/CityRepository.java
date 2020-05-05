package com.example.computerrepair.repos;

import com.example.computerrepair.domain.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {

    City findByNameCity(String nameCity);
}