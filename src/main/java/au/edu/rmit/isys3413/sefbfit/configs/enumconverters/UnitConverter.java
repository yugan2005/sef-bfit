package au.edu.rmit.isys3413.sefbfit.configs.enumconverters;

import au.edu.rmit.isys3413.sefbfit.models.workoutrecipe.Unit;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class UnitConverter implements AttributeConverter<Unit, String> {
  @Override
  public String convertToDatabaseColumn(Unit unit) {
    return unit.toDisplayName();
  }

  @Override
  public Unit convertToEntityAttribute(String displayName) {
    return Unit.fromDisplayName(displayName);
  }
}
