package org.devs.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/group")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET)
    public String someGetMethod() {
        return "Hello, Apis!";
    }
}