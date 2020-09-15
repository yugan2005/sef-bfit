package au.edu.rmit.isys3413.sefbfit.controllers;

import au.edu.rmit.isys3413.sefbfit.models.AppUser;
import au.edu.rmit.isys3413.sefbfit.services.AppUserService;
import au.edu.rmit.isys3413.sefbfit.validator.AppUserValidator;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Slf4j
public class AppUserController {
  private final AppUserService appUserService;
  private final AppUserValidator appUserValidator;

  public AppUserController(AppUserService appUserService, AppUserValidator appUserValidator) {
    this.appUserService = appUserService;
    this.appUserValidator = appUserValidator;
  }

  @GetMapping("/signup")
  public String signup(Model model) {
    model.addAttribute("user", AppUser.builder().build());
    return "auth/signup";
  }

  @PostMapping("/signup")
  public String register(@ModelAttribute("user") AppUser appUser, BindingResult bindingResult) {
    appUserValidator.validate(appUser, bindingResult);

    if (bindingResult.hasErrors()) {
      return "auth/signup";
    }

    appUserService.save(appUser);

    //TODO
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("user", AppUser.builder().build());
    return "auth/login";
  }

  @GetMapping("/loginError")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    model.addAttribute("user", AppUser.builder().build());
    return "auth/login";
  }

//  @PostMapping("/auth")
//  public String authorization(@ModelAttribute("user") AppUser appUser, BindingResult bindingResult) {
//    log.error("login post");
//    log.error(appUser.toString());
//    if (securityService.authenticate(appUser)) {
//      log.error("authenticated");
//    } else {
//      log.error("not authenticated");
//    }
//    return "welcome/index";
//  }
}
