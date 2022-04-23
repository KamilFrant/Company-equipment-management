package pl.kfrant.personelmanagement.user.mapper;

import org.springframework.stereotype.Component;
import pl.kfrant.personelmanagement.assignment.Assignment;
import pl.kfrant.personelmanagement.equipment.item.Item;
import pl.kfrant.personelmanagement.user.dto.UserAssignmentDto;

@Component
public class UserAssignmentMapper {

     public UserAssignmentDto assignmentToDto(Assignment assignment){
        UserAssignmentDto dto = new UserAssignmentDto();
        dto.setId(assignment.getId());
        dto.setStart(assignment.getStart());
        dto.setEnd(assignment.getEnd());
        Item item = assignment.getItem();
        dto.setAssetId(item.getId());
        dto.setAssetName(item.getName());
        dto.setAssetSerialNumber(item.getSerialNumber());
        return dto;
    }
}
