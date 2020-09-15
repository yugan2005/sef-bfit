package au.edu.rmit.isys3413.sefbfit.models;

import au.edu.rmit.isys3413.sefbfit.models.workoutrecipe.Recipe;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workout {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String name;

  @Singular
  @ElementCollection
  private List<Recipe> recipes;

  @Singular
  @ManyToMany (mappedBy = "workouts")
  private List<Program> programs;

  public void removeRecipeByIndex(int idx) {
    recipes.remove(idx);
  }

  public void addRecipe(Recipe recipe) {
    if (recipes == null) {
      recipes = new ArrayList<>();
    }
    recipes.add(recipe);
  }
}
