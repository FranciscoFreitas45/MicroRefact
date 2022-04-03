package com.project.stockexchangeappbackend.DTO;

import lombok.*;

import javax.persistence.*;
import com.project.stockexchangeappbackend.entity.Stock;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    
    private Long id;

    
    private User user;

    
    private Stock stock;

   
    private Integer amount;

}
