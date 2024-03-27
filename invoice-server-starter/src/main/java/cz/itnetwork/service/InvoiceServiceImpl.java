package cz.itnetwork.service;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PersonService personService;





    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        entity = invoiceRepository.saveAndFlush(entity);
        entity.setBuyer(personRepository.findById(invoiceDTO.getBuyer().getId()).orElseThrow());
        entity.setSeller(personRepository.findById(invoiceDTO.getSeller().getId()).orElseThrow());


        return invoiceMapper.toDTO(entity);
    }

    @Override
    public List<InvoiceDTO> getAllInvoices(){
        return invoiceRepository.findAll()
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoiceById(long invoiceId) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(invoiceId);

        return invoiceMapper.toDTO(invoiceEntity);
    }


    public InvoiceDTO removeInvoice(long invoiceId) {
        InvoiceEntity invoice = fetchInvoiceById(invoiceId);//ziskani faktury z databaze
        InvoiceDTO model = invoiceMapper.toDTO(invoice);

        invoiceRepository.delete(invoice); //smazani faktury z databaze
        return model;

    }


    public InvoiceDTO editInvoice(long invoiceId, InvoiceDTO invoiceDTO) {
        invoiceDTO.setId(invoiceId);
        InvoiceEntity entity = fetchInvoiceById(invoiceId);

        entity.setBuyer(personService.fetchPersonById(invoiceDTO.getBuyer().getId()));
        entity.setSeller(personService.fetchPersonById(invoiceDTO.getSeller().getId()));

        invoiceMapper.updateInvoiceEntity(invoiceDTO, entity);

        InvoiceEntity saved = invoiceRepository.save(entity);

        return invoiceMapper.toDTO(saved);



    }




    private InvoiceEntity fetchInvoiceById(long id){
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }






}

