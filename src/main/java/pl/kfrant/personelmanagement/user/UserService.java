package pl.kfrant.personelmanagement.user;

import org.springframework.stereotype.Service;
import pl.kfrant.personelmanagement.user.dto.UserAssignmentDto;
import pl.kfrant.personelmanagement.user.dto.UserDto;
import pl.kfrant.personelmanagement.user.exception.PeselExistException;
import pl.kfrant.personelmanagement.user.exception.UserNotFoundException;
import pl.kfrant.personelmanagement.user.mapper.UserAssignmentMapper;
import pl.kfrant.personelmanagement.user.mapper.UserMapper;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserAssignmentMapper userAssignmentMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper,UserAssignmentMapper userAssignmentMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userAssignmentMapper=userAssignmentMapper;
    }

    List<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    List<UserDto> findByLastName(String lastName) {
        return userRepository.findByLastNameContainsIgnoreCase(lastName)
                .stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapToUserDto);
    }

    UserDto saveUser(UserDto dto) {
        Optional<User> userByPesel = userRepository.findByPesel(dto.getPesel());
        userByPesel.ifPresent(user -> {
            throw new PeselExistException();
        });
        return mapAndSave(dto);
    }


    UserDto updateUser(UserDto dto) {
        Optional<User> userByPesel = userRepository.findByPesel(dto.getPesel());
        userByPesel.ifPresent(user -> {
            if (!user.getId().equals(dto.getId()))
                throw new PeselExistException();
        });
        return mapAndSave(dto);
    }

    private UserDto mapAndSave(UserDto dto) {
        User userToSave = userMapper.mapToUser(dto);
        User savedUser = userRepository.save(userToSave);
        return userMapper.mapToUserDto(savedUser);
    }

    public List<UserAssignmentDto> getUserAssignments(Long userId) {
        return userRepository.findById(userId)
                .map(User::getAssignments)
                .orElseThrow(UserNotFoundException::new)
                .stream()
                .map(userAssignmentMapper::assignmentToDto)
                .collect(Collectors.toList());
    }
}
