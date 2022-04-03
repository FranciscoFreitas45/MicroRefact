package com.github.haseoo.courier.DTO;
 import com.github.haseoo.courier.enums.ClientType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType.LAZY;
public class ClientModel extends UserModel{

 private  String emailAddress;

 private  String phoneNumber;

 private  ClientType clientType;

 private List<ParcelModel> sentParcels;

 private List<ParcelModel> observedParcels;


}