package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.DrugDto;

import java.util.List;

public interface DrugService {
    DrugDto create(DrugDto drugDto);

    DrugDto update(DrugDto drugDto);

    List<DrugDto> search(String keyWord);

    List<DrugDto> getAll();

    DrugDto getById(int id);

    Boolean deleteById(int id);
}
