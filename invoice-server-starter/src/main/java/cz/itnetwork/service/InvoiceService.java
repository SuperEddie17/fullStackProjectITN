package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceFilter;

import java.util.List;

public interface InvoiceService {
    /**
     * Přidá novou fakturu.
     *
     * @param invoiceDTO objekt reprezentující fakturu, která má být přidána
     * @return objekt reprezentující nově přidanou fakturu
     */
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    /**
     * Vrací seznam všech faktur s aplikovaným filtrem.
     *
     * @param invoiceFilter objekt obsahující filtry pro vyhledávání faktur
     * @return seznam faktur odpovídajících zadaným filtrům
     */
    List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter );

    /**
     * Vyhledá fakturu podle zadaného ID a vrátí ji ve formátu DTO.
     *
     * @param id ID hledané faktury
     * @return faktura odpovídající zadanému ID ve formátu DTO, nebo null, pokud faktura není nalezena
     */
    InvoiceDTO getInvoiceById(long id);

    /**
     * Odebere fakturu podle zadaného ID.
     *
     * @param invoiceId ID faktury, která má být odebrána
     */
    void removeInvoice(long invoiceId);

    /**
     * Upraví fakturu podle zadaného ID.
     *
     * @param invoiceId ID faktury, která má být upravena
     * @param invoiceDTO objekt reprezentující nový stav faktury
     * @return objekt reprezentující upravenou fakturu
     */
    InvoiceDTO editInvoice(long invoiceId,InvoiceDTO invoiceDTO);



}






