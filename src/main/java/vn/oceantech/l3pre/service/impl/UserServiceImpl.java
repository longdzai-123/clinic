package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.exception.ProException;
import vn.oceantech.l3pre.repository.UserRepo;
import vn.oceantech.l3pre.service.UserService;
import vn.oceantech.l3pre.validation.UserValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserValidator userValidator;

    @Override
    public UserDto signUp(UserDto userDto) {
        userValidator.checkDuplicateEmail(userDto.getEmail());
        userDto.setIsActive(false);
        userDto.setRoleId("R2");
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
    public UserDto managerCreateUser(UserDto userDto) {
        userValidator.checkDuplicateEmail(userDto.getEmail());
        if (userDto.getPassword() != null) {
            userDto.setPassword(new BCryptPasswordEncoder(12).encode(userDto.getPassword()));
        }
        userDto.setIsActive(false);
        userDto.setCreatedAt(LocalDateTime.now());
        User user = new ModelMapper().map(userDto, User.class);
        userRepo.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public List<UserDto> getAllDoctorSimple() {
        List<User> users = userRepo.getAllDoctor();
        return users.stream().map(user -> new ModelMapper()
                .map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userRepo.getByEmail(email);
        UserDto userDto = new ModelMapper().map(user, UserDto.class);
        userDto.setImage(userDto.getImage());
        return userDto;
    }

    @Override
    public List<UserDto> getAllDoctorAndAdmin() {
        List<User> users = userRepo.getAllDoctorAndAdmin();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            ModelMapper mapper = new ModelMapper();
            mapper.createTypeMap(User.class, UserDto.class)
                    .addMappings(map -> map.skip(UserDto::setPassword));
            UserDto userDto = mapper.map(user, UserDto.class);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public Boolean activeAccount(int doctorId) {
        User user = userRepo.findById(doctorId).orElseThrow(() -> new ProException(ErrorMessage.NOT_FOUND_USER));
        user.setIsActive(!user.getIsActive());
        return userRepo.save(user).getIsActive();
    }
}

