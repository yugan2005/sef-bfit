package au.edu.rmit.isys3413.sefbfit.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String email;
  private String name;
  private String password;

  @Singular
  @Enumerated(value = EnumType.STRING)
  @ElementCollection(targetClass = Role.class)
  private List<Role> roles;

  public void addRole(Role role) {
    if (roles == null) {
      roles = new ArrayList<>();
    }
    roles.add(role);
  }
}
