package Mappers;

import Dto.UserDto;
import entity.User;

public class UserMapper {

    //-------------------------------------------------------User
    public static UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setAdmin_user(user.getAdmin_user());
        return userDto;
    }
    public static User dtoToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAdmin_user(userDto.getAdmin_user());

        return user;
    }
}
