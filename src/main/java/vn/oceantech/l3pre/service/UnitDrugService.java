package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.DrugDto;
import vn.oceantech.l3pre.dto.UnitDrugDto;
import vn.oceantech.l3pre.entity.UnitDrug;

import java.util.List;

public interface UnitDrugService {
    UnitDrugDto create(UnitDrugDto unitDrugDto);

    List<UnitDrugDto> getAll();
}
