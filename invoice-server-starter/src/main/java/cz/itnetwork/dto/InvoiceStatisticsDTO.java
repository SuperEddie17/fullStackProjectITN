package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceStatisticsDTO {

    private Long currentYearSum;

    private Long allTimeSum;

    private Long invoicesCount;
}
