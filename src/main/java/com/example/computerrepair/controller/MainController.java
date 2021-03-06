package com.example.computerrepair.controller;

import com.example.computerrepair.domain.*;
import com.example.computerrepair.repos.*;
import com.example.computerrepair.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;


    @GetMapping("/")
    public String greeting(Model model) {

        return "greeting";
    }

    @GetMapping("/clientData")
    public String getClient(Model model) {
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);

        return "clientData";
    }

    @PostMapping("/clientData")
    public String setClient
            (
                    String lastname,
                    String name,
                    String patronymic,
                    Long phone,
                    String nameCity,
                    String nameNeighborhood,
                    String nameStreet,
                    Integer house,
                    Integer apartment,
                    Integer index,
                    Model model
            ) {
        List<Client> existClients = clientRepository.findByNameAndLastname(name, lastname);

        if (existClients.isEmpty()) {
            City cityOb = addressService.getCityObject(nameCity);

            Neighborhood neighborhoodOb = addressService.getNeighborhoodObject(nameNeighborhood);

            Street streetOb = addressService.getStreetObject(nameStreet);

            List<Address> existAddresses = addressRepository.findByCityAndNeighborhoodAndStreetAndHouseAndApartment(
                    cityOb, neighborhoodOb, streetOb, house, apartment
            );
            if (existAddresses.isEmpty()) {
                Address address = new Address(cityOb, neighborhoodOb, streetOb, house, apartment, index);
                Client client = new Client(lastname, name, patronymic, phone, address);
                clientRepository.save(client);
            }
        }

        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);

        return "clientData";
    }
}
