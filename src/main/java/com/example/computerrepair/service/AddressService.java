package com.example.computerrepair.service;

import com.example.computerrepair.domain.Address;
import com.example.computerrepair.domain.City;
import com.example.computerrepair.domain.Neighborhood;
import com.example.computerrepair.domain.Street;
import com.example.computerrepair.repos.AddressRepository;
import com.example.computerrepair.repos.CityRepository;
import com.example.computerrepair.repos.NeighborhoodRepository;
import com.example.computerrepair.repos.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private NeighborhoodRepository neighborhoodRepository;
    @Autowired
    private StreetRepository streetRepository;


    public City getCityObject(String nameCity) {
        City cityOb = cityRepository.findByNameCity(nameCity);
        if (cityOb == null) {
            cityOb = new City(nameCity);
            cityRepository.save(cityOb);
        }

        return cityOb;
    }

    public Neighborhood getNeighborhoodObject(String nameNeighborhood) {
        Neighborhood neighborhoodOb = neighborhoodRepository.findByNameHood(nameNeighborhood);
        if (neighborhoodOb == null) {
            neighborhoodOb = new Neighborhood(nameNeighborhood);
            neighborhoodRepository.save(neighborhoodOb);
        }

        return neighborhoodOb;
    }

    public Street getStreetObject(String nameStreet) {
        Street streetOb = streetRepository.findByNameStreet(nameStreet);
        if (streetOb == null) {
            streetOb = new Street(nameStreet);
            streetRepository.save(streetOb);
        }

        return streetOb;
    }

    public Address getAddressObject(City cityOb, Neighborhood neighborhoodOb, Street streetOb,
                                    Integer house, Integer apartment, Integer index) {
        Address address;

        List<Address> existAddresses = addressRepository.findByCityAndNeighborhoodAndStreetAndHouseAndApartment(
                cityOb, neighborhoodOb, streetOb, house, apartment
        );

        if (existAddresses.isEmpty()) {
            address = new Address(cityOb, neighborhoodOb, streetOb, house, apartment, index);
        } else {
            address = addressRepository.getAddressByCityAndNeighborhoodAndStreetAndHouseAndApartmentAndIndex(
                    cityOb, neighborhoodOb, streetOb, house, apartment, index
            );
        }

        return address;
    }
}
