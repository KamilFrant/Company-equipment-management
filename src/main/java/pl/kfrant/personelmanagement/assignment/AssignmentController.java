package pl.kfrant.personelmanagement.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kfrant.personelmanagement.assignment.dto.AssignmentDto;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<AssignmentDto> saveAssignment(@RequestBody AssignmentDto assignmentDto){
        AssignmentDto savedAssignment;
        try {
            savedAssignment = assignmentService.createAssignment(assignmentDto);
        }catch (InvalidAssignmentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAssignment.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedAssignment);
    }

    @PostMapping("/{id}/end")
    public ResponseEntity<LocalDateTime> endAssignment(@PathVariable Long id){
        LocalDateTime endDate = assignmentService.finishAssignment(id);
        return ResponseEntity.accepted().body(endDate);
    }
}
