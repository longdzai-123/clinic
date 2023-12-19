package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.DrugDto;
import vn.oceantech.l3pre.entity.Drug;
import vn.oceantech.l3pre.repository.DrugRepo;
import vn.oceantech.l3pre.service.DrugService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {
    private final DrugRepo drugRepo;

    @Override
    public DrugDto create(DrugDto drugDto) {
        Drug drug = new ModelMapper().map(drugDto, Drug.class);
        drug.setCreatedAt(LocalDateTime.now());
        drugRepo.save(drug);
        drugDto.setId(drug.getId());
        drugDto.setCreatedAt(drug.getCreatedAt());
        return drugDto;
    }


}
