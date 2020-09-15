package au.edu.rmit.isys3413.sefbfit.repositories;

import au.edu.rmit.isys3413.sefbfit.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  AppUser findByEmail(String email);
}
