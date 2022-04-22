package pl.kfrant.personelmanagement.user;

import org.springframework.stereotype.Service;
import pl.kfrant.personelmanagement.user.dto.UserDto;
import pl.kfrant.personelmanagement.user.exception.PeselExistException;
import pl.kfrant.personelmanagement.user.mapper.UserMapper;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    List<UserDto> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    List<UserDto> findByLastName(String lastName){
        return userRepository.findByLastNameContainsIgnoreCase(lastName)
                .stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    Optional<UserDto> findById(Long id){
        return userRepository.findById(id)
                .map(userMapper::mapToUserDto);
    }

    UserDto saveUser(UserDto dto){
        Optional<User> userByPesel = userRepository.findByPesel(dto.getPesel());
        userByPesel.ifPresent(user -> {
            throw new PeselExistException();
        });
        return mapAndSave(dto);
    }


    UserDto updateUser(UserDto dto){
        Optional<User> userByPesel = userRepository.findByPesel(dto.getPesel());
        userByPesel.ifPresent(user -> {
            throw new PeselExistException();
        });
        return mapAndSave(dto);
    }

    private UserDto mapAndSave(UserDto dto){
        User userToSave = userMapper.mapToUser(dto);
        User savedUser = userRepository.save(userToSave);
        return userMapper.mapToUserDto(savedUser);
    }
}
