package com.example.computerrepair.repos;

import com.example.computerrepair.domain.Neighborhood;
import org.springframework.data.repository.CrudRepository;

public interface NeighborhoodRepository extends CrudRepository<Neighborhood, Long> {

    Neighborhood findByNameHood(String nameHood);
}
