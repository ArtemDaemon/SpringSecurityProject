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
 * Controller for handling login, logout, and signup forms.
 */
@Controller
public class AccountController {
    @Autowired
    UsersService usersService;

    /**
     * Displays the login form.
     *
     * @return the name of the login view
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    /**
     * Handles the logout process by redirecting to the login page.
     *
     * @return the redirect path to the login view
     */
    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    /**
     * Displays the signup form.
     *
     * @return the name of the signup view
     */
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    /**
     * Processes the signup form, creating a new user if the username is not already taken.
     * Adds an error message to the model if the username or password is empty, or if the username is already taken.
     *
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @param model    the MVC model
     * @return the redirect path to the login view if successful, or the name of the signup view if there is an error
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
