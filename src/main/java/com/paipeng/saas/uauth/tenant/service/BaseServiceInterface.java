package com.paipeng.saas.uauth.tenant.service;

import java.util.List;

public interface BaseServiceInterface<T> {
    T query(Long taskId);
    List<T> query();
//    T save(T t);
//    T update(long taskId, T t);
    void delete(Long taskId);
}
