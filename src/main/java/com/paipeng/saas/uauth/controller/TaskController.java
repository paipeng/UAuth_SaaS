package com.paipeng.saas.uauth.controller;

import com.paipeng.saas.uauth.tenant.entity.Product;
import com.paipeng.saas.uauth.tenant.service.ProductService;
import com.paipeng.saas.uauth.util.exception.SC_NOT_FOUND;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController extends BaseController {
    @Autowired
    private ProductService taskService;

    @GetMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Product query(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("query: " + id);
        Product Task = taskService.query(id);
        if (Task == null) {
            throw new SC_NOT_FOUND();
        }
        return Task;
    }

    @GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public List<Product> query() throws Exception {
        logger.trace("query all");
        return taskService.query();
    }

    @PostMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public Product save(@NotNull @RequestBody Product Task) throws Exception {
        logger.trace("save: " + Task);
        //response.setStatus(SC_CREATED);
        return taskService.save(Task);
    }

    @PutMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Product update(@PathVariable("id") Long id, @NotNull @RequestBody Product task) throws Exception {
        logger.trace("update: " + task);
        //response.setStatus(SC_OK);
        return taskService.update(id, task);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public void delete(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("delete" + id);
        //response.setStatus(SC_NO_CONTENT);
        taskService.delete(id);
    }
}
