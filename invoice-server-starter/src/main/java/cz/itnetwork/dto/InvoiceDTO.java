package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    @JsonProperty("_id")
    private long id;

    private int invoiceNumber;

    private Date issued;

    private Date dueDate;

    private long price;

    private int vat;

    private String note;

    private PersonDTO buyer;

    private PersonDTO seller;


}
