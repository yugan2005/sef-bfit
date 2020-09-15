package au.edu.rmit.isys3413.sefbfit.models.workoutrecipe;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Recipe {

  @Enumerated(EnumType.STRING)
  private RecipeType recipeType;

  @Enumerated(EnumType.STRING)
  private Unit unit;

  private Integer quantity;
  private Integer repetitions;
}
