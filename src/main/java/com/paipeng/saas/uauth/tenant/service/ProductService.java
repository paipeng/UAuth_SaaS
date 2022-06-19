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

    public Product query(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> query() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        if (product.getCompany() == null) {
            User user = getUserFromSecurity();
            product.setCompany(user.getCompany());
        }
        return productRepository.saveAndFlush(product);
    }

    public Product update(Long productId, Product product) {
        Product foundTask = query(productId);
        if (foundTask == null) {
            throw new SC_NOT_FOUND();
        }
        foundTask.setName(product.getName());
        foundTask.setDescription(product.getDescription());
        foundTask.setState(product.getState());
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
