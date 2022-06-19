package com.paipeng.saas.uauth.controller;

import com.paipeng.saas.uauth.tenant.entity.Code;
import com.paipeng.saas.uauth.tenant.service.CodeService;
import com.paipeng.saas.uauth.util.exception.SC_NOT_FOUND;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/codes")
public class CodeController extends BaseController{
    @Autowired
    private CodeService codeService;

    @GetMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Code query(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("query: " + id);
        Code Code = codeService.query(id);
        if (Code == null) {
            throw new SC_NOT_FOUND();
        }
        return Code;
    }

    @GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public List<Code> query() throws Exception {
        logger.trace("query all");
        return codeService.query();
    }

    @PostMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public Code save(@NotNull @RequestBody Code code) throws Exception {
        logger.trace("save: " + code);
        //response.setStatus(SC_CREATED);
        return codeService.save(code);
    }

    @PutMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Code update(@PathVariable("id") Long id, @NotNull @RequestBody Code code) throws Exception {
        logger.trace("update: " + code);
        //response.setStatus(SC_OK);
        return codeService.update(id, code);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public void delete(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("delete" + id);
        //response.setStatus(SC_NO_CONTENT);
        codeService.delete(id);
    }


    @GetMapping(value = "/serialnumber/{serialNumber}", produces = {"application/json;charset=UTF-8"})
    public Code queryBySerialNumber(@NotNull @PathVariable("serialNumber") String serialNumber) throws Exception {
        logger.trace("query: " + serialNumber);
        Code Code = codeService.queryBySerialNumber(serialNumber);
        if (Code == null) {
            throw new SC_NOT_FOUND();
        }
        return Code;
    }

    @GetMapping(value = "/tasks/{taskId}", produces = {"application/json;charset=UTF-8"})
    public List<Code> queryCodesByTaskId(@NotNull @PathVariable("taskId") Long taskId) throws Exception {
        logger.trace("queryCodesByTaskId: " + taskId);
        return codeService.queryCodesByTaskId(taskId);
    }
}
