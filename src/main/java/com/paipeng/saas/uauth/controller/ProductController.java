package com.paipeng.saas.uauth.controller;

import com.paipeng.saas.uauth.tenant.entity.Product;
import com.paipeng.saas.uauth.tenant.service.ProductService;
import com.paipeng.saas.uauth.util.exception.SC_NOT_FOUND;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Product query(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("query: " + id);
        Product Task = productService.query(id);
        if (Task == null) {
            throw new SC_NOT_FOUND();
        }
        return Task;
    }

    @GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public List<Product> query() throws Exception {
        logger.trace("query all");
        return productService.query();
    }

    @PostMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public Product save(@NotNull @RequestBody Product product) throws Exception {
        logger.trace("save: " + product);
        //response.setStatus(SC_CREATED);
        return productService.save(product);
    }

    @PutMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public Product update(@PathVariable("id") Long id, @NotNull @RequestBody Product product) throws Exception {
        logger.trace("update: " + product);
        //response.setStatus(SC_OK);
        return productService.update(id, product);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"})
    public void delete(@NotNull @PathVariable("id") Long id) throws Exception {
        logger.trace("delete" + id);
        //response.setStatus(SC_NO_CONTENT);
        productService.delete(id);
    }
}
