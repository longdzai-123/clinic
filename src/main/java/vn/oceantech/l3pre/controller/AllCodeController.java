package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.AllCodesDto;
import vn.oceantech.l3pre.service.ProcedureService.AllCodeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/allcodes")
public class AllCodeController {
    private final AllCodeService allCodeService;

    @GetMapping
    public Response<List<AllCodesDto>> getAllCodeByType(@RequestParam("type") String type){
        return Response.buildResponse(allCodeService.getAllCodeByType(type));
    }
}
