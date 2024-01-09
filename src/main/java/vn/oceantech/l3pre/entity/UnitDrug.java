package vn.oceantech.l3pre.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "unit_drug")
@Setter
@Getter
public class UnitDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "key_map")
    private String keyMap;
    @Column(name = "value_vi")
    private String valueVi;
    @Column(name = "value_en")
    private String valueEn;
}
