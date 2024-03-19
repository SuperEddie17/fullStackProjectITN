package cz.itnetwork.controller;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoices")
    public  InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return  invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getPerson (@PathVariable long invoiceId){
        return invoiceService.getInvoiceById(invoiceId);
    }










}

