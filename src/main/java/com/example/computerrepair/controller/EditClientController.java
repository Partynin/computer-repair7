package com.example.computerrepair.controller;

import com.example.computerrepair.domain.*;
import com.example.computerrepair.repos.ClientRepository;
import com.example.computerrepair.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class EditClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressService addressService;

    @GetMapping("/editClient")
    public String getClient(Model model) {
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);

        return "editClient";
    }

    @PostMapping("/deleteClient")
    public String deleteClient(Model model, Long clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            clientRepository.delete(client);
        }

        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);

        return "editClient";
    }

    @PostMapping("/editClient")
    public String setClient
            (
                    Long id,
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
        Optional<Client> existClient = clientRepository.findById(id);

        if (existClient.isPresent()) {
            City cityOb = addressService.getCityObject(nameCity);

            Neighborhood neighborhoodOb = addressService.getNeighborhoodObject(nameNeighborhood);

            Street streetOb = addressService.getStreetObject(nameStreet);

            Address address = addressService.getAddressObject(cityOb, neighborhoodOb, streetOb,
                    house, apartment, index);

            Client client = existClient.get();
            client.setAddress(address);
            client.setNewPersonalInformation(lastname, name, patronymic, phone);

            clientRepository.save(client);
        }

        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);

        return "editClient";
    }
}
