package au.edu.rmit.isys3413.sefbfit.services;

import au.edu.rmit.isys3413.sefbfit.models.AppUser;
import au.edu.rmit.isys3413.sefbfit.models.Role;
import au.edu.rmit.isys3413.sefbfit.repositories.AppUserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SecurityUserDetailsService implements UserDetailsService {
  private final AppUserRepository appUserRepository;

  public SecurityUserDetailsService(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    AppUser appUser = appUserRepository.findByEmail(email);
    if (appUser == null) {
      throw new UsernameNotFoundException("No user found with email: " + email);
    }
    return new User(appUser.getEmail(), appUser.getPassword(), true, true, true, true,
        getAuthorities(appUser.getRoles()));
  }

  private static List<GrantedAuthority> getAuthorities(List<Role> roles) {
    return roles.stream().map(Role::name).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }
}
