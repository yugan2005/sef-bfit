package au.edu.rmit.isys3413.sefbfit.configs.enumconverters;

import au.edu.rmit.isys3413.sefbfit.models.workoutrecipe.RecipeType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class RecipeTypeConverter implements AttributeConverter<RecipeType, String> {
  @Override
  public String convertToDatabaseColumn(RecipeType recipeType) {
    return recipeType.toDisplayName();
  }

  @Override
  public RecipeType convertToEntityAttribute(String displayName) {
    return RecipeType.fromDisplayName(displayName);
  }
}
