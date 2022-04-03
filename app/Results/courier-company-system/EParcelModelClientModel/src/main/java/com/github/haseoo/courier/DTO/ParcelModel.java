package com.github.haseoo.courier.DTO;
 import lombok.Data;
import javax.persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType.ALL;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import com.github.haseoo.courier.Request.ParcelTypeModelRequest;
import com.github.haseoo.courier.Request.Impl.ParcelTypeModelRequestImpl;
import com.github.haseoo.courier.DTO.ParcelTypeModel;
import com.github.haseoo.courier.Request.AddressModelRequest;
import com.github.haseoo.courier.Request.Impl.AddressModelRequestImpl;
import com.github.haseoo.courier.DTO.AddressModel;
public class ParcelModel {

 private  Long id;

 private  char[] pin;

 private  ParcelTypeModel parcelType;

 private  AddressModel deliveryAddress;

 private  AddressModel senderAddress;

 private  ClientModel sender;

 private  ReceiverInfoModel receiverContactData;

 private  LocalDate expectedCourierArrivalDate;

 private  Boolean priority;

 private  BigDecimal parcelFee;

 private  Boolean paid;

 private  Boolean dateMoved;

 private  Boolean toReturn;

 private  List<ParcelStateRecord> parcelStates;

 private List<ClientModel> observingClients;

 private Long id;

 private ParcelTypeModelRequest parceltypemodelrequest = new ParcelTypeModelRequestImpl();;

 private Long id;

 private AddressModelRequest addressmodelrequest = new AddressModelRequestImpl();;

 private Long id;


}