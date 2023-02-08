package org.devs.crm.dao.exception;

import org.springframework.dao.DataAccessException;

public class InvalidIdException extends DataAccessException {
    public InvalidIdException(String msg) {
        super(msg);
    }
}
