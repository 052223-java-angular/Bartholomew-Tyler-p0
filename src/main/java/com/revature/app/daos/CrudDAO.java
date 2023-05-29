package com.revature.app.daos;

import java.util.List;

public interface CrudDAO<T> {
    void save(T obj);

    void update(T obj);

    void delete(String id);

    T findById(String id);

    List<T> findAll();
}
