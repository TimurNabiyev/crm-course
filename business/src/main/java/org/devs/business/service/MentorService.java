package org.devs.business.service;

import org.devs.business.model.dto.MentorDto;
import org.devs.business.model.request.CreateMentorRequest;

public interface MentorService {

    MentorDto create(CreateMentorRequest mentor);
}
