package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.entity.User;

public interface UserService {
    UserDto create(UserDto userDto);
}
