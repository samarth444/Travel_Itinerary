package com.travel.travel_itinerary.controller;

import com.travel.travel_itinerary.model.User;
import com.travel.travel_itinerary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already exists!");
            return "auth/register";
        }
        userService.createUser(user);
        return "redirect:/auth/login?success";
    }
}
