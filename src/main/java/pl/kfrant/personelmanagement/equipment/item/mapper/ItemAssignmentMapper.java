package pl.kfrant.personelmanagement.equipment.item.mapper;

import org.springframework.stereotype.Component;
import pl.kfrant.personelmanagement.assignment.Assignment;
import pl.kfrant.personelmanagement.equipment.item.dto.ItemAssignmentDto;

@Component
public class ItemAssignmentMapper{

    public ItemAssignmentDto assignmentToDto(Assignment assignment){
        ItemAssignmentDto dto = new ItemAssignmentDto();
        dto.setId(assignment.getId());
        dto.setStart(assignment.getStart());
        dto.setEnd(assignment.getEnd());
        dto.setUserId(assignment.getUser().getId());
        dto.setFirstName(assignment.getUser().getFirstName());
        dto.setLastName(assignment.getUser().getLastName());
        dto.setPesel(assignment.getUser().getPesel());
        return dto;
    }
}
