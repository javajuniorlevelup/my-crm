package ru.levelup.mycrm.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/users")
public class UsersController {

    @GetMapping
//    @Secured("ADMIN")
    @RolesAllowed("ADMIN")
    public String index() {
        return "users";
    }
}
