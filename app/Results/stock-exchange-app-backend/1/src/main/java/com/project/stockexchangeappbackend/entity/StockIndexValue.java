package com.project.stockexchangeappbackend.entity;
 import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Entity
@Table(name = "STOCK_INDEX_VALUES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StockIndexValue {

@Id
@GeneratedValue(generator = "STOCK_INDEX_VALUES_SEQUENCE")
 private  Long id;

@Column(name = "TIMESTAMP", nullable = false, updatable = false)
 private  OffsetDateTime timestamp;

@Column(name = "VALUE", nullable = false, updatable = false, precision = 14, scale = 2)
 private  BigDecimal value;

@ManyToOne
@JoinColumn(name = "STOCK_ID", nullable = false, updatable = false, referencedColumnName = "ID")
 private  Stock stock;


}