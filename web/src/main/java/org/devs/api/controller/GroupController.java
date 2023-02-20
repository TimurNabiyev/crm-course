package org.devs.api.controller;

import lombok.RequiredArgsConstructor;
import org.devs.business.model.dto.GroupDto;
import org.devs.business.model.request.CreateGroupRequest;
import org.devs.business.service.GroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/group")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/create")
    public GroupDto create(@RequestBody CreateGroupRequest request) {
        return groupService.create(request);
    }

    @GetMapping("/get/{id}")
    public GroupDto getOne(@PathVariable Long id) {
        return groupService.getOne(id);
    }



}