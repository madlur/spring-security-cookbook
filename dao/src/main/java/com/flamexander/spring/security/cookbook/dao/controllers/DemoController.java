package com.flamexander.spring.security.cookbook.dao.controllers;

import com.flamexander.spring.security.cookbook.dao.entities.User;
import com.flamexander.spring.security.cookbook.dao.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/unsecured")
    public String usecuredPage() {
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authenticatedPage() {
        return "authenticated";
    }

    @GetMapping("/admin")
    // @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/user_info")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        Collection listOfRoles = Arrays.asList(user.getRoles());
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail() ;
    }

    @GetMapping("/read")
    // @PreAuthorize("hasRole('ADMIN')")
    public String readPage() {
        return "read";
    }

    @GetMapping("/write")
    // @PreAuthorize("hasRole('ADMIN')")
    public String writePage() {
        return "write";
    }

    @GetMapping("/delete")
    // @PreAuthorize("hasRole('ADMIN')")
    public String deletePage() {
        return "delete";
    }

}
