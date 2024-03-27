package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    List<InvoiceDTO> getAllInvoices();

    InvoiceDTO getInvoiceById(long id);

    InvoiceDTO removeInvoice(long invoiceId);

    InvoiceDTO editInvoice(long invoiceId,InvoiceDTO invoiceDTO);



}






