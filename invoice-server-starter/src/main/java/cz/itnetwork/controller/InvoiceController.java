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


    //Přidá novou fakturu
    @PostMapping("/invoices")
    public  InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return  invoiceService.addInvoice(invoiceDTO);
    }

    //Vypíše všechny faktury i s filtrací
    @GetMapping("/invoices")
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter){
        return invoiceService.getAllInvoices(invoiceFilter);
    }

    //Vypíše určitou fakturu dle jejího ID
    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getPerson (@PathVariable long invoiceId){
        return invoiceService.getInvoiceById(invoiceId);
    }

    //Smaže fakturu, po smazání hodí status 204 no content
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/invoices/{invoiceId}")
    public void deleteInvoice(@PathVariable long invoiceId) {
         invoiceService.removeInvoice(invoiceId);
    }

    //upraví fakturu dle jejího ID
    @PutMapping({"/invoices/{invoiceId}", "/invoices/{invoiceId}"} )
    public InvoiceDTO editInvoice (@PathVariable long invoiceId, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.editInvoice(invoiceId,invoiceDTO);
    }










}

