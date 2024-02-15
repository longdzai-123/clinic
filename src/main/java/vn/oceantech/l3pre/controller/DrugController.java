package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.DrugDto;
import vn.oceantech.l3pre.service.DrugService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drugs")
public class DrugController {
    private final DrugService drugService;

    @PostMapping
    public Response<DrugDto> create(@RequestBody DrugDto drugDto) {
        return Response.buildResponse(drugService.create(drugDto));
    }

    @PutMapping
    public Response<DrugDto> update(@RequestBody DrugDto drugDto) {
        return Response.buildResponse(drugService.update(drugDto));
    }

    @GetMapping("/search")
    public Response<List<DrugDto>> search(String keyWord) {
        return Response.buildResponse(drugService.search(keyWord));
    }

    @GetMapping
    public Response<List<DrugDto>> getAll() {
        return Response.buildResponse(drugService.getAll());
    }

    @GetMapping("/{id}")
    public Response<DrugDto> getById(@PathVariable("id") Integer id) {
        return Response.buildResponse(drugService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Response<Boolean> deleteById(@PathVariable("id") Integer id) {
        return Response.buildResponse(drugService.deleteById(id));
    }

}
