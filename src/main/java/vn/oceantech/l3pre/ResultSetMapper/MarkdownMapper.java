package vn.oceantech.l3pre.ResultSetMapper;

import vn.oceantech.l3pre.dto.MarkdownsDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDateTime;

@MappedSuperclass
@SqlResultSetMapping(
        name = "MarkdownMapper",
        classes = @ConstructorResult(
                targetClass = MarkdownsDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "content_html", type = String.class),
                        @ColumnResult(name = "content_markdown", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "doctor_id", type = Integer.class),
                        @ColumnResult(name = "specialty_id", type = Integer.class),
                        @ColumnResult(name = "clinic_id", type = Integer.class),
                        @ColumnResult(name = "created_at", type = LocalDateTime.class),
                        @ColumnResult(name = "updated_at", type = LocalDateTime.class)
                }
        )
)
public class MarkdownMapper {
}
