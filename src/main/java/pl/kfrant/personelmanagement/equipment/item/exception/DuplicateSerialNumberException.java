package pl.kfrant.personelmanagement.equipment.item.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Przedmiot z takim numerem seryjnym już istnieje")
public class DuplicateSerialNumberException extends RuntimeException {
}
