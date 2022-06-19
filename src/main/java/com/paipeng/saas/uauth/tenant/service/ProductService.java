package com.paipeng.saas.uauth.tenant.service;

import com.paipeng.saas.uauth.tenant.entity.Product;
import com.paipeng.saas.uauth.tenant.entity.User;
import com.paipeng.saas.uauth.tenant.repository.ProductRepository;
import com.paipeng.saas.uauth.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends BaseService {
    @Autowired
    private ProductRepository productRepository;

    public Product query(Long taskId) {
        return productRepository.findById(taskId).orElse(null);
    }

    public List<Product> query() {
        return productRepository.findAll();
    }

    public Product save(Product task) {
        if (task.getCompany() == null) {
            User user = getUserFromSecurity();
            task.setCompany(user.getCompany());
        }
        return productRepository.saveAndFlush(task);
    }

    public Product update(Long taskId, Product task) {
        Product foundTask = query(taskId);
        if (foundTask == null) {
            throw new SC_NOT_FOUND();
        }
        foundTask.setName(task.getName());
        foundTask.setDescription(task.getDescription());
        foundTask.setState(task.getState());
        logger.trace("foundProduct: " + foundTask.getCompany().getName());
        return productRepository.saveAndFlush(foundTask);
    }

    public void delete(Long taskId) {
        Product foundTask = query(taskId);
        if (foundTask == null) {
            throw new SC_NOT_FOUND();
        }
        productRepository.delete(foundTask);
    }
}
