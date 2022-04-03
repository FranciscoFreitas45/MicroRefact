package com.project.stockexchangeappbackend.entity;
 import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import com.project.stockexchangeappbackend.DTO.Resource;
import com.project.stockexchangeappbackend.DTO.Tag;
import com.project.stockexchangeappbackend.Request.Impl.ResourceRequestImpl;
import com.project.stockexchangeappbackend.Request.Impl.TagRequestImpl;
import com.project.stockexchangeappbackend.Request.ResourceRequest;
import com.project.stockexchangeappbackend.Request.TagRequest;
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STOCKS")
public class Stock {

@Id
@GeneratedValue(generator = "STOCK_SEQUENCE")
 private  Long id;

@Column(name = "NAME", unique = true, nullable = false)
 private  String name;

@Column(name = "ABBREVIATION", unique = true, nullable = false)
 private  String abbreviation;

@Column(name = "CURRENT_PRICE", precision = 14, scale = 2, nullable = false)
 private  BigDecimal currentPrice;

@Column(name = "AMOUNT", nullable = false)
 private  Integer amount;

@Column(name = "PRICE_CHANGE_RATIO", nullable = false)
 private  Double priceChangeRatio;

@Transient
 private  List<Resource> resources;

@Column(name = "IS_DELETED", nullable = false)
 private  Boolean isDeleted;

@Transient
 private  Tag tag;

@Transient
 private ResourceRequest resourcerequest = new ResourceRequestImpl();;

@Column(name = "tag_id")
 private Long tag_id;

@Transient
 private TagRequest tagrequest = new TagRequestImpl();;


}