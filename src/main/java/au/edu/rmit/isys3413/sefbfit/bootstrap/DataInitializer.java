package au.edu.rmit.isys3413.sefbfit.bootstrap;

import au.edu.rmit.isys3413.sefbfit.models.AppUser;
import au.edu.rmit.isys3413.sefbfit.models.AppUserProgramProgress;
import au.edu.rmit.isys3413.sefbfit.models.Program;
import au.edu.rmit.isys3413.sefbfit.models.Role;
import au.edu.rmit.isys3413.sefbfit.models.Workout;
import au.edu.rmit.isys3413.sefbfit.models.workoutrecipe.Recipe;
import au.edu.rmit.isys3413.sefbfit.models.workoutrecipe.RecipeType;
import au.edu.rmit.isys3413.sefbfit.models.workoutrecipe.Unit;
import au.edu.rmit.isys3413.sefbfit.services.AppUserProgramProgressService;
import au.edu.rmit.isys3413.sefbfit.services.AppUserService;
import au.edu.rmit.isys3413.sefbfit.services.ProgramService;
import au.edu.rmit.isys3413.sefbfit.services.WorkoutService;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {
  private final WorkoutService workoutService;
  private final AppUserService appUserService;
  private final ProgramService programService;
  private final AppUserProgramProgressService appUserProgramProgressService;
  private static final String BASIC_WORKOUT_I = "Basic Workout I";
  private static final String BASIC_WORKOUT_II = "Basic Workout II";
  private static final String TREADMILL_WORKOUT_I = "Treadmill Workout I";
  private static final String TREADMILL_WORKOUT_II = "Treadmill Workout II";
  private static final String USER_ADMIN_EMAIL = "admin@test.com";
  private static final String USER_TUTOR_EMAIL = "tutor@rmit.edu.au";
  private static final String USER_USER_EMAIL = "user@test.com";
  private static final String FOURTEEN_DAY_WEIGHT_LOSE_PROGRAM = "14 Days weight lose program";
  private static final String FOURTEEN_DAY_WEIGHT_MAINTAIN_PROGRAM = "14 Days weight maintain program";
  private static final String FOURTEEN_DAY_BODY_BUILDING_PROGRAM = "14 Days body building program";
  private static final String FOURTEEN_DAY_BODY_MAINTAIN_PROGRAM = "14 Days body maintain program";

  public DataInitializer(WorkoutService workoutService, AppUserService appUserService, ProgramService programService,
      AppUserProgramProgressService appUserProgramProgressService) {
    this.workoutService = workoutService;
    this.appUserService = appUserService;
    this.programService = programService;
    this.appUserProgramProgressService = appUserProgramProgressService;
  }

  @Override
  public void run(String... args) {
    addWorkout();
    addProgram();
    addUser();
    usersRegisterPrograms();
  }

  private void addWorkout() {
    if (workoutService.getAll().size() > 0) {
      return;
    }

    Workout basicWorkoutI = Workout.builder()
        .name(BASIC_WORKOUT_I)
        .recipe(Recipe.builder().recipeType(RecipeType.SQUAT).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.PUSHUP).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.LUNGE).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.CHEST_PRESS).quantity(3).unit(Unit.SET).repetitions(20).build())
        .build();
    Workout basicWorkoutII = Workout.builder()
        .name(BASIC_WORKOUT_II)
        .recipe(Recipe.builder().recipeType(RecipeType.SQUAT).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.PUSHUP).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.LUNGE).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.CHEST_PRESS).quantity(3).unit(Unit.SET).repetitions(20).build())
        .recipe(Recipe.builder().recipeType(RecipeType.SQUAT).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.PUSHUP).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.LUNGE).quantity(3).unit(Unit.SET).repetitions(15).build())
        .recipe(Recipe.builder().recipeType(RecipeType.CHEST_PRESS).quantity(3).unit(Unit.SET).repetitions(20).build())
        .build();
    Workout treadmillWorkoutI = Workout.builder()
        .name(TREADMILL_WORKOUT_I)
        .recipe(Recipe.builder().recipeType(RecipeType.EASY_JOG).quantity(5).unit(Unit.MINUTE).build())
        .recipe(Recipe.builder().recipeType(RecipeType.SPRINT).quantity(30).unit(Unit.SECOND).build())
        .recipe(Recipe.builder().recipeType(RecipeType.EASY_JOG).quantity(90).unit(Unit.SECOND).build())
        .recipe(Recipe.builder().recipeType(RecipeType.COOL_DOWN).quantity(4).unit(Unit.MINUTE).build())
        .build();
    Workout treadmillWorkoutII = Workout.builder()
        .name(TREADMILL_WORKOUT_II)
        .recipe(Recipe.builder().recipeType(RecipeType.EASY_JOG).quantity(5).unit(Unit.MINUTE).build())
        .recipe(Recipe.builder().recipeType(RecipeType.SPRINT).quantity(30).unit(Unit.SECOND).build())
        .recipe(Recipe.builder().recipeType(RecipeType.EASY_JOG).quantity(90).unit(Unit.SECOND).build())
        .recipe(Recipe.builder().recipeType(RecipeType.SPRINT).quantity(30).unit(Unit.SECOND).build())
        .recipe(Recipe.builder().recipeType(RecipeType.EASY_JOG).quantity(90).unit(Unit.SECOND).build())
        .recipe(Recipe.builder().recipeType(RecipeType.SPRINT).quantity(30).unit(Unit.SECOND).build())
        .recipe(Recipe.builder().recipeType(RecipeType.COOL_DOWN).quantity(4).unit(Unit.MINUTE).build())
        .build();

    workoutService.save(basicWorkoutI);
    workoutService.save(basicWorkoutII);
    workoutService.save(treadmillWorkoutI);
    workoutService.save(treadmillWorkoutII);
  }

  private void addUser() {
    if (appUserService.getAll().size() > 0) {
      return;
    }
    AppUser admin =
        AppUser.builder().email(USER_ADMIN_EMAIL).name("Administrator").role(Role.ADMIN).password("admin").build();
    AppUser tutor =
        AppUser.builder().email(USER_TUTOR_EMAIL).name("SEF Tutor").role(Role.ADMIN).password("tutor").build();
    AppUser customer =
        AppUser.builder().email(USER_USER_EMAIL).name("Customer").role(Role.USER).password("user").build();

    appUserService.save(admin);
    appUserService.save(tutor);
    appUserService.save(customer);
  }

  private void addProgram() {
    if (programService.getAll().size() > 0) {
      return;
    }
    Program fourteenDayWeightLoseProgram = Program.builder()
        .name(FOURTEEN_DAY_WEIGHT_LOSE_PROGRAM)
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_II))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_II))
        .build();
    Program fourteenDayWeightMaintainProgram = Program.builder()
        .name(FOURTEEN_DAY_WEIGHT_MAINTAIN_PROGRAM)
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .workout(workoutService.getByName(TREADMILL_WORKOUT_I))
        .build();
    Program fourteenDayBodyBuildingProgram = Program.builder()
        .name(FOURTEEN_DAY_BODY_BUILDING_PROGRAM)
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_II))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_II))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_II))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_II))
        .build();
    Program fourteenDayBodyMaintainProgram = Program.builder()
        .name(FOURTEEN_DAY_BODY_MAINTAIN_PROGRAM)
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .workout(workoutService.getByName(BASIC_WORKOUT_I))
        .build();

    programService.save(fourteenDayWeightLoseProgram);
    programService.save(fourteenDayWeightMaintainProgram);
    programService.save(fourteenDayBodyBuildingProgram);
    programService.save(fourteenDayBodyMaintainProgram);
  }

  private void usersRegisterPrograms() {
    if (appUserProgramProgressService.getAll().size() > 0) {
      return;
    }

    AppUser admin = appUserService.getByEmail(USER_ADMIN_EMAIL);
    AppUser tutor = appUserService.getByEmail(USER_TUTOR_EMAIL);
    AppUser customer = appUserService.getByEmail(USER_USER_EMAIL);

    Program weightLoseProgram = programService.getByName(FOURTEEN_DAY_WEIGHT_LOSE_PROGRAM);
    Program weightMaintainProgram = programService.getByName(FOURTEEN_DAY_WEIGHT_MAINTAIN_PROGRAM);
    Program bodyBuildingProgram = programService.getByName(FOURTEEN_DAY_BODY_BUILDING_PROGRAM);
    Program bodyMaintainProgram = programService.getByName(FOURTEEN_DAY_BODY_MAINTAIN_PROGRAM);

    AppUserProgramProgress adminWeightLoseProgress0 = AppUserProgramProgress.builder()
        .appUser(admin)
        .program(weightLoseProgram)
        .workoutDate(LocalDate.of(2020, 9, 12))
        .workoutIdxInProgram(0)
        .build();
    AppUserProgramProgress adminWeightLoseProgress1 = AppUserProgramProgress.builder()
        .appUser(admin)
        .program(weightLoseProgram)
        .workoutDate(LocalDate.of(2020, 9, 13))
        .workoutIdxInProgram(1)
        .build();
    AppUserProgramProgress adminWeightLoseProgress2 = AppUserProgramProgress.builder()
        .appUser(admin)
        .program(weightLoseProgram)
        .workoutDate(LocalDate.of(2020, 9, 15))
        .workoutIdxInProgram(2)
        .build();
    AppUserProgramProgress adminBodyMaintainProgress0 = AppUserProgramProgress.builder()
        .appUser(admin)
        .program(bodyMaintainProgram)
        .workoutDate(LocalDate.of(2020, 8, 25))
        .workoutIdxInProgram(0)
        .build();
    AppUserProgramProgress adminBodyMaintainProgress1 = AppUserProgramProgress.builder()
        .appUser(admin)
        .program(bodyMaintainProgram)
        .workoutDate(LocalDate.of(2020, 8, 27))
        .workoutIdxInProgram(1)
        .build();
    AppUserProgramProgress adminBodyMaintainProgress2 = AppUserProgramProgress.builder()
        .appUser(admin)
        .program(bodyMaintainProgram)
        .workoutDate(LocalDate.of(2020, 9, 1))
        .workoutIdxInProgram(4)
        .build();
    AppUserProgramProgress adminBodyMaintainProgress3 = AppUserProgramProgress.builder()
        .appUser(admin)
        .program(bodyMaintainProgram)
        .workoutDate(LocalDate.of(2020, 9, 8))
        .workoutIdxInProgram(6)
        .build();
    AppUserProgramProgress tutorWeightMaintainProgress0 = AppUserProgramProgress.builder()
        .appUser(tutor)
        .program(weightMaintainProgram)
        .workoutDate(LocalDate.of(2020, 9, 11))
        .workoutIdxInProgram(0)
        .build();
    AppUserProgramProgress tutorWeightMaintainProgress1 = AppUserProgramProgress.builder()
        .appUser(tutor)
        .program(weightMaintainProgram)
        .workoutDate(LocalDate.of(2020, 9, 13))
        .workoutIdxInProgram(1)
        .build();
    AppUserProgramProgress tutorBodyBuildingProgress0 = AppUserProgramProgress.builder()
        .appUser(admin)
        .program(bodyBuildingProgram)
        .workoutDate(LocalDate.of(2020, 9, 6))
        .workoutIdxInProgram(0)
        .build();
    AppUserProgramProgress tutorBodyBuildingProgress1 = AppUserProgramProgress.builder()
        .appUser(admin)
        .program(bodyBuildingProgram)
        .workoutDate(LocalDate.of(2020, 9, 10))
        .workoutIdxInProgram(1)
        .build();
    AppUserProgramProgress tutorBodyBuildingProgress2 = AppUserProgramProgress.builder()
        .appUser(admin)
        .program(bodyBuildingProgram)
        .workoutDate(LocalDate.of(2020, 9, 14))
        .workoutIdxInProgram(3)
        .build();

    AppUserProgramProgress customerWeightLoseProgress0 = AppUserProgramProgress.builder()
        .appUser(customer)
        .program(weightLoseProgram)
        .workoutDate(LocalDate.of(2020, 9, 6))
        .workoutIdxInProgram(0)
        .build();
    AppUserProgramProgress customerWeightLoseProgress1 = AppUserProgramProgress.builder()
        .appUser(customer)
        .program(weightLoseProgram)
        .workoutDate(LocalDate.of(2020, 9, 10))
        .workoutIdxInProgram(2)
        .build();
    AppUserProgramProgress customerBodyBuildingProgress0 = AppUserProgramProgress.builder()
        .appUser(customer)
        .program(bodyBuildingProgram)
        .workoutDate(LocalDate.of(2020, 9, 9))
        .workoutIdxInProgram(0)
        .build();
    AppUserProgramProgress customerBodyBuildingProgress1 = AppUserProgramProgress.builder()
        .appUser(customer)
        .program(bodyBuildingProgram)
        .workoutDate(LocalDate.of(2020, 9, 13))
        .workoutIdxInProgram(2)
        .build();

    appUserProgramProgressService.save(adminWeightLoseProgress0);
    appUserProgramProgressService.save(adminWeightLoseProgress1);
    appUserProgramProgressService.save(adminWeightLoseProgress2);

    appUserProgramProgressService.save(adminBodyMaintainProgress0);
    appUserProgramProgressService.save(adminBodyMaintainProgress1);
    appUserProgramProgressService.save(adminBodyMaintainProgress2);
    appUserProgramProgressService.save(adminBodyMaintainProgress3);

    appUserProgramProgressService.save(tutorWeightMaintainProgress0);
    appUserProgramProgressService.save(tutorWeightMaintainProgress1);

    appUserProgramProgressService.save(tutorBodyBuildingProgress0);
    appUserProgramProgressService.save(tutorBodyBuildingProgress1);
    appUserProgramProgressService.save(tutorBodyBuildingProgress2);

    appUserProgramProgressService.save(customerWeightLoseProgress0);
    appUserProgramProgressService.save(customerWeightLoseProgress1);

    appUserProgramProgressService.save(customerBodyBuildingProgress0);
    appUserProgramProgressService.save(customerBodyBuildingProgress1);
  }
}
