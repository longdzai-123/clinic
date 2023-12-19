package vn.oceantech.l3pre.service.ProcedureService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.AllCodesDto;
import vn.oceantech.l3pre.dto.MarkdownsDto;
import vn.oceantech.l3pre.dto.UserDto;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final EntityManager entityManager;

    public List<UserDto> getTopDoctorHome(Integer size) {
        if (Objects.isNull(size)) {
            size = 10;
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_top_doctor_home", "DoctorMapper")
                .registerStoredProcedureParameter("size", Integer.class, ParameterMode.IN)
                .setParameter("size", size);
        List<UserDto> userDtos = new ArrayList<>();
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            userDtos.add(new UserDto((UserDto) object[0], (AllCodesDto) object[1]));
        }
        return userDtos;
    }

    public List<UserDto> getDetailsAllDoctor() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_details_all_doctor", "DoctorDetailsMapper");
        List<UserDto> userDtos = new ArrayList<>();
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            userDtos.add(new UserDto((UserDto) object[0], (MarkdownsDto) object[1]));
        }
        return userDtos;
    }

    public UserDto getDetailsDoctorById(int id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_details_doctor_by_id", "DoctorDetailsMapper")
                .registerStoredProcedureParameter("doctorId", Integer.class, ParameterMode.IN)
                .setParameter("doctorId", id);
        Object[] object = (Object[]) query.getSingleResult();
        return new UserDto((UserDto) object[0], (MarkdownsDto) object[1], (AllCodesDto) object[2]);
    }

    public List<UserDto> getAllDoctor() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_all_doctor", "DoctorSimpleMapper");
        List<?> list = query.getResultList();
        List<UserDto> userDtos = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof UserDto) {
                userDtos.add((UserDto) object);
            }
        }
        return userDtos;
    }


}
