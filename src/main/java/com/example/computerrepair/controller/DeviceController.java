package com.example.computerrepair.controller;

import com.example.computerrepair.domain.Client;
import com.example.computerrepair.domain.Device;
import com.example.computerrepair.repos.ClientRepository;
import com.example.computerrepair.repos.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class DeviceController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/modifyDevice")
    private String get(Model model) {
        Iterable<Device> devices = deviceRepository.findAll();
        model.addAttribute("devices", devices);

        return "modifyDevice";
    }

    @PostMapping("/addDevice")
    public String addDevice(String clientId, String deviceName, String modelDevice, String serialNumber, Model model) {
        Optional<Client> clientOptional = clientRepository.findById(Long.valueOf(clientId));
        List<Device> deviceExists = deviceRepository.findByDeviceNameAndSerialNumber(deviceName, serialNumber);

        if (clientOptional.isPresent() && deviceExists.isEmpty()) {
            Client client = clientOptional.get();
            Device device = new Device(deviceName, modelDevice, serialNumber, client);
            deviceRepository.save(device);
        }

        Iterable<Device> devices = deviceRepository.findAll();
        model.addAttribute("devices", devices);

        return "modifyDevice";
    }

    @PostMapping("/deleteDevice")
    public String deleteDevice(int deviceId, Model model) {
        Optional<Device> optionalDevice = deviceRepository.findById((long) deviceId);
        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();
            deviceRepository.delete(device);
        }

        Iterable<Device> devices = deviceRepository.findAll();
        model.addAttribute("devices", devices);

        return "modifyDevice";
    }
}
