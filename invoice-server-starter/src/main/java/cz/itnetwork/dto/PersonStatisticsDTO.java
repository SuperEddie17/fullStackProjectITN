package cz.itnetwork.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonStatisticsDTO {

    private Long personId;
    private String personName;
    private Long revenue;
}
