package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;

import java.util.List;

public interface StatisticsService {

    /**
     * obecné statistiky pro všechny faktury
     * @return obecné statistiky pro všechny faktury
     */
    InvoiceStatisticsDTO getInvoicesStatistics();

    /**
     * statistiky pro jednotlivé osoby
     * @return seznam statistik pro jednotlivé osoby
     */
    List<PersonStatisticsDTO> getPersonStatistics();
}
