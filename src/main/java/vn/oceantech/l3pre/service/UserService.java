package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.dto.UserProDto;
import vn.oceantech.l3pre.entity.User;

import java.util.List;

public interface UserService {
    UserDto signUp(UserDto userDto);
    UserDto managerCreateUser(UserDto userDto);

    List<UserDto> getAllDoctorSimple();

    UserDto getByEmail(String email);

    List<UserDto> getAllDoctorAndAdmin();

    Boolean activeAccount(int doctorId);
}
