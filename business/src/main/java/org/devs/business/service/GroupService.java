package org.devs.business.service;

import org.devs.business.model.dto.GroupDto;
import org.devs.business.model.request.CreateGroupRequest;

public interface GroupService {

    GroupDto create(CreateGroupRequest group);
    GroupDto getOne(Long id);

}
