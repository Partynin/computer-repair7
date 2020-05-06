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

    @GetMapping("/updateServiceOperation")
    public String getUpdateService(Model model) {
        addAttributes(model);

        return "editServiceOperation";
    }

    @PostMapping("/serviceOperation")
    public String setService(Long clientId, Long deviceId, String serviceName, String price, Model model) {
        // Make your code more readable and protect it against null pointer exceptions.
        // https://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html
        // the Optional class forces you to think about the case when the value is not present
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        if (clientOptional.isPresent() && deviceOptional.isPresent()) {
            Client client = clientOptional.get();
            Device device = deviceOptional.get();
            Service service = new Service(client, device, serviceName, Integer.parseInt(price));
            serviceRepository.save(service);
        }
        addAttributes(model);

        return "serviceOperation";
    }

    @PostMapping("/updateServiceOperation")
    public String updateService(Long serviceId, Long clientId, Long deviceId, String serviceName, Integer price, Model model) {
        addAttributes(model);

        if (serviceId != null && clientId != null && deviceId != null) {
            Optional<Service> serviceOptional = serviceRepository.findById(serviceId);
            if (serviceOptional.isPresent()) {
                Optional<Client> clientOptional = clientRepository.findById(clientId);
                Optional<Device> deviceOptional = deviceRepository.findById(deviceId);

                if (clientOptional.isPresent() && deviceOptional.isPresent()) {
                    Client client = clientOptional.get();
                    Device device = deviceOptional.get();
                    Service service = serviceOptional.get();
                    service.setClient(client);
                    service.setDevice(device);
                    service.setServiceName(serviceName);
                    service.setPrice(price);
                    serviceRepository.save(service);
                } else {
                    model.addAttribute("updateError", "Клиента или устройства с указанным ID не существует!");
                }
            } else {
                model.addAttribute("updateError", "Услуги с указанным ID не существует!");
            }
        } else {
            model.addAttribute("updateError", "Поля ID услуги, ID клиента, ID устройства не должны быть пустыми!");
        }

        return "editServiceOperation";
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

    @PostMapping("/updateDiscount")
    public String updateDiscount(Long discountId, String discountType, int amount, String status, Model model) {
        Optional<Discount> discountOptional = discountRepository.findById(discountId);
        if (!discountOptional.isPresent()) {
            Discount discount = discountOptional.get();
            discount.setDiscountType(discountType);
            discount.setAmount(amount);
            discount.setStatus(Boolean.parseBoolean(status));
            discountRepository.save(discount);
        }
        addAttributes(model);

        return "editServiceOperation";
    }

    @PostMapping("/addServiceGuarantee")
    public String setGuarantee(Guarantee guarantee, Model model) {
        guaranteeRepository.save(guarantee);
        addAttributes(model);

        return "serviceOperation";
    }

    @PostMapping("/updateGuarantee")
    public String updateGuarantee(Long guaranteeId, String guaranteeType, int duration, String status, Model model) {
        Optional<Guarantee> guaranteeOptional = guaranteeRepository.findById(guaranteeId);
        if (guaranteeOptional.isPresent()) {
            Guarantee guarantee = guaranteeOptional.get();
            guarantee.setGuaranteeType(guaranteeType);
            guarantee.setDuration(duration);
            guarantee.setStatus(Boolean.parseBoolean(status));
            guaranteeRepository.save(guarantee);
        }
        addAttributes(model);

        return "editServiceOperation";
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
    public String deleteService(Long serviceId, Model model) {
        Optional<Service> optionalService = serviceRepository.findById(serviceId);
        if (optionalService.isPresent()) {
            Service service = optionalService.get();
            serviceRepository.delete(service);
        }
        addAttributes(model);

        return "editServiceOperation";
    }

    @PostMapping("/deleteDiscount")
    public String deleteDiscount(Long discountId, Model model) {
        Optional<Discount> optionalDiscount = discountRepository.findById(discountId);
        if (optionalDiscount.isPresent()) {
            Discount discount = optionalDiscount.get();
            discountRepository.delete(discount);
        }
        addAttributes(model);

        return "editServiceOperation";
    }

    @PostMapping("/deleteGuarantee")
    public String deleteGuarantee(Long guaranteeId, Model model) {
        Optional<Guarantee> optionalGuarantee = guaranteeRepository.findById(guaranteeId);
        if (optionalGuarantee.isPresent()) {
            Guarantee guarantee = optionalGuarantee.get();
            guaranteeRepository.delete(guarantee);
        }
        addAttributes(model);

        return "editServiceOperation";
    }

    private void addAttributes(Model model) {
        Iterable<Guarantee> guarantees = guaranteeRepository.findAll();
        model.addAttribute("guarantees", guarantees);
        Iterable<Service> services = serviceRepository.findAll();
        model.addAttribute("services", services);
        Iterable<Discount> discounts = discountRepository.findAll();
        model.addAttribute("discounts", discounts);
        model.addAttribute("updateError");
    }
}
