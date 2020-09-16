package au.edu.rmit.isys3413.sefbfit.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Program {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;

  @ManyToMany
  @Singular
  private List<Workout> workouts;

  @Singular
  @OneToMany(mappedBy = "program")
  private List<AppUserProgramProgress> appUserProgramProgresses;
}
