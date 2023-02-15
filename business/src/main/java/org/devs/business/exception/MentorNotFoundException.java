package org.devs.business.exception;

import org.devs.business.model.dto.MentorDto;

public class MentorNotFoundException extends RuntimeException {
    public MentorNotFoundException(String message) {
        super(message);
    }
}
