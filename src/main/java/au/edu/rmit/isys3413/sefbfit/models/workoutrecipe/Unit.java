package au.edu.rmit.isys3413.sefbfit.models.workoutrecipe;

import java.util.EnumSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public enum Unit {
  SET("Sets"), MINUTE("minutes"), SECOND("seconds");

  private String displayName;
  private static final Map<String, Unit> displayNameMap =
      EnumSet.allOf(Unit.class).stream().collect(Collectors.toMap(Unit::toDisplayName, Function.identity()));

  Unit(String displayName) {
    this.displayName = displayName;
  }

  public String toDisplayName() {
    return this.displayName;
  }

  public static Unit fromDisplayName(String displayName) {
    return displayNameMap.get(displayName);
  }
}
