package org.devs.business;

import org.devs.business.config.BusinessConfig;
import org.devs.business.model.dto.GroupDto;
import org.devs.business.model.request.CreateGroupRequest;
import org.devs.business.service.GroupService;
import org.devs.business.service.impl.GroupServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;

public class BusinessClass {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BusinessConfig.class);

        GroupService groupService = applicationContext.getBean(GroupServiceImpl.class);

        CreateGroupRequest request = new CreateGroupRequest();
        request.setCourseId(null);
        request.setMentorsIds(Arrays.asList(1L, 2L, 3L, 4L));
        request.setGroupName("Apple");
        request.setStartDate(LocalDate.now().plusDays(7));
        request.setStudentsIds(Arrays.asList(1L, 2L, 3L, 4L));

        GroupDto dto = groupService.create(request);

        System.out.println(groupService.getOne(7L));
    }
}
