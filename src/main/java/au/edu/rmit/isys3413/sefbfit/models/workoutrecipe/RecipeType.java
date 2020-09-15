package au.edu.rmit.isys3413.sefbfit.models.workoutrecipe;

import java.util.EnumSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public enum RecipeType {

  SQUAT("Squat"),
  PUSHUP("Pushup"),
  CHEST_PRESS("Chest press"),
  LUNGE("Lunges"),
  CRUNCH("Crunch"),
  EASY_JOG("Easy jog"),
  SPRINT("Sprint"),
  COOL_DOWN("Cool down");

  private final String displayName;
  private static final Map<String, RecipeType> displayNameMap = EnumSet.allOf(RecipeType.class)
      .stream()
      .collect(Collectors.toMap(RecipeType::toDisplayName, Function.identity()));

  RecipeType(String displayName) {
    this.displayName = displayName;
  }

  public String toDisplayName() {
    return this.displayName;
  }

  public static RecipeType fromDisplayName(String displayName) {
    return displayNameMap.get(displayName);
  }
}
