package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.repository.UserRepo;
import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.service.UserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public UserDto create(UserDto userDto) {
        userDto.setCreatedAt(LocalDateTime.now());
        User user = new ModelMapper().map(userDto, User.class);
        userRepo.save(user);
        userDto.setId(user.getId());
        return userDto;
    }
}
