package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.security.JwtTokenProvider;
import vn.oceantech.l3pre.service.ProcedureService.UserServiceProcedure;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserServiceProcedure userService;

    @PostMapping("/login")
    public Response<String> login(@RequestParam("email") String email,
                                 @RequestParam("password") String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return Response.buildResponse(jwtTokenProvider.createToken(email));
        } catch (AuthenticationException e) {
            return Response.buildResponse(ErrorMessage.FORBIDDEN);
        }
    }

    @PostMapping("/login-system")
    public Response<UserDto> loginSystem(@RequestParam("email") String email,
                                  @RequestParam("password") String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return Response.buildResponse(userService.getUserSimpleByEmail(email));
        } catch (AuthenticationException e) {
            return Response.buildResponse(ErrorMessage.FORBIDDEN);
        }
    }

    @PostMapping("/create")
    public Response<UserDto> create(@RequestPart("userDTO") UserDto userDTO,
                       @RequestPart("image") MultipartFile image) {
        return Response.buildResponse(userService.create(userDTO, image));
    }

    @PutMapping
    public Response<UserDto> update(@RequestPart("userDTO") UserDto userDTO,
                       @RequestPart("image") MultipartFile image) {
        return Response.buildResponse(userService.update(userDTO, image));
    }

    @PostMapping("/manager-create")
    public Response<UserDto> managerCreateUser(@RequestBody UserDto userDTO) {
        return Response.buildResponse(userService.managerCreateUser(userDTO));
    }

    @PutMapping("/manager-update")
    public Response<UserDto> managerUpdateUser(@RequestBody UserDto userDTO) {
        return Response.buildResponse(userService.managerUpdateUser(userDTO));
    }

    @DeleteMapping("/{id}")
    public Response<String> deleteById(@PathVariable("id") int id) {
        return Response.buildResponse(userService.deleteById(id));
    }


    @GetMapping("/{id}")
    public Response<UserDto> getById(@PathVariable("id") int id) {
        return Response.buildResponse(userService.getUserById(id));
    }

    @GetMapping("/all")
    public Response<List<UserDto>> getAll() {
        return Response.buildResponse(userService.getAllUser());
    }
}
