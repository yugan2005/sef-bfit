package au.edu.rmit.isys3413.sefbfit.controllers;

import au.edu.rmit.isys3413.sefbfit.models.AppUser;
import au.edu.rmit.isys3413.sefbfit.models.AppUserProgramProgress;
import au.edu.rmit.isys3413.sefbfit.models.Program;
import au.edu.rmit.isys3413.sefbfit.services.AppUserService;
import au.edu.rmit.isys3413.sefbfit.validator.AppUserValidator;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
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

  @ModelAttribute
  public void addUserInfo(Principal principal, Model model) {
    if (principal != null) {
      String email = principal.getName();
      AppUser user = appUserService.getByEmail(email);
      model.addAttribute("user", user);
    }
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

  @GetMapping("/users/profile")
  public String getUserProfile(Model model) {
    AppUser appUser = (AppUser) model.getAttribute("user");
    if (appUser == null) {
      return "auth/login";
    }
    List<AppUserProgramProgress> programProgresses = appUser.getAppUserProgramProgresses();
    Map<Program, Map<Integer, LocalDate>> programDetails = programProgresses.stream()
        .collect(Collectors.groupingBy(AppUserProgramProgress::getProgram,
            Collectors.toMap(AppUserProgramProgress::getWorkoutIdxInProgram, AppUserProgramProgress::getWorkoutDate)));
    model.addAttribute("programDetails", programDetails);
    return "users/me";
  }
}
