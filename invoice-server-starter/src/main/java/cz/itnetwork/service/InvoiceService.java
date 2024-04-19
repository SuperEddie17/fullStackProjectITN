package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceFilter;

import java.util.List;

public interface InvoiceService {
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter );

    InvoiceDTO getInvoiceById(long id);

    void removeInvoice(long invoiceId);

    InvoiceDTO editInvoice(long invoiceId,InvoiceDTO invoiceDTO);



}






