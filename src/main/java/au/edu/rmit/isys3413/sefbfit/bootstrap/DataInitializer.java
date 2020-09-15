package au.edu.rmit.isys3413.sefbfit.bootstrap;

import au.edu.rmit.isys3413.sefbfit.models.AppUser;
import au.edu.rmit.isys3413.sefbfit.models.Role;
import au.edu.rmit.isys3413.sefbfit.models.workoutrecipe.Recipe;
import au.edu.rmit.isys3413.sefbfit.models.workoutrecipe.RecipeType;
import au.edu.rmit.isys3413.sefbfit.models.workoutrecipe.Unit;
import au.edu.rmit.isys3413.sefbfit.models.Workout;
import au.edu.rmit.isys3413.sefbfit.services.AppUserService;
import au.edu.rmit.isys3413.sefbfit.services.WorkoutService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {
  private final WorkoutService workoutService;
  private final AppUserService appUserService;

  public DataInitializer(WorkoutService workoutService, AppUserService appUserService) {
    this.workoutService = workoutService;
    this.appUserService = appUserService;
  }

  @Override
  public void run(String... args) {
    addWorkout();
    addUser();
  }

  private void addWorkout() {
    Workout basicWorkoutI = Workout.builder()
        .name("Basic Workout I")
        .recipe(Recipe.builder().recipeType(RecipeType.SQUAT).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.PUSHUP).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.LUNGE).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.CHEST_PRESS).quantity(3).unit(Unit.SET).repetitions(20).build())
        .build();
    Workout treadmillWorkoutI = Workout.builder()
        .name("Treadmill Workout I")
        .recipe(Recipe.builder().recipeType(RecipeType.EASY_JOG).quantity(5).unit(Unit.MINUTE).build())
        .recipe(Recipe.builder().recipeType(RecipeType.SPRINT).quantity(30).unit(Unit.SECOND).build())
        .recipe(Recipe.builder().recipeType(RecipeType.EASY_JOG).quantity(90).unit(Unit.SECOND).build())
        .recipe(Recipe.builder().recipeType(RecipeType.COOL_DOWN).quantity(4).unit(Unit.MINUTE).build())
        .build();
    if (workoutService.getAll().size() == 0) {
      workoutService.save(basicWorkoutI);
      workoutService.save(treadmillWorkoutI);
    }
  }

  private void addUser() {
    AppUser admin = AppUser.builder()
        .email("admin@test.com")
        .name("Administrator")
        .role(Role.ADMIN)
        .password("admin")
        .build();
    AppUser tutor = AppUser.builder()
        .email("tutor@rmit.edu.au")
        .name("SEF Tutor")
        .role(Role.ADMIN)
        .password("tutor")
        .build();
    AppUser customer = AppUser.builder()
        .email("user@test.com")
        .name("Customer")
        .role(Role.USER)
        .password("user")
        .build();
    if (appUserService.getAll().size() == 0) {
      appUserService.save(admin);
      appUserService.save(tutor);
      appUserService.save(customer);
    }
  }
}
