package au.edu.rmit.isys3413.sefbfit.repositories;

import au.edu.rmit.isys3413.sefbfit.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
  Program findByName(String name);
}
