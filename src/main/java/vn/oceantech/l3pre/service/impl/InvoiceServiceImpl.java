package vn.oceantech.l3pre.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.oceantech.l3pre.dto.InvoiceDto;
import vn.oceantech.l3pre.dto.RemedyDto;
import vn.oceantech.l3pre.dto.UserDto;
import vn.oceantech.l3pre.entity.*;
import vn.oceantech.l3pre.exception.ErrorMessage;
import vn.oceantech.l3pre.exception.ProException;
import vn.oceantech.l3pre.repository.AllCodesRepo;
import vn.oceantech.l3pre.repository.DoctorInforRepo;
import vn.oceantech.l3pre.repository.InvoiceRepo;
import vn.oceantech.l3pre.service.InvoiceService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepo invoiceRepo;
    private final DoctorInforRepo doctorInforRepo;
    private final AllCodesRepo allCodesRepo;

    @Override
    public InvoiceDto create(InvoiceDto invoicesDto) {
        Invoice invoice = new Invoice();
        this.mapDtoToEntity(invoicesDto, invoice);
        invoiceRepo.save(invoice);
        return invoicesDto;
    }

    @Override
    public void createByRemedy(Remedy remedy) {
        if (invoiceRepo.getByRemedyId(remedy.getId()) == null) {
            Invoice invoice = new Invoice();
            invoice.setIsPay(false);
            invoice.setDoctor(remedy.getDoctor());
            invoice.setPatient(remedy.getPatient());
            invoice.setRemedy(remedy);
            DoctorInformation doctorInformation = doctorInforRepo.getByDoctorId(remedy.getDoctor().getId());
            invoice.setSpecialty(doctorInformation.getSpecialty());
            AllCodes allCodes = allCodesRepo.getByKeyMap(doctorInformation.getPriceId());
            invoice.setTotalCost(Integer.parseInt(allCodes.getValueVi()));
            invoice.setCreatedAt(LocalDateTime.now());
            invoiceRepo.save(invoice);
        }
    }

    @Override
    public List<InvoiceDto> getAllByDoctorId(int doctorId) {
        List<Invoice> invoices = invoiceRepo.getAllByDoctorId(doctorId);
        List<InvoiceDto> invoiceDtos = new ArrayList<>();
        for (Invoice invoice : invoices) {
            InvoiceDto invoiceDto = new InvoiceDto();
            this.mapEntityToDto(invoice, invoiceDto);
            invoiceDtos.add(invoiceDto);
        }
        return invoiceDtos;
    }

    @Override
    public InvoiceDto confirmPayment(int invoiceId) {
        Invoice invoice = invoiceRepo.findById(invoiceId).orElseThrow(() -> new ProException(ErrorMessage.NOT_FOUND_INVOICE));
        invoice.setIsPay(true);
        invoiceRepo.save(invoice);
        InvoiceDto invoiceDto = new InvoiceDto();
        this.mapEntityToDto(invoice, invoiceDto);
        return invoiceDto;
    }

    @Override
    public List<InvoiceDto> searchByPatientName(String patientName) {
        List<Invoice> invoices = invoiceRepo.searchByPatientName(patientName);
        List<InvoiceDto> invoiceDtos = new ArrayList<>();
        for (Invoice invoice : invoices) {
            InvoiceDto invoiceDto = new InvoiceDto();
            this.mapEntityToDto(invoice, invoiceDto);
            invoiceDtos.add(invoiceDto);
        }
        return invoiceDtos;
    }

    private void mapEntityToDto(Invoice invoice, InvoiceDto invoiceDto) {
        invoiceDto.setId(invoice.getId());

        UserDto doctor = new UserDto();
        doctor.setId(invoice.getDoctor().getId());
        invoiceDto.setDoctor(doctor);


        UserDto patient = new UserDto();
        patient.setId(invoice.getPatient().getId());
        patient.setFirstName(invoice.getPatient().getFirstName());
        patient.setAddress(invoice.getRemedy().getBooking().getPatientAddress());
        invoiceDto.setPatient(patient);

        RemedyDto remedyDto = new RemedyDto();
        remedyDto.setId(invoice.getRemedy().getId());
        remedyDto.setDescription(invoice.getRemedy().getDescription());
        remedyDto.setPhoneNumber(invoice.getRemedy().getPhoneNumber());
        remedyDto.setDate(invoice.getRemedy().getDate());
        invoiceDto.setRemedy(remedyDto);

        AllCodes allCodes = allCodesRepo.getByKeyMap(invoice.getRemedy().getTimeType());
        invoiceDto.setTime(allCodes.getValueVi());

        invoiceDto.setIsPay(invoice.getIsPay());
        invoiceDto.setTotalCost(invoice.getTotalCost());
    }

    private void mapDtoToEntity(InvoiceDto invoicesDto, Invoice invoice) {
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
