package net.homecredit.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
@Configuration
public class HomeController {

    @Bean
    public User createUser(){
        return new User("TESTONOME");
    }

    @Autowired
    private User defaultUser;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        System.out.println("Home Page Requested");

        model.addAttribute("defaultUser", defaultUser);

        return "home";
    }
}
