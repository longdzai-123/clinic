package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.repository.UserRepo;
import vn.oceantech.l3pre.service.UserService;
import vn.oceantech.l3pre.validation.UserValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserValidator userValidator;

    @Override
    public UserDto managerCreateUser(UserDto userDto) {
        userValidator.checkDuplicateEmail(userDto.getEmail());
        if (userDto.getPassword() != null) {
            userDto.setPassword(new BCryptPasswordEncoder(12).encode(userDto.getPassword()));
        }
        userDto.setCreatedAt(LocalDateTime.now());
        User user = new ModelMapper().map(userDto, User.class);
        userRepo.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public UserDto create(UserDto userDto) {
        return null;
    }

    @Override
    public List<UserDto> getAllDoctorSimple() {
        List<User> users = userRepo.getAllDoctor();
        return users.stream().map(user -> new ModelMapper()
                .map(user, UserDto.class)).collect(Collectors.toList());
    }
}
