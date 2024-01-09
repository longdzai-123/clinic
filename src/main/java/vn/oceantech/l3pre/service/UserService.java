package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.dto.UserProDto;
import vn.oceantech.l3pre.entity.User;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);
    UserDto managerCreateUser(UserDto userDto);

    List<UserDto> getAllDoctorSimple();

    UserDto getByEmail(String email);
}
