package com.jedivision.controller;

import com.jedivision.entity.User;
import com.jedivision.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(final ModelMap model) {
        LOGGER.debug("Index page");
        model.addAttribute("usersWithoutCurrent", userService.findUsersExceptCurrentUser());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", User.builder().build());
        return Pages.INDEX;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String save(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/index";
    }

}
