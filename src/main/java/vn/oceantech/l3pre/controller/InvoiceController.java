package vn.oceantech.l3pre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.l3pre.common.Response;
import vn.oceantech.l3pre.dto.InvoiceDto;
import vn.oceantech.l3pre.service.InvoiceService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping
    public Response<List<InvoiceDto>> getAllByDoctorId(@NotNull Integer doctorId) {
        return Response.buildResponse(invoiceService.getAllByDoctorId(doctorId));
    }

    @GetMapping("/confirm-payment")
    public Response<InvoiceDto> confirmPayment(@NotNull Integer invoiceId) {
        return Response.buildResponse(invoiceService.confirmPayment(invoiceId));
    }

    @GetMapping("/search")
    public Response<List<InvoiceDto>> searchByPatientName(String patientName) {
        return Response.buildResponse(invoiceService.searchByPatientName(patientName));
    }

}
