package org.devs.crm.dao.exception;

import org.springframework.dao.DataAccessException;

public class NullParameterPassedException extends DataAccessException {
    public NullParameterPassedException(String msg) {
        super(msg);
    }
}
