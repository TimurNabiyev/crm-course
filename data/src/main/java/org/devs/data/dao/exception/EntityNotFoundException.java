package org.devs.data.dao.exception;

import org.springframework.dao.DataAccessException;

public class EntityNotFoundException extends DataAccessException {
    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
