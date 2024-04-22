package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    //zobrazení obecných statistik pro všechny faktury
    @GetMapping("/invoices/statistics")
    public InvoiceStatisticsDTO getInvoicesStatistics() {
        return statisticsService.getInvoicesStatistics();
    }

    //zobrazení statitik pro jednotlivé osoby
    @GetMapping("/persons/statistics")
    public List<PersonStatisticsDTO> getPersonsStatistics() {return statisticsService.getPersonStatistics();}
}
