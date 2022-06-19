package com.paipeng.saas.uauth.tenant.service;

import com.paipeng.saas.uauth.tenant.entity.Device;
import com.paipeng.saas.uauth.tenant.entity.User;
import com.paipeng.saas.uauth.tenant.repository.DeviceRepository;
import com.paipeng.saas.uauth.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService extends BaseService{
    @Autowired
    private DeviceRepository deviceRepository;

    public Device query(Long deviceId) {
        return deviceRepository.findById(deviceId).orElse(null);
    }

    public List<Device> query() {
        return deviceRepository.findAll();
    }

    public Device save(Device device) {
        logger.trace("save");
        if (device.getCompany() == null) {
            User user = getUserFromSecurity();
            device.setCompany(user.getCompany());
        }
        return deviceRepository.saveAndFlush(device);
    }

    public Device update(Long deviceId, Device device) {
        Device foundDevice = query(deviceId);
        if (foundDevice == null) {
            throw new SC_NOT_FOUND();
        }
        foundDevice.setName(device.getName());
        foundDevice.setLatitude(device.getLatitude());
        foundDevice.setLongitude(device.getLongitude());
        foundDevice.setPlace(device.getPlace());
        return deviceRepository.saveAndFlush(device);
    }

    public void delete(Long deviceId) {
        Device foundDevice = query(deviceId);
        if (foundDevice == null) {
            throw new SC_NOT_FOUND();
        }
        deviceRepository.delete(foundDevice);
    }
}
