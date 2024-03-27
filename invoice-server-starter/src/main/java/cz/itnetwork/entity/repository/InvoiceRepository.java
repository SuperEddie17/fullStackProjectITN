package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    @Query(value = "SELECT NEW cz.itnetwork.dto.InvoiceStatisticsDTO(SUM(letosek.price), SUM(vsechno.price), COUNT(vsechno.id)) FROM invoice vsechno LEFT JOIN invoice letosek ON vsechno.id = letosek.id AND YEAR(letosek.issued) = YEAR(CURRENT_DATE)")
    InvoiceStatisticsDTO getInvoicesStatistics();








    }


