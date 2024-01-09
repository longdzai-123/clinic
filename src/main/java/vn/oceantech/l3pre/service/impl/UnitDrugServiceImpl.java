package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.UnitDrugDto;
import vn.oceantech.l3pre.entity.UnitDrug;
import vn.oceantech.l3pre.repository.UnitDrugRepo;
import vn.oceantech.l3pre.service.UnitDrugService;

@Service
@RequiredArgsConstructor
public class UnitDrugServiceImpl implements UnitDrugService {
    private final UnitDrugRepo unitDrugRepo;

    @Override
    public UnitDrugDto create(UnitDrugDto unitDrugDto) {
        UnitDrug unitDrug = new ModelMapper().map(unitDrugDto, UnitDrug.class);
        unitDrugRepo.save(unitDrug);
        return new ModelMapper().map(unitDrug, UnitDrugDto.class);
    }

}
