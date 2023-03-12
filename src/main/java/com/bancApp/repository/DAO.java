package com.bancApp.repository;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, K extends Serializable> {
    void save(T entity);

    T findById(K id);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);
}
