package au.edu.rmit.isys3413.sefbfit.repositories;

import au.edu.rmit.isys3413.sefbfit.models.AppUserProgramProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserProgramProgressRepository extends JpaRepository<AppUserProgramProgress, Long> {
}
