package org.devs.business.mapper;


/**
 *
 * @param <T> Entity object
 * @param <S> Dto object
 */
public interface BaseMapper<T, S> {

    T toEntity(S dto);
    S toDto(T entity);

}
