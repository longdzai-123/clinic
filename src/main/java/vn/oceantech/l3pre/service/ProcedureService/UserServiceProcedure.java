package vn.oceantech.l3pre.service.ProcedureService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.dto.UserPrincipal;
import vn.oceantech.l3pre.utils.ConvertToJson;
import vn.oceantech.l3pre.utils.FileStore;
import vn.oceantech.l3pre.validation.UserValidator;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceProcedure implements UserDetailsService {
    private final EntityManager entityManager;
    private final UserValidator userValidation;

    public UserDto create(UserDto userDto, MultipartFile image) {
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setPassword(new BCryptPasswordEncoder(12).encode(userDto.getPassword()));
        if (image != null && !image.isEmpty()) {
            userDto.setImage(FileStore.getFileName(image, "user-"));
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("create_user", "UserSimpleMapper")
                .registerStoredProcedureParameter("userJSON", String.class, ParameterMode.IN)
                .setParameter("userJSON", ConvertToJson.toJsonString(userDto));
        return (UserDto) query.getSingleResult();
    }

    public UserDto managerCreateUser(UserDto userDto) {
        userValidation.checkDuplicateEmail(userDto.getEmail());
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setPassword(new BCryptPasswordEncoder(12).encode(userDto.getPassword()));
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("create_user", "UserSimpleMapper")
                .registerStoredProcedureParameter("userJSON", String.class, ParameterMode.IN)
                .setParameter("userJSON", ConvertToJson.toJsonString(userDto));
        return (UserDto) query.getSingleResult();
    }

    public UserDto update(UserDto userDto, MultipartFile image) {
        userDto.setUpdatedAt(LocalDateTime.now());
        if (image != null && !image.isEmpty()) {
            userDto.setImage(FileStore.getFileName(image, "user-"));
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("update_user", "UserSimpleMapper")
                .registerStoredProcedureParameter("userJSON", String.class, ParameterMode.IN)
                .setParameter("userJSON", ConvertToJson.toJsonString(userDto));
        return (UserDto) query.getSingleResult();
    }

    public UserDto managerUpdateUser(UserDto userDto) {
        userDto.setUpdatedAt(LocalDateTime.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("update_user", "UserSimpleMapper")
                .registerStoredProcedureParameter("userJSON", String.class, ParameterMode.IN)
                .setParameter("userJSON", ConvertToJson.toJsonString(userDto));
        return (UserDto) query.getSingleResult();
    }

    public String deleteById(int id) {
        userValidation.existsUser(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("delete_user_by_id")
                .registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("message", String.class, ParameterMode.OUT)
                .setParameter("userId", id);
        return String.valueOf(query.getOutputParameterValue("message"));
    }

    public UserDto getUserById(int id) {
        userValidation.existsUser(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_user_by_id", "UserSimpleMapper")
                .registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN)
                .setParameter("userId", id);
        return (UserDto) query.getSingleResult();
    }

    public List<UserDto> getAllUser() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_all_user", "UserSimpleMapper");
        List<?> list = query.getResultList();
        List<UserDto> userDtos = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof UserDto) {
                userDtos.add((UserDto) object);
            }
        }
        return userDtos;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = getUserByEmail(email);
        if (Objects.isNull(userDto)) {
            throw new UsernameNotFoundException("not found");
        }
        String role = userDto.getRoleId();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        authorities.add(authority);
        return new UserPrincipal(userDto.getId(), userDto.getEmail(), userDto.getPassword(), authorities);
    }

    public UserDto getUserByEmail(String email) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_user_by_email", "UserMapper")
                .registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
                .setParameter("email", email);
        return (UserDto) query.getSingleResult();
    }

    public UserPrincipal getUserLogin() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public UserDto getUserSimpleByEmail(String email) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_user_by_email", "UserSimpleMapper")
                .registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
                .setParameter("email", email);
        return (UserDto) query.getSingleResult();
    }
}
