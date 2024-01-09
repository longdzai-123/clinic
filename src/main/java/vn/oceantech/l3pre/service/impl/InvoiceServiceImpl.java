package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.InvoicesDto;
import vn.oceantech.l3pre.entity.Invoice;
import vn.oceantech.l3pre.entity.Remedy;
import vn.oceantech.l3pre.entity.Specialty;
import vn.oceantech.l3pre.entity.User;
import vn.oceantech.l3pre.repository.InvoiceRepo;
import vn.oceantech.l3pre.service.InvoiceService;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepo invoiceRepo;

    @Override
    public InvoicesDto create(InvoicesDto invoicesDto) {
        Invoice invoice = new Invoice();
        this.mapDtoToEntity(invoicesDto, invoice);
        invoiceRepo.save(invoice);
        return invoicesDto;
    }

    private void mapDtoToEntity(InvoicesDto invoicesDto, Invoice invoice) {
        User doctor = new User();
        doctor.setId(invoicesDto.getDoctor().getId());
        invoice.setDoctor(doctor);

        User patient = new User();
        patient.setId(invoicesDto.getPatient().getId());
        invoice.setPatient(patient);

        Remedy remedy = new Remedy();
        remedy.setId(invoicesDto.getRemedy().getId());
        invoice.setRemedy(remedy);

        Specialty specialty = new Specialty();
        specialty.setId(invoicesDto.getSpecialty().getId());
        invoice.setSpecialty(specialty);

        invoice.setTotalCost(invoicesDto.getTotalCost());
    }
}
