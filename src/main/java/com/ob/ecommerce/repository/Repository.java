package com.ob.ecommerce.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    T save(T entity);
    Optional<T> getById(int id);
    void delete(T entity);
    List<T> getAll();
}