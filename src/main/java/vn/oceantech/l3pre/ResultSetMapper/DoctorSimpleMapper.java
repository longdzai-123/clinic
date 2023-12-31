package vn.oceantech.l3pre.ResultSetMapper;

import vn.oceantech.l3pre.dto.UserProDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDateTime;

@MappedSuperclass
@SqlResultSetMapping(
        name = "DoctorSimpleMapper",
        classes = @ConstructorResult(
                targetClass = UserProDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "first_name", type = String.class),
                        @ColumnResult(name = "last_name", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "gender", type = String.class),
                        @ColumnResult(name = "role_id", type = String.class),
                        @ColumnResult(name = "phone_number", type = String.class),
                        @ColumnResult(name = "position_id", type = String.class),
                        @ColumnResult(name = "created_at", type = LocalDateTime.class),
                        @ColumnResult(name = "updated_at", type = LocalDateTime.class),
                        @ColumnResult(name = "total_cost", type = Integer.class),
                        @ColumnResult(name = "total_revenue", type = Integer.class)
                }
        )
)
public class DoctorSimpleMapper {
}
