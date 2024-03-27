package cz.itnetwork.controller;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    @DeleteMapping("/invoices/{invoiceId}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable long invoiceId) {
        InvoiceDTO deletedInvoice = invoiceService.removeInvoice(invoiceId);
        if (deletedInvoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping({"/invoices/{invoiceId}", "/invoices/{invoiceId}"} )
    public InvoiceDTO editInvoice (@PathVariable long invoiceId, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.editInvoice(invoiceId,invoiceDTO);
    }










}

