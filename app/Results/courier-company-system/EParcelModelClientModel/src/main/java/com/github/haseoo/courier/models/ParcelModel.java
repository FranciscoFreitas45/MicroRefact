package com.github.haseoo.courier.models;
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
import com.github.haseoo.courier.Request.AddressModelRequest;
import com.github.haseoo.courier.Request.Impl.AddressModelRequestImpl;
import com.github.haseoo.courier.DTO.AddressModel;
import com.github.haseoo.courier.Request.ParcelStateRecordRequest;
import com.github.haseoo.courier.Request.Impl.ParcelStateRecordRequestImpl;
import com.github.haseoo.courier.DTO.ParcelStateRecord;
@Entity
@Data
@Table(name = "Parcel")
public class ParcelModel {

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(nullable = false, insertable = false)
 private  Long id;

@Column(nullable = false)
 private  char[] pin;

@Transient
 private  ParcelTypeModel parcelType;

@Transient
 private  AddressModel deliveryAddress;

@Transient
 private  AddressModel senderAddress;

@ManyToOne(fetch = LAZY)
@JoinColumn(name = "clientId")
 private  ClientModel sender;

@ManyToOne(fetch = LAZY)
@JoinColumn(nullable = false, name = "receiverContactDataId")
 private  ReceiverInfoModel receiverContactData;

 private  LocalDate expectedCourierArrivalDate;

@Column(nullable = false)
 private  Boolean priority;

@Column(nullable = false)
 private  BigDecimal parcelFee;

@Column(nullable = false, name = "isPaid")
 private  Boolean paid;

@Column(nullable = false, name = "isDateMoved")
 private  Boolean dateMoved;

@Column(nullable = false, name = "isToReturn")
 private  Boolean toReturn;

@Transient
 private  List<ParcelStateRecord> parcelStates;

@ManyToMany(fetch = LAZY, mappedBy = "observedParcels")
 private List<ClientModel> observingClients;

@Column(name = "id")
 private Long id;

@Transient
 private ParcelTypeModelRequest parceltypemodelrequest = new ParcelTypeModelRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private AddressModelRequest addressmodelrequest = new AddressModelRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private AddressModelRequest addressmodelrequest = new AddressModelRequestImpl();;

@Transient
 private ParcelStateRecordRequest parcelstaterecordrequest = new ParcelStateRecordRequestImpl();;


}