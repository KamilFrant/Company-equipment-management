package pl.kfrant.personelmanagement.assignment.mapper;

import org.springframework.stereotype.Component;
import pl.kfrant.personelmanagement.assignment.Assignment;
import pl.kfrant.personelmanagement.assignment.dto.AssignmentDto;

@Component
public class AssignmentMapper {

    public AssignmentDto toDto(Assignment assignment){
        AssignmentDto dto = new AssignmentDto();
        dto.setId(assignment.getId());
        dto.setStart(assignment.getStart());
        dto.setEnd(assignment.getEnd());
        dto.setUserID(assignment.getUser().getId());
        dto.setItemID(assignment.getItem().getId());
        return dto;
    }
}

