package pl.kfrant.personelmanagement.assignment;

import org.springframework.stereotype.Service;
import pl.kfrant.personelmanagement.assignment.dto.AssignmentDto;
import pl.kfrant.personelmanagement.assignment.exception.AssignmentAlreadyFinishedException;
import pl.kfrant.personelmanagement.assignment.exception.AssignmentNotFoundException;
import pl.kfrant.personelmanagement.assignment.exception.InvalidAssignmentException;
import pl.kfrant.personelmanagement.assignment.mapper.AssignmentMapper;
import pl.kfrant.personelmanagement.equipment.item.Item;
import pl.kfrant.personelmanagement.equipment.item.ItemRepository;
import pl.kfrant.personelmanagement.user.User;
import pl.kfrant.personelmanagement.user.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final AssignmentMapper assignmentMapper;

    public AssignmentService(AssignmentRepository assignmentRepository, ItemRepository itemRepository, UserRepository userRepository,AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.assignmentMapper=assignmentMapper;
    }

    AssignmentDto createAssignment(AssignmentDto assignmentDto) {
        Optional<Assignment> activeAssignmentForItem = assignmentRepository
                .findByItem_IdAndEndIsNull(assignmentDto.getItemID());
        activeAssignmentForItem.ifPresent(a -> {
            throw new InvalidAssignmentException("To wyposażenie jest przypisane do kogoś");
        });
        Optional<User> user = userRepository.findById(assignmentDto.getUserID());
        Optional<Item> item = itemRepository.findById(assignmentDto.getItemID());
        Assignment assignment = new Assignment();
        Long userId = assignmentDto.getUserID();
        Long itemId = assignmentDto.getItemID();
        assignment.setUser(user.orElseThrow(() ->
                new InvalidAssignmentException("Brak użytkownika o id: " + userId)
        ));
        assignment.setItem(item.orElseThrow(() ->
                new InvalidAssignmentException("Brak wyposażenia o id: " + itemId)
        ));
        assignment.setStart(LocalDateTime.now());
        return assignmentMapper.toDto(assignmentRepository.save(assignment));
    }

    @Transactional
    public LocalDateTime finishAssignment(Long assignmentId){
        Optional<Assignment> assignmentToEnd = assignmentRepository.findById(assignmentId);
        Assignment assignment = assignmentToEnd.orElseThrow(AssignmentNotFoundException::new);
        if(assignment.getEnd()!=null){
            throw new AssignmentAlreadyFinishedException();
        }else {
            assignment.setEnd(LocalDateTime.now());
        }
        return assignment.getEnd();
    }
}
