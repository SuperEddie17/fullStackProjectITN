package cz.itnetwork.service;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceFilter;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;



    /**
     * funkce pro vytvoření nové faktury
     * @param invoiceDTO objekt reprezntující fakturu, která má být vytvořena
     * @return nově vytvořená faktura
     */
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        //Nastavení kupujícího a prodávajícího na základě ID v InvoiceDTO
        entity.setBuyer(personRepository.findById(invoiceDTO.getBuyer().getId()).orElseThrow());
        entity.setSeller(personRepository.findById(invoiceDTO.getSeller().getId()).orElseThrow());
        entity = invoiceRepository.saveAndFlush(entity);

        return invoiceMapper.toDTO(entity);
    }

    /**
     * vrací seznam všech faktur s aplikovaným filtrem
     * @param invoiceFilter objekt obsahující filtry pro faktury
     * @return seznam faktur odpovídající zadaným filtrům
     */
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter){
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);
        //Vyhledávání všech faktur podle specifikace a omezení na požadovaný počet výsledků
        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * funkce pro nalezení faktury dle jejího ID
     * @param invoiceId ID hledané faktury
     * @return vrátí fakturu podle zadaného ID nebo null, pokud faktura neexistuje
     */
    @Override
    public InvoiceDTO getInvoiceById(Long invoiceId) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(invoiceId);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    /**
     * funkce pro smazání faktury
     * @param invoiceId ID hledané faktury
     */
    public  void removeInvoice(Long invoiceId) {
        InvoiceEntity invoice = fetchInvoiceById(invoiceId);//ziskani faktury z databaze
        invoiceRepository.delete(invoice); //smazani faktury z databaze
    }

    /**
     * funkce pro editování faktury
     * @param invoiceId ID hledané faktury
     * @param invoiceDTO upravovaná faktura
     * @return vrátí upravenou fakturu
     */
    public InvoiceDTO editInvoice(Long invoiceId, InvoiceDTO invoiceDTO) {
        //nalezení faktury dle ID
        InvoiceEntity entity = fetchInvoiceById(invoiceId);
        //nastavení ID faktury
        invoiceDTO.setId(invoiceId);
        invoiceMapper.updateInvoiceEntity(invoiceDTO, entity);
        InvoiceEntity saved = invoiceRepository.save(entity);
        return invoiceMapper.toDTO(saved);
    }

    /**
     * funkce pro nalezení faktury dle ID
     * @param id ID faktury
     * @return vrátí celý objekt hledané faktury, pokud ne, vypíše hlášku o nenalezení
     */
    private InvoiceEntity fetchInvoiceById(Long id){
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }






}

