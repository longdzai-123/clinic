package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.DrugDto;

import java.util.List;

public interface DrugService {
    DrugDto create(DrugDto drugDto);

    List<DrugDto> search(String keyWord);
}
