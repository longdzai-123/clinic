package vn.oceantech.l3pre.service.ProcedureService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.AllCodesDto;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllCodeService {
    private final EntityManager entityManager;

    public List<AllCodesDto> getAllCodeByType(String type) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_all_codes_by_type", "AllCodeMapper")
                .registerStoredProcedureParameter("type", String.class, ParameterMode.IN)
                .setParameter("type", type);
        List<?> list = query.getResultList();
        List<AllCodesDto> AllCodesDtos = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof AllCodesDto) {
                AllCodesDtos.add((AllCodesDto) object);
            }
        }
        return AllCodesDtos;
    }

}
