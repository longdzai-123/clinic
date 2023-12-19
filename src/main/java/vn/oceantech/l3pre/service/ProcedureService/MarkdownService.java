package vn.oceantech.l3pre.service.ProcedureService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.MarkdownsDto;
import vn.oceantech.l3pre.utils.ConvertToJson;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MarkdownService {
    private final EntityManager entityManager;

    public MarkdownsDto create(MarkdownsDto markdownsDto) {
        markdownsDto.setCreatedAt(LocalDateTime.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("create_markdowns", "MarkdownMapper")
                .registerStoredProcedureParameter("markdownJSON", String.class, ParameterMode.IN)
                .setParameter("markdownJSON", ConvertToJson.toJsonString(markdownsDto));
        return (MarkdownsDto) query.getSingleResult();
    }

    public MarkdownsDto update(MarkdownsDto markdownsDto) {
        markdownsDto.setUpdatedAt(LocalDateTime.now());
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("update_markdown", "MarkdownMapper")
                .registerStoredProcedureParameter("markdownJSON", String.class, ParameterMode.IN)
                .setParameter("markdownJSON", ConvertToJson.toJsonString(markdownsDto));
        return (MarkdownsDto) query.getSingleResult();
    }
}
