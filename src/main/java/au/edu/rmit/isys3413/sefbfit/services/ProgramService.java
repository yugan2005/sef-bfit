package au.edu.rmit.isys3413.sefbfit.services;

import au.edu.rmit.isys3413.sefbfit.models.Program;
import au.edu.rmit.isys3413.sefbfit.repositories.ProgramRepository;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ProgramService {
  private final ProgramRepository programRepository;

  public ProgramService(ProgramRepository programRepository) {
    this.programRepository = programRepository;
  }

  public Program save(Program program) {
    return programRepository.save(program);
  }

  public List<Program> getAll() {
    return programRepository.findAll();
  }

  public Program getByName(String name) {
    return programRepository.findByName(name);
  }
}
