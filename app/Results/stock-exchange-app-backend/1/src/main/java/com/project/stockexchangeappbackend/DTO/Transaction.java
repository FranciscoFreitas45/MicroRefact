package com.project.stockexchangeappbackend.DTO;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

   
    private Long id;

   
    private OffsetDateTime date;

    
    private Integer amount;

   
    private BigDecimal unitPrice;

   
    private ArchivedOrder buyingOrder;

    
    private ArchivedOrder sellingOrder;

}
