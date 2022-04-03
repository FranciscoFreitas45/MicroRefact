package com.project.stockexchangeappbackend.entity;
 import lombok.*;
import javax.persistence.*;
import com.project.stockexchangeappbackend.Request.StockRequest;
import com.project.stockexchangeappbackend.Request.Impl.StockRequestImpl;
import com.project.stockexchangeappbackend.DTO.*;
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RESOURCES")
public class Resource {

@Id
@GeneratedValue(generator = "RESOURCE_SEQUENCE")
 private  Long id;

@ManyToOne(cascade = CascadeType.MERGE)
@JoinColumn(name = "USER_ID", nullable = false, updatable = false, referencedColumnName = "ID")
 private  User user;

@Transient
 private  Stock stock;

@Column(name = "AMOUNT", nullable = false)
 private  Integer amount;

@Column(name = "stock_id")
 private Long stock_id;

@Transient
 private StockRequest stockrequest = new StockRequestImpl();;


}