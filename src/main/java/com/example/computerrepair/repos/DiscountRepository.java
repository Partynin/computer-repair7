package com.example.computerrepair.repos;

import com.example.computerrepair.domain.Discount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DiscountRepository extends CrudRepository<Discount, Long> {

    Optional<Discount> findByAmount(int amount);
}
