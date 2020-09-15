package au.edu.rmit.isys3413.sefbfit.validator;

import au.edu.rmit.isys3413.sefbfit.models.AppUser;
import au.edu.rmit.isys3413.sefbfit.services.AppUserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class AppUserValidator implements Validator {
  private final AppUserService appUserService;

  public AppUserValidator(AppUserService appUserService) {
    this.appUserService = appUserService;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return AppUser.class.equals(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    AppUser user = (AppUser) o;

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");

    if (user.getName().length() < 2 || user.getName().length() > 32) {
      errors.rejectValue("name", "Size.appUser.name");
    }
    if (appUserService.getByEmail(user.getEmail()) != null) {
      errors.rejectValue("email", "Duplicate.appUser.email");
    }

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

    if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
      errors.rejectValue("password", "Size.appUser.password");
    }
  }
}
