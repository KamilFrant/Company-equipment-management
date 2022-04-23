package pl.kfrant.personelmanagement.equipment.item.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Wyposażenie o takim id nie istnieje")
public class ItemNotFoundException extends RuntimeException{
}
