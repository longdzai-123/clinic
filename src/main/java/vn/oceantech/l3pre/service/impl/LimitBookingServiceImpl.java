package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.LimitBookingDto;
import vn.oceantech.l3pre.entity.LimitBooking;
import vn.oceantech.l3pre.repository.LimitBookingRepo;
import vn.oceantech.l3pre.service.LimitBookingService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LimitBookingServiceImpl implements LimitBookingService {
    private final LimitBookingRepo limitBookingRepo;

    @Override
    public LimitBookingDto create(LimitBookingDto limitBookingDto) {
        LimitBooking limitBooking = new ModelMapper().map(limitBookingDto, LimitBooking.class);
        limitBooking.setCreatedAt(LocalDateTime.now());
        limitBookingRepo.save(limitBooking);
        limitBookingDto.setId(limitBooking.getId());
        return limitBookingDto;
    }
}
