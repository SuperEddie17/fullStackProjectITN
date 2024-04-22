package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    PersonRepository personRepository;

    /**
     * obecné statistiky pro faktury
     * @return statistiky pro faktury
     */
    @Override
    public InvoiceStatisticsDTO getInvoicesStatistics() {
        return invoiceRepository.getInvoicesStatistics();
    }

    /**
     * statistiky pro jednotlivé osoby
     * @return seznam statistiky pro jednotlivé osoby
     */
    @Override
    public List<PersonStatisticsDTO> getPersonStatistics() {
        return personRepository.getPersonStatistics();
    }
}

