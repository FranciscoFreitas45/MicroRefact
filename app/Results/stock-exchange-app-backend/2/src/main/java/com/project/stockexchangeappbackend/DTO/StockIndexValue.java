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
public class StockIndexValue {

   
    private Long id;

    
    private OffsetDateTime timestamp;

   
    private BigDecimal value;

    
    private Stock stock;

}
