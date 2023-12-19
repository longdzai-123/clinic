package vn.oceantech.l3pre.ResultSetMapper;

import vn.oceantech.l3pre.dto.AllCodesDto;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

@MappedSuperclass
@SqlResultSetMapping(
        name = "AllCodeMapper",
        classes = @ConstructorResult(
                targetClass = AllCodesDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "key_map", type = String.class),
                        @ColumnResult(name = "type", type = String.class),
                        @ColumnResult(name = "value_en", type = String.class),
                        @ColumnResult(name = "value_vi", type = String.class)
                }
        )
)
public class AllCodeMapper {
}
