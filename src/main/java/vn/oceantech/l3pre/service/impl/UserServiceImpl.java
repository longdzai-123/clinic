package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.repository.UserRepo;
import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.service.UserService;
import vn.oceantech.l3pre.validation.UserValidator;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserValidator userValidator;

    @Override
    public UserDto create(UserDto userDto) {
        userDto.setCreatedAt(LocalDateTime.now());
        userValidator.checkDuplicateEmail(userDto.getEmail());
        User user = new ModelMapper().map(userDto, User.class);
        userRepo.save(user);
        userDto.setId(user.getId());
        return userDto;
    }
}
