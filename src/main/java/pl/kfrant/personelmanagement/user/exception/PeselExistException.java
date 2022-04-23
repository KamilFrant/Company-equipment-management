package pl.kfrant.personelmanagement.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Użytkownik o podanym peselu już istnieje")
public class PeselExistException extends RuntimeException{
}
