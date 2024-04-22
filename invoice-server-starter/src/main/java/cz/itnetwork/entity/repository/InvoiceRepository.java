package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, PagingAndSortingRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    /**
     * obecné statistiky pro faktury pomocí query
     * @return obecné statistiky pro faktury
     */
    @Query(value = "SELECT NEW cz.itnetwork.dto.InvoiceStatisticsDTO(SUM(letosek.price), SUM(vsechno.price), COUNT(vsechno.id)) FROM invoice vsechno LEFT JOIN invoice letosek ON vsechno.id = letosek.id AND YEAR(letosek.issued) = YEAR(CURRENT_DATE)")
    InvoiceStatisticsDTO getInvoicesStatistics();











    }


