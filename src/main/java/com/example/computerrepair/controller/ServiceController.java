package com.example.computerrepair.controller;


import com.example.computerrepair.domain.*;
import com.example.computerrepair.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private GuaranteeRepository guaranteeRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/serviceOperation")
    public String getService(Model model) {
        addAttributes(model);

        return "serviceOperation";
    }

    @PostMapping("/serviceOperation")
    public String setService(int clientId, int deviceId, String serviceName, String price, Model model) {
        // Make your code more readable and protect it against null pointer exceptions.
        // https://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html
        // the Optional class forces you to think about the case when the value is not present
        Optional<Client> clientOptional = clientRepository.findById(Long.valueOf(clientId));
        Optional<Device> deviceOptional = deviceRepository.findById(Long.valueOf(deviceId));
        if (clientOptional.isPresent() && deviceOptional.isPresent()) {
            Client client = clientOptional.get();
            Device device = deviceOptional.get();
            Service service = new Service(client, device, serviceName, Integer.parseInt(price));
            serviceRepository.save(service);
        }

        addAttributes(model);

        return "serviceOperation";
    }

    @PostMapping("/createDiscountOperation")
    public String createDiscountOperation(String discountType, int amount, Model model) {
        Optional<Discount> discountOptional = discountRepository.findByAmount(amount);
        if (!discountOptional.isPresent()) {
            Discount discount = new Discount(discountType, amount);
            discountRepository.save(discount);
        }

        addAttributes(model);

        return "serviceOperation";
    }

    @PostMapping("/addServiceGuarantee")
    public String addServiceGuarantee(Guarantee guarantee, Model model) {
        guaranteeRepository.save(guarantee);

        addAttributes(model);

        return "serviceOperation";
    }

    @PostMapping("/addDiscountToService")
    public String addDiscountToService(String serviceId, String discountId, Model model) {
        Optional<Discount> discountOptional = discountRepository.findById(Long.valueOf(discountId));
        if (discountOptional.isPresent()) {
            Discount discount = discountOptional.get();
            serviceRepository.setDiscountByServiceId(Long.valueOf(serviceId), discount);
        }

        addAttributes(model);

        return "serviceOperation";
    }

    @PostMapping("/addGuaranteeToService")
    public String addGuaranteeToService(Long serviceId, Long guaranteeId, Model model) {
        Optional<Guarantee> guaranteeOptional = guaranteeRepository.findById(guaranteeId);
        if (guaranteeOptional.isPresent()) {
            Guarantee guarantee = guaranteeOptional.get();
            serviceRepository.setGuaranteeByServiceId(serviceId, guarantee);
        }

        addAttributes(model);

        return "serviceOperation";
    }

    @PostMapping("/deleteService")
    public String deleteService(int serviceId, Model model) {
        Optional<Service> optionalService = serviceRepository.findById(Long.valueOf(serviceId));
        if (optionalService.isPresent()) {
            Service service = optionalService.get();
            serviceRepository.delete(service);
        }

        addAttributes(model);

        return "serviceOperation";
    }

    private void addAttributes(Model model) {
        Iterable<Guarantee> guarantees = guaranteeRepository.findAll();
        model.addAttribute("guarantees", guarantees);
        Iterable<Service> services = serviceRepository.findAll();
        model.addAttribute("services", services);
        Iterable<Discount> discounts = discountRepository.findAll();
        model.addAttribute("discounts", discounts);
    }
}
