package vn.edu.tdtu.javatech.Lab8;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
    
    @PostMapping("/contact")
    public String processContactForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            Model model
    ) {
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        return "contact-result";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

}
