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
import vn.oceantech.l3pre.dto.UserProDto;
import vn.oceantech.l3pre.dto.UserPrincipal;
import vn.oceantech.l3pre.utils.ConvertToJson;
import vn.oceantech.l3pre.utils.FileStore;
import vn.oceantech.l3pre.validation.UserValidatorProcedure;

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
    private final UserValidatorProcedure userValidation;

    public UserProDto create(UserProDto userDto, MultipartFile image) {
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setPassword(new BCryptPasswordEncoder(12).encode(userDto.getPassword()));
        if (image != null && !image.isEmpty()) {
            userDto.setImage(FileStore.getNameFile(image, "user-"));
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("create_user", "UserSimpleMapper")
                .registerStoredProcedureParameter("userJSON", String.class, ParameterMode.IN)
                .setParameter("userJSON", ConvertToJson.toJsonString(userDto));
        return (UserProDto) query.getSingleResult();
    }

    public UserProDto managerCreateUser(UserProDto userDto) {
        userValidation.checkDuplicateEmail(userDto.getEmail());
        userDto.setCreatedAt(LocalDateTime.now());
        userDto.setPassword(new BCryptPasswordEncoder(12).encode(userDto.getPassword()));
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("create_user", "UserSimpleMapper")
                .registerStoredProcedureParameter("userJSON", String.class, ParameterMode.IN)
                .setParameter("userJSON", ConvertToJson.toJsonString(userDto));
        return (UserProDto) query.getSingleResult();
    }

    public UserProDto update(UserProDto userDto, MultipartFile image) {
        userDto.setUpdatedAt(LocalDateTime.now());
        if (image != null && !image.isEmpty()) {
            userDto.setImage(FileStore.getNameFile(image, "user-"));
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("update_user", "UserSimpleMapper")
                .registerStoredProcedureParameter("userJSON", String.class, ParameterMode.IN)
                .setParameter("userJSON", ConvertToJson.toJsonString(userDto));
        return (UserProDto) query.getSingleResult();
    }

    public UserProDto managerUpdateUser(UserProDto userDto) {
        userDto.setUpdatedAt(LocalDateTime.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("update_user", "UserSimpleMapper")
                .registerStoredProcedureParameter("userJSON", String.class, ParameterMode.IN)
                .setParameter("userJSON", ConvertToJson.toJsonString(userDto));
        return (UserProDto) query.getSingleResult();
    }

    public String deleteById(int id) {
        userValidation.existsUser(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("delete_user_by_id")
                .registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("message", String.class, ParameterMode.OUT)
                .setParameter("userId", id);
        return String.valueOf(query.getOutputParameterValue("message"));
    }

    public UserProDto getUserById(int id) {
        userValidation.existsUser(id);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_user_by_id", "UserSimpleMapper")
                .registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN)
                .setParameter("userId", id);
        return (UserProDto) query.getSingleResult();
    }

    public List<UserProDto> getAllUser() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_all_user", "UserSimpleMapper");
        List<?> list = query.getResultList();
        List<UserProDto> userDtos = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof UserProDto) {
                userDtos.add((UserProDto) object);
            }
        }
        return userDtos;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserProDto userDto = getUserByEmail(email);
        if (Objects.isNull(userDto)) {
            throw new UsernameNotFoundException("not found");
        }
        String role = userDto.getRoleId();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        authorities.add(authority);
        return new UserPrincipal(userDto.getId(), userDto.getEmail(), userDto.getPassword(), authorities);
    }

    public UserProDto getUserByEmail(String email) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_user_by_email", "UserMapper")
                .registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
                .setParameter("email", email);
        return (UserProDto) query.getSingleResult();
    }

    public UserPrincipal getUserLogin() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public UserProDto getUserSimpleByEmail(String email) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_user_by_email", "UserSimpleMapper")
                .registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
                .setParameter("email", email);
        return (UserProDto) query.getSingleResult();
    }
}
