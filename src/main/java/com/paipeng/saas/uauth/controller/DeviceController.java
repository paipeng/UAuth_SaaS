package com.paipeng.saas.uauth.controller;


import com.paipeng.saas.uauth.tenant.entity.Device;
import com.paipeng.saas.uauth.tenant.service.DeviceService;
import com.paipeng.saas.uauth.util.exception.SC_NOT_FOUND;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static javax.servlet.http.HttpServletResponse.*;

@RestController
@RequestMapping(value = "/devices")
public class DeviceController extends BaseController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Device query(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("query: " + id);
        Device device = deviceService.query(id);
        if (device == null) {
            throw new SC_NOT_FOUND();
        }
        return device;
    }

    @GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public List<Device> query() throws Exception {
        logger.trace("query all");
        return deviceService.query();
    }

    @PostMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public Device save(@NotNull @RequestBody Device device) throws Exception {
        logger.trace("save: " + device);
        //response.setStatus(SC_CREATED);
        return deviceService.save(device);
    }

    @PutMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Device update(@PathVariable("id") Long id, @NotNull @RequestBody Device device) throws Exception {
        logger.trace("update: " + device);
        response.setStatus(SC_OK);
        return deviceService.update(id, device);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public void delete(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("delete" + id);
        response.setStatus(SC_NO_CONTENT);
        deviceService.delete(id);
    }
}
