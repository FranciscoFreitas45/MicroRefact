package com.github.haseoo.courier.models;
 import com.github.haseoo.courier.enums.ClientType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType.LAZY;
import com.github.haseoo.courier.Request.ParcelModelRequest;
import com.github.haseoo.courier.Request.Impl.ParcelModelRequestImpl;
import com.github.haseoo.courier.DTO.ParcelModel;
@Entity
@Table(name = "Client")
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "userId")
public class ClientModel extends UserModel{

@Column(nullable = false)
 private  String emailAddress;

@Column
 private  String phoneNumber;

@Column(nullable = false)
 private  ClientType clientType;

@Transient
 private List<ParcelModel> sentParcels;

@ManyToMany(cascade = { PERSIST, MERGE })
@JoinTable(joinColumns = @JoinColumn(name = "clientId"), inverseJoinColumns = @JoinColumn(name = "parcelId"))
 private List<ParcelModel> observedParcels;

@Transient
 private ParcelModelRequest parcelmodelrequest = new ParcelModelRequestImpl();;


}