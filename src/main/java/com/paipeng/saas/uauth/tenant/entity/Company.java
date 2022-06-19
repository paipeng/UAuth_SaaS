package com.paipeng.saas.uauth.tenant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Component
@Entity
@Table(name = "company")
public class Company extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss z", timezone = "GMT+2")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @Column(name = "expire", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Timestamp expire;

    @JsonBackReference("company-users")
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "company")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    private List<User> users;


    @JsonBackReference("company-products")
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "company")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    private List<Product> products;


    @JsonBackReference("company-devices")
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "company")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    private List<Device> devices;

    @Column(name = "license_count", nullable = false)
    private int licenseCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public int getLicenseCount() {
        return licenseCount;
    }

    public void setLicenseCount(int licenseCount) {
        this.licenseCount = licenseCount;
    }

    public Timestamp getExpire() {
        return expire;
    }

    public void setExpire(Timestamp expire) {
        this.expire = expire;
    }
}
