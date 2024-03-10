package org.example.springboot.demoone.Controller;

import org.example.springboot.demoone.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.example.springboot.demoone.Model.User;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/new")
    public String addNewUser (Model model) {
        model.addAttribute("user", new User());
        return "userInfo";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userInfo";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("")
    public String showUsers (Model model) {
        List <User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "showUsers";
    }

    @GetMapping("/delete")
    public String deleteUserFrom (Model model,@RequestParam("id") Integer id) {
        model.addAttribute("user", userService.getUserById(id));
        return "deleteUser";
    }

    @PostMapping("/delete")
    public String deleteUser (@RequestParam ("id") Integer id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String updateUserFrom (@RequestParam ("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/update")
    public String updateUSer (@ModelAttribute("user") @Validated User UpdateUser, BindingResult bindingResult) {
        userService.updateUser(UpdateUser);
        if (bindingResult.hasErrors()) {
            return "updateUser";
        }
        return "redirect:/users";
    }

}
