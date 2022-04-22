package pl.kfrant.personelmanagement.user.mapper;

import org.springframework.stereotype.Component;
import pl.kfrant.personelmanagement.user.User;
import pl.kfrant.personelmanagement.user.dto.UserDto;

@Component
public class UserMapper {

     public User mapToUser(UserDto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setPesel(dto.getPesel());
        return user;
    }

    public UserDto mapToUserDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPesel(user.getPesel());
        return dto;
    }

}
