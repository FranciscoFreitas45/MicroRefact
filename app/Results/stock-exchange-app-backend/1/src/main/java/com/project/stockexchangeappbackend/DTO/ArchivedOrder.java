package com.project.stockexchangeappbackend.DTO;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import com.project.stockexchangeappbackend.entity.Stock;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArchivedOrder {

   
    private Long id;

    
    private User user;

   
    private Stock stock;

   
    private Integer amount;

   
    private Integer remainingAmount;

   
    private OrderType orderType;

    
    private PriceType priceType;

   
    private BigDecimal price;

  
    private OffsetDateTime dateCreation;

  
    private OffsetDateTime dateExpiration;

   
    private OffsetDateTime dateClosing;

}
