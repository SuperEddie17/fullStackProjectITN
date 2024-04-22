package cz.itnetwork.dto;


import lombok.Data;

@Data
public class InvoiceFilter {
    private Long buyerID;

    private Long sellerID;

    private Long minPrice;

    private Long maxPrice;

    private Integer limit = 30;

    private String product;
}
