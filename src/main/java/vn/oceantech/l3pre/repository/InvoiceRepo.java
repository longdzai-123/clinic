package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.oceantech.l3pre.entity.Invoice;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {
    @Query(value = "SELECT * FROM invoices i WHERE i.remedy_id = :remedyId", nativeQuery = true)
    Invoice getByRemedyId(int remedyId);

    @Query(value = "SELECT * FROM invoices i WHERE i.doctor_id = :doctorId", nativeQuery = true)
    List<Invoice> getAllByDoctorId(Integer doctorId);
}
