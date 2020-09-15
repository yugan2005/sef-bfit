package au.edu.rmit.isys3413.sefbfit.configs.exceptions;

public class UserAlreadyExistException extends RuntimeException{
  public UserAlreadyExistException() {
    super();
  }

  public UserAlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserAlreadyExistException(String message) {
    super(message);
  }

  public UserAlreadyExistException(Throwable cause) {
    super(cause);
  }
}
