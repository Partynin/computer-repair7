package com.example.computerrepair.controller;

import com.example.computerrepair.domain.Client;
import com.example.computerrepair.domain.Service;
import com.example.computerrepair.repos.ClientRepository;
import com.example.computerrepair.repos.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ReportController {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/report")
    public String getReport(Model model) {
        model.addAttribute("clients", new HashSet<>());

        return "reports";
    }

    @PostMapping("/reportAboutClientsContactingASpecifiedNumberOfTimes")
    public String getReportAboutClientsContactingASpecifiedNumberOfTimes(Integer amount, Model model) {
        Iterable<Service> services = serviceRepository.findAll();
        Map<Client, Integer> clientAndCount = new HashMap<>();

        for (Service service: services) {
            Client client = service.getClient();
            if (!clientAndCount.containsKey(client)) {
                clientAndCount.put(client, 1);
            } else {
                clientAndCount.put(client, clientAndCount.get(client) + 1);
            }
        }

        Set<Client> clients = new HashSet<>();
        for (Client key : clientAndCount.keySet()) {
            if (clientAndCount.get(key) == amount){
                clients.add(key);
            }
        }

        model.addAttribute("clients", clients);

        return "reports";
    }
}
