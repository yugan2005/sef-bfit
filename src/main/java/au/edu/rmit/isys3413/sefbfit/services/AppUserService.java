package au.edu.rmit.isys3413.sefbfit.services;

import au.edu.rmit.isys3413.sefbfit.configs.exceptions.UserAlreadyExistException;
import au.edu.rmit.isys3413.sefbfit.models.AppUser;
import au.edu.rmit.isys3413.sefbfit.models.Role;
import au.edu.rmit.isys3413.sefbfit.repositories.AppUserRepository;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service
@Transactional
public class AppUserService {
  private final AppUserRepository appUserRepository;
  private final PasswordEncoder passwordEncoder;

  public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder,
      PasswordEncoder passwordEncoder1) {
    this.appUserRepository = appUserRepository;
    this.passwordEncoder = passwordEncoder1;
  }

  public List<AppUser> getAll() {
    return appUserRepository.findAll();
  }

  public AppUser getByEmail(String email) {
    return appUserRepository.findByEmail(email);
  }

  public AppUser save(AppUser appUser) throws UserAlreadyExistException {
    if (appUser.getId() != null && getByEmail(appUser.getEmail()) != null) {
      throw new UserAlreadyExistException(String.format("user with email: %s already exist", appUser.getEmail()));
    }

    // Add default Role of USER if not set
    if (CollectionUtils.isEmpty(appUser.getRoles())) {
      appUser.addRole(Role.USER);
    }

    appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
    return appUserRepository.save(appUser);
  }
}
