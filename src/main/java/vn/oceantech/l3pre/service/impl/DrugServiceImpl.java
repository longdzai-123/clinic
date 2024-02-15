package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.DrugDto;
import vn.oceantech.l3pre.entity.Drug;
import vn.oceantech.l3pre.entity.UnitDrug;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.exception.NotFoundException;
import vn.oceantech.l3pre.repository.DrugRepo;
import vn.oceantech.l3pre.service.DrugService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public DrugDto update(DrugDto drugDto) {
        Drug drug = drugRepo.findById(drugDto.getId()).orElseThrow(() ->
                new NotFoundException(ErrorMessage.NOT_FOUND_DRUG));
        if (Objects.nonNull(drug)) {
            drug.setName(drugDto.getName());
            drug.setUnit(new ModelMapper().map(drugDto.getUnit(), UnitDrug.class));
            drugRepo.save(drug);
        }
        return new ModelMapper().map(drug, DrugDto.class);
    }

    @Override
    public List<DrugDto> search(String keyWord) {
        List<Drug> drugs = drugRepo.search(keyWord);
        return drugs.stream().map(drug -> new ModelMapper().map(drug, DrugDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<DrugDto> getAll() {
        List<Drug> drugs = drugRepo.getAll();
        return drugs.stream().map(drug -> new ModelMapper().map(drug, DrugDto.class)).collect(Collectors.toList());
    }

    @Override
    public DrugDto getById(int id) {
        Drug drug = drugRepo.getById(id);
        return new ModelMapper().map(drug, DrugDto.class);
    }

    @Override
    public Boolean deleteById(int id) {
        drugRepo.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_DRUG));
        drugRepo.deleteById(id);
        return true;
    }
}
