package com.github.haseoo.courier.DTO;
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
public class AddressModel {

 private  Long id;

 private  String city;

 private  String street;

 private  String postalCode;

 private  String buildingNumber;

 private  String flatNumber;

 private List<ParcelModel> senderAddressParcels;

 private List<ParcelModel> deliveryAddressParcels;


}