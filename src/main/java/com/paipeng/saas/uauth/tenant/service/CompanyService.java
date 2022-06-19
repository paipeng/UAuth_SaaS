package com.paipeng.saas.uauth.tenant.service;


import com.paipeng.saas.uauth.tenant.entity.Company;
import com.paipeng.saas.uauth.tenant.repository.CompanyRepository;
import com.paipeng.saas.uauth.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService extends BaseService{

    @Autowired
    private CompanyRepository companyRepository;

    public Company query(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Company> query() {
        return companyRepository.findAll();
    }

    public Company save(Company company) {
        company = companyRepository.saveAndFlush(company);
        return company;
    }

    public Company update(Long companyId, Company company) {
        Company foundCompany = query(companyId);
        if (foundCompany == null) {
            throw new SC_NOT_FOUND();
        }
        foundCompany.setName(company.getName());
        foundCompany.setLicenseCount(company.getLicenseCount());
        foundCompany.setExpire(company.getExpire());
        company = companyRepository.saveAndFlush(foundCompany);
        return company;
    }

    public void delete(Long companyId) {
        Company foundCompany = query(companyId);
        if (foundCompany == null) {
            throw new SC_NOT_FOUND();
        }
        companyRepository.delete(foundCompany);
    }
}
