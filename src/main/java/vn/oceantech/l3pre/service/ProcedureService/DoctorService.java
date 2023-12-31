package vn.oceantech.l3pre.service.ProcedureService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.AllCodesDto;
import vn.oceantech.l3pre.dto.MarkdownsDto;
import vn.oceantech.l3pre.dto.UserProDto;

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

    public List<UserProDto> getTopDoctorHome(Integer size) {
        if (Objects.isNull(size)) {
            size = 10;
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_top_doctor_home", "DoctorMapper")
                .registerStoredProcedureParameter("size", Integer.class, ParameterMode.IN)
                .setParameter("size", size);
        List<UserProDto> userDtos = new ArrayList<>();
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            userDtos.add(new UserProDto((UserProDto) object[0], (AllCodesDto) object[1]));
        }
        return userDtos;
    }

    public List<UserProDto> getDetailsAllDoctor() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_details_all_doctor", "DoctorDetailsMapper");
        List<UserProDto> userDtos = new ArrayList<>();
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            userDtos.add(new UserProDto((UserProDto) object[0], (MarkdownsDto) object[1]));
        }
        return userDtos;
    }

    public UserProDto getDetailsDoctorById(int id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_details_doctor_by_id", "DoctorDetailsMapper")
                .registerStoredProcedureParameter("doctorId", Integer.class, ParameterMode.IN)
                .setParameter("doctorId", id);
        Object[] object = (Object[]) query.getSingleResult();
        return new UserProDto((UserProDto) object[0], (MarkdownsDto) object[1], (AllCodesDto) object[2]);
    }

    public List<UserProDto> getAllDoctor() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_all_doctor", "DoctorSimpleMapper");
        List<?> list = query.getResultList();
        List<UserProDto> userDtos = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof UserProDto) {
                userDtos.add((UserProDto) object);
            }
        }
        return userDtos;
    }


}
