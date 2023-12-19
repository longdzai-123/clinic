package vn.oceantech.l3pre.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "allcodes")
@NoArgsConstructor
@AllArgsConstructor
public class AllCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "key_map")
    private String keyMap;
    @Column(name = "type")
    private String type;
    @Column(name = "value_en")
    private String valueEn;
    @Column(name = "value_vi")
    private String valueVi;
}
