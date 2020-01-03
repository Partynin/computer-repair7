package com.example.computerrepair.repos;

import com.example.computerrepair.domain.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceRepository extends CrudRepository<Device, Long> {

    List<Device> findByDeviceNameAndSerialNumber(String deviceName, String serialNumber);
}
