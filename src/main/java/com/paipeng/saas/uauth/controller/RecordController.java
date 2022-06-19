package com.paipeng.saas.uauth.controller;

import com.paipeng.saas.uauth.tenant.entity.Record;
import com.paipeng.saas.uauth.tenant.service.RecordService;
import com.paipeng.saas.uauth.util.exception.SC_NOT_FOUND;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/records")
public class RecordController extends BaseController {
    @Autowired
    private RecordService recordService;

    @GetMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Record query(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("query: " + id);
        Record Record = recordService.query(id);
        if (Record == null) {
            throw new SC_NOT_FOUND();
        }
        return Record;
    }

    @GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public List<Record> query() throws Exception {
        logger.trace("query all");
        return recordService.query();
    }

    @PostMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public Record save(@NotNull @RequestBody Record record) throws Exception {
        logger.trace("save: " + record);
        //response.setStatus(SC_CREATED);
        return recordService.save(record);
    }

    @PutMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Record update(@PathVariable("id") Long id, @NotNull @RequestBody Record record) throws Exception {
        logger.trace("update: " + record);
        //response.setStatus(SC_OK);
        return recordService.update(id, record);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public void delete(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("delete" + id);
        //response.setStatus(SC_NO_CONTENT);
        recordService.delete(id);
    }


    @GetMapping(value = "/products/{productId}", produces = {"application/json;charset=UTF-8"})
    public List<Record> queryByProductId(@NotNull @PathVariable("productId") Long productId) throws Exception {
        logger.trace("queryByProductId: " + productId);
        return recordService.queryByProductId(productId);
    }
}
