package au.edu.rmit.isys3413.sefbfit.controllers;

import au.edu.rmit.isys3413.sefbfit.models.User;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeController {

  @GetMapping("/")
  public String welcomePage(Model model) {
    model.addAttribute("date", new Date());
    model.addAttribute("user_logged_in", false);
    model.addAttribute("user", User.builder().name("test").email("test").build());
    return "welcome/index";
  }
}
