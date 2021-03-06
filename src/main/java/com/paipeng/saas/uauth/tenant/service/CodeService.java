package com.paipeng.saas.uauth.tenant.service;

import com.paipeng.saas.uauth.tenant.entity.Code;
import com.paipeng.saas.uauth.tenant.entity.Product;
import com.paipeng.saas.uauth.tenant.repository.CodeRepository;
import com.paipeng.saas.uauth.tenant.repository.ProductRepository;
import com.paipeng.saas.uauth.util.exception.SC_BAD_REQUEST;
import com.paipeng.saas.uauth.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService extends BaseService {
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private ProductRepository productRepository;

    public Code query(Long codeId) {
        return codeRepository.findById(codeId).orElse(null);
    }

    public List<Code> query() {
        return codeRepository.findAll();
    }

    public Code save(Code code) {
        if (code.getProduct() == null) {
            throw new SC_BAD_REQUEST();
        }
        return codeRepository.saveAndFlush(code);
    }

    public Code update(Long productId, Code code) {
        Code foundCode = query(productId);
        if (foundCode == null) {
            throw new SC_NOT_FOUND();
        }
        foundCode.setName(code.getName());
        foundCode.setLatitude(code.getLatitude());
        foundCode.setState(code.getState());
        foundCode.setLongitude(code.getLongitude());
        foundCode.setSerialNumber(code.getSerialNumber());
        foundCode.setType(code.getType());
        logger.trace("foundCode: " + foundCode.getProduct().getName());
        return codeRepository.saveAndFlush(foundCode);
    }

    public void delete(Long taskId) {
        Code foundCode = query(taskId);
        if (foundCode == null) {
            throw new SC_NOT_FOUND();
        }
        codeRepository.delete(foundCode);
    }

    public Code queryBySerialNumber(String serialNumber) {
        Code code = codeRepository.findBySerialNumber(serialNumber);
        return code;
    }

    public List<Code> queryCodesByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            return codeRepository.findAllByProductId(productId);
        } else {
            throw new SC_NOT_FOUND();
        }
    }
}
