package org.example.controllers;

import org.example.models.User;
import org.example.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for login/out and signup forms
 */
@Controller
public class AccountController {
    @Autowired
    UsersService usersService;

    /**
     * Method to get login view
     * @return Name of login view
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    /**
     * Method for redirecting when logout
     * @return Path to redirect
     */
    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    /**
     * Method to get signup view
     * @return Name of signup view
     */
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    /**
     * Method to processing signup form
     * @param username
     * @param password
     * @param model MVC model
     * @return Path to redirect or name of signup view
     */
    @PostMapping("/signup")
    public String signup(@RequestParam(name="username", required = true) String username, @RequestParam(name = "password", required = true) String password, Model model) {
        if(username == null || password == null) {
            model.addAttribute("error", "Username and password shouldn't be empty");
            return "signup";
        }

        if(usersService.loadUserByUsername(username) != null) {
            model.addAttribute("error", "This username is already occupied");
            return "signup";
        }
        usersService.createUser(new User(username, password));
        return "redirect:/login";
    }
}
