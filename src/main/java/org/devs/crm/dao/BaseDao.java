package org.devs.crm.dao;

public interface BaseDao<T> {

    T findById(Long id);
    T save(T t);

}
