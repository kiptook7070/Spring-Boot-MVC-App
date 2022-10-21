package com.kiptook7070.SpringBootMvcCrudApp.service;

import com.kiptook7070.SpringBootMvcCrudApp.exception.InvoiceNotFoundException;
import com.kiptook7070.SpringBootMvcCrudApp.model.Invoice;
import com.kiptook7070.SpringBootMvcCrudApp.repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        Optional<Invoice>  optionalInvoice = invoiceRepository.findById(id);
        if(optionalInvoice.isPresent()){
            return optionalInvoice.get();
        } else {
            throw new InvoiceNotFoundException("Invoice with ID:" +id+ "Not Found");
        }
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.delete(getInvoiceById(id));
    }
}
