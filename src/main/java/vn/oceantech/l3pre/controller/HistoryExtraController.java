package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.HistoryExtraDto;
import vn.oceantech.l3pre.service.HistoryExtraService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history-extra")
public class HistoryExtraController {
    private final HistoryExtraService historyExtraService;

    @PostMapping
    public Response<HistoryExtraDto> create(@RequestBody HistoryExtraDto historyExtraDto) {
        return null;
    }
}
