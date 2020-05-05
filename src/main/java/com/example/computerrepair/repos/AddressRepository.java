package com.example.computerrepair.repos;

import com.example.computerrepair.domain.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {

    List<Address> findByCityAndNeighborhoodAndStreetAndHouseAndApartment(City city, Neighborhood hood, Street street,
                                                                         Integer house, Integer apartment);

    Address getAddressByCityAndNeighborhoodAndStreetAndHouseAndApartmentAndIndex(City city, Neighborhood hood,
                                                                                 Street street, Integer house,
                                                                                 Integer apartment, Integer index);
}
