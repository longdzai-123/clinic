package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.AllCodesDto;
import vn.oceantech.l3pre.entity.AllCodes;
import vn.oceantech.l3pre.repository.AllCodesRepo;
import vn.oceantech.l3pre.service.AllCodesService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllCodesServiceImpl implements AllCodesService {
    private final AllCodesRepo allCodesRepo;

    @Override
    public List<AllCodesDto> getAllCodeByType(String type) {
        List<AllCodes> allCodes = allCodesRepo.getAllCodeByType(type);
        List<AllCodesDto> allCodesDtos = allCodes.stream().
                map(p -> new ModelMapper().map(p, AllCodesDto.class)).collect(Collectors.toList());
        return allCodesDtos;
    }
}
