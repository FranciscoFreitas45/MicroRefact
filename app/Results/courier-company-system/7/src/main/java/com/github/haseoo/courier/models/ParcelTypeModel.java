package com.github.haseoo.courier.models;
 import lombok.Data;
import javax.persistence;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import com.github.haseoo.courier.Request.ParcelModelRequest;
import com.github.haseoo.courier.Request.Impl.ParcelModelRequestImpl;
import com.github.haseoo.courier.DTO.ParcelModel;
@Entity
@Data
@Table(name = "ParcelType")
public class ParcelTypeModel {

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(nullable = false, insertable = false)
 private  Long id;

@Column(nullable = false)
 private  String name;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String description;

@Column(nullable = false, scale = 2)
 private  BigDecimal price;

@Column(nullable = false, name = "isActive")
 private  Boolean active;

@Transient
 private List<ParcelModel> parcels;

@Transient
 private ParcelModelRequest parcelmodelrequest = new ParcelModelRequestImpl();;


}