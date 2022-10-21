package com.kiptook7070.SpringBootMvcCrudApp.repo;

import com.kiptook7070.SpringBootMvcCrudApp.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
