package cz.itnetwork.controller;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceFilter;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter){
        return invoiceService.getAllInvoices(invoiceFilter);
    }

    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getPerson (@PathVariable long invoiceId){
        return invoiceService.getInvoiceById(invoiceId);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/invoices/{invoiceId}")
    public void deleteInvoice(@PathVariable long invoiceId) {
         invoiceService.removeInvoice(invoiceId);

    }

    @PutMapping({"/invoices/{invoiceId}", "/invoices/{invoiceId}"} )
    public InvoiceDTO editInvoice (@PathVariable long invoiceId, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.editInvoice(invoiceId,invoiceDTO);
    }










}

