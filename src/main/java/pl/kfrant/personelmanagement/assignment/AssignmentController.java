package pl.kfrant.personelmanagement.assignment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kfrant.personelmanagement.assignment.dto.AssignmentDto;
import pl.kfrant.personelmanagement.assignment.exception.InvalidAssignmentException;

import java.net.URI;
import java.time.LocalDateTime;

@Api(tags = "Assignments")
@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    @ApiOperation("Create new assignment")
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
    @ApiOperation("End of assignment")
    public ResponseEntity<LocalDateTime> endAssignment(@PathVariable Long id){
        LocalDateTime endDate = assignmentService.finishAssignment(id);
        return ResponseEntity.accepted().body(endDate);
    }
}
