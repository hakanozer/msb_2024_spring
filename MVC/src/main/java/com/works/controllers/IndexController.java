package com.works.controllers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class IndexController {

    final CustomerService customerService;

    @GetMapping("/")
    public String index(Model model) {
        //model.addAttribute("title", "New Title");
        return "index";
    }

    /*
    @PostMapping("login")
    public String login(@RequestParam String username, @RequestParam String password) {
        System.out.println(username);
        System.out.println(password);
        return "index";
    }
     */

    @PostMapping("login")
    public String login(Customer customer, Model model) {
        boolean status = customerService.login(customer);
        if(!status) {
            model.addAttribute("error", "Username or Password fail!");
        }
        return status ? "redirect:/dashboard" : "index";
    }

    @GetMapping("logout")
    public String logout(){
        customerService.logout();
        return "redirect:/";
    }

}
