package com.github.haseoo.courier.models;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence;
import java.util.List;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import com.github.haseoo.courier.Request.ParcelModelRequest;
import com.github.haseoo.courier.Request.Impl.ParcelModelRequestImpl;
import com.github.haseoo.courier.DTO.ParcelModel;
import com.github.haseoo.courier.Request.ParcelModelRequest;
import com.github.haseoo.courier.Request.Impl.ParcelModelRequestImpl;
import com.github.haseoo.courier.DTO.ParcelModel;
@Entity
@Data
@EqualsAndHashCode
@Table(name = "Address")
public class AddressModel {

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(insertable = false, nullable = false)
 private  Long id;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String city;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String street;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String postalCode;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String buildingNumber;

@Column(columnDefinition = "TEXT")
 private  String flatNumber;

@Transient
 private List<ParcelModel> senderAddressParcels;

@Transient
 private List<ParcelModel> deliveryAddressParcels;

@Transient
 private ParcelModelRequest parcelmodelrequest = new ParcelModelRequestImpl();;

@Transient
 private ParcelModelRequest parcelmodelrequest = new ParcelModelRequestImpl();;


}