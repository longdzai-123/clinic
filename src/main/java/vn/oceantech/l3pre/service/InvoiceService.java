package vn.oceantech.l3pre.service;

import vn.oceantech.l3pre.dto.InvoiceDto;
import vn.oceantech.l3pre.entity.Remedy;

import java.util.List;

public interface InvoiceService {
    InvoiceDto create(InvoiceDto invoicesDto);

    void createByRemedy(Remedy remedy);

    List<InvoiceDto> getAllByDoctorId(int doctorId);

    InvoiceDto confirmPayment(int invoiceId);

    List<InvoiceDto> searchByPatientName(String patientName);
}
