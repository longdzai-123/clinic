package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.RemedyDto;

public interface RemedyService {
    RemedyDto create(RemedyDto remedyDto);

    RemedyDto getByBookingId(Integer bookingId);

    RemedyDto updateRemedyDetails(RemedyDto remedyDto);

    RemedyDto updateRemedyImage(RemedyDto remedyDto);
}
