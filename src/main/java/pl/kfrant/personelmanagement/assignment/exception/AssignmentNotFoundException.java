package pl.kfrant.personelmanagement.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Przypisanie o takim id nie istnieje")
public class AssignmentNotFoundException extends RuntimeException{
}
