package com.kiptook7070.SpringBootMvcCrudApp.controller;

import com.kiptook7070.SpringBootMvcCrudApp.exception.InvoiceNotFoundException;
import com.kiptook7070.SpringBootMvcCrudApp.model.Invoice;
import com.kiptook7070.SpringBootMvcCrudApp.service.InvoiceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/")
    public String showHomePage() {
        return "homePage";
    }

    @GetMapping("register")
    public String showRegistration() {
        return "registerInvoicePage";
    }

    @PostMapping("/save")
    public String saveInvoice(
            @ModelAttribute
            Invoice invoice,
            Model model
    ) {
        invoiceService.saveInvoice(invoice);
        Long id = invoiceService.saveInvoice(invoice).getId();
        String message = "Record with ID: " + id + "is saved Successfully";
        model.addAttribute("message", message);
        return "registerInvoicePage";
    }

    @GetMapping("/all")
    public String getAllInvoices(
            @RequestParam(value = "message", required = false) String message, Model model) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        model.addAttribute("list", invoices);
        model.addAttribute("message", message);
        return "allInvoicesPage";
    }

    @GetMapping("/edit")
    public String getEditPage(
            Model model, RedirectAttributes redirectAttributes,
            @RequestParam Long id) {
        String page = null;
        try {
            Invoice invoice = invoiceService.getInvoiceById(id);
            model.addAttribute("invoice", invoice);
            page = "editInvoicePage";
        } catch (InvoiceNotFoundException invoiceNotFoundException) {
            invoiceNotFoundException.printStackTrace();
            redirectAttributes.addAttribute("message", invoiceNotFoundException.getMessage());
            page = "redirect:getAllInvoices";
        }
        return page;
    }

    @PostMapping("/update")
    public String updateInvoice(
            @ModelAttribute Invoice invoice,
            RedirectAttributes redirectAttributes) {
        invoiceService.updateInvoice(invoice);
        Long id = invoice.getId();
        redirectAttributes.addAttribute("message", "Invoice with ID:" + id + "is updated successfully.");
        return "redirect:getAllInvoices";
    }

    @DeleteMapping("delete")
    public String deleteInvoice(
            @RequestParam Long id,
            RedirectAttributes redirectAttributes) {
        try {
            invoiceService.deleteInvoice(id);
            redirectAttributes.addAttribute("message", "Invoice with ID:" + id + "is removed successfully");
        } catch (InvoiceNotFoundException invoiceNotFoundException) {
            invoiceNotFoundException.printStackTrace();
            redirectAttributes.addAttribute("message", invoiceNotFoundException.getMessage());
        }
        return "redirect:getAllInvoices";
    }


}
