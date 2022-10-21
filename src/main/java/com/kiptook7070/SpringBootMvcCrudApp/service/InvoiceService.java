package com.kiptook7070.SpringBootMvcCrudApp.service;

import com.fasterxml.jackson.databind.deser.UnresolvedId;
import com.kiptook7070.SpringBootMvcCrudApp.model.Invoice;

import java.util.List;

public interface InvoiceService {
    public Invoice saveInvoice(Invoice invoice);

    List<Invoice> getAllInvoices();

   public Invoice getInvoiceById(Long id);

    public void updateInvoice(Invoice invoice);

    public void deleteInvoice(Long id);
}
