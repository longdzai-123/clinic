package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.AllCodesDto;

import java.util.List;

public interface AllCodesService {
    List<AllCodesDto> getAllCodeByType(String type);
}
