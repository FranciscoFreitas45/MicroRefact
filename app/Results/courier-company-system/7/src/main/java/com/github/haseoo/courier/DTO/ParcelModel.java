package com.github.haseoo.courier.DTO;
 import lombok.Data;
import javax.persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType.ALL;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
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


}