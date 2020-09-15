package au.edu.rmit.isys3413.sefbfit.controllers;

import au.edu.rmit.isys3413.sefbfit.models.AppUser;
import au.edu.rmit.isys3413.sefbfit.models.Workout;
import au.edu.rmit.isys3413.sefbfit.models.workoutrecipe.Recipe;
import au.edu.rmit.isys3413.sefbfit.services.AppUserService;
import au.edu.rmit.isys3413.sefbfit.services.WorkoutService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/workouts")
@Slf4j
public class WorkoutController {
  private final WorkoutService workoutService;
  private final AppUserService appUserService;

  public WorkoutController(WorkoutService workoutService, AppUserService appUserService) {
    this.workoutService = workoutService;
    this.appUserService = appUserService;
  }

  @ModelAttribute
  public void addUserInfo(Principal principal, Model model) {
    if (principal != null) {
      String email = principal.getName();
      AppUser user = appUserService.getByEmail(email);
      model.addAttribute("user", user);
    }
  }

  @GetMapping({"", "/"})
  public String listWorkouts(Model model) {
    model.addAttribute("workouts", workoutService.getAll());
    return "workouts/list";
  }

  @GetMapping("/{id}")
  public String getWorkout(@PathVariable Long id, Model model) {
    Workout workout = workoutService.getById(id);
    model.addAttribute("workout", workout);
    return "workouts/show";
  }

  @GetMapping("/new")
  public String addWorkout(Model model) {
    Workout workout = Workout.builder().build();
    model.addAttribute("user_logged_in", false);
    model.addAttribute("user", AppUser.builder().name("test").email("test").build());
    model.addAttribute("workout", workout);
    return "workouts/new";
  }

  @GetMapping("/edit")
  public String editWorkout(@RequestParam Long id, Model model) {
    Workout workout = workoutService.getById(id);
    model.addAttribute("workout", workout);
    return "workouts/edit";
  }

  @PostMapping("/edit")
  public String updateWorkout(final Workout workout, Model model) {
    Workout savedWorkout = workoutService.save(workout);
    model.addAttribute("workout", savedWorkout);
    return "workouts/show";
  }

  @PostMapping(value = "/edit", params = {"addRecipe"})
  public String addRecipe(final Workout workout) {
    workout.addRecipe(Recipe.builder().build());
    return "workouts/edit";
  }

  @PostMapping(value = "/edit", params = {"removeRecipe"})
  public String removeRecipe(final Workout workout, final HttpServletRequest request) {
    int removeRecipeIndex = Integer.parseInt(request.getParameter("removeRecipe"));
    workout.removeRecipeByIndex(removeRecipeIndex);
    return "workouts/edit";
  }
}
