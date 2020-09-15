package au.edu.rmit.isys3413.sefbfit.services;

import au.edu.rmit.isys3413.sefbfit.models.AppUserProgramProgress;
import au.edu.rmit.isys3413.sefbfit.repositories.AppUserProgramProgressRepository;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class AppUserProgramProgressService {

  private final AppUserProgramProgressRepository appUserProgramProgressRepository;

  public AppUserProgramProgressService(AppUserProgramProgressRepository appUserProgramProgressRepository) {
    this.appUserProgramProgressRepository = appUserProgramProgressRepository;
  }

  public AppUserProgramProgress save(AppUserProgramProgress appUserProgramProgress) {
    return appUserProgramProgressRepository.save(appUserProgramProgress);
  }

  public List<AppUserProgramProgress> getAll() {
    return appUserProgramProgressRepository.findAll();
  }
}
