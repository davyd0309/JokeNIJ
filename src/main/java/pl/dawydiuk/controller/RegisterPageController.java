package pl.dawydiuk.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dawydiuk.domain.User;

import javax.ws.rs.GET;

@Controller
public class RegisterPageController {

    @GET
    @RequestMapping("/register")
    public String showRegisterPage(Model model) {

        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

}
