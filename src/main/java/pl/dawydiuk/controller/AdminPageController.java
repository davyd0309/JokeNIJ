package pl.dawydiuk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.service.UserService;

import javax.ws.rs.GET;
import java.util.List;

@Controller
public class AdminPageController {


    private UserService userService;


    @Autowired
    public AdminPageController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @RequestMapping(value = "/admin/main")
    @Secured(value = {"ROLE_ADMIN"})
    public String showAdminPanel() {
        return "admin/admin";
    }


    @GET
    @RequestMapping(value = "/admin/users")
    @Secured(value = {"ROLE_ADMIN"})
    public String getAllUsers(Model model) {

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("userList", allUsers);

        return "admin/users";
    }


}
