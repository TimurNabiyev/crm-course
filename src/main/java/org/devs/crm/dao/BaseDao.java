package org.devs.crm.dao;

import java.util.Optional;

public interface BaseDao<T> {

    Optional<T> findById(Long id);
    T save(T t);

}
