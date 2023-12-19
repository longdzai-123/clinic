package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.MarkdownsDto;
import vn.oceantech.l3pre.service.ProcedureService.MarkdownService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/markdown")
public class MarkdownController {
    private final MarkdownService markdownService;

    @PostMapping
    public Response<MarkdownsDto> create(@RequestBody MarkdownsDto markdownsDto) {
        return Response.buildResponse(markdownService.create(markdownsDto));
    }

    @PutMapping
    public Response<MarkdownsDto> update(@RequestBody MarkdownsDto markdownsDto) {
        return Response.buildResponse(markdownService.update(markdownsDto));
    }

}
