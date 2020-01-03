package com.example.computerrepair.controller;

import com.example.computerrepair.domain.Address;
import com.example.computerrepair.domain.Client;
import com.example.computerrepair.repos.ClientRepository;
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
                    String name,
                    String lastname,
                    String phone,
                    String neighborhood,
                    String street,
                    String house,
                    String apartment,
                    Model model
            ) {
        List<Client> existClient = clientRepository.findByNameAndLastname(name, lastname);

        if (existClient.isEmpty()) {
            Address address = new Address(neighborhood, street, house, apartment);
            Client client = new Client(name, lastname, phone, address);
            clientRepository.save(client);
        }

        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);

        return "clientData";
    }
}
