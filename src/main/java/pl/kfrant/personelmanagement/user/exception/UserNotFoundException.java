package pl.kfrant.personelmanagement.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Brak użytkownika o takim id.")
public class UserNotFoundException extends RuntimeException{
}
