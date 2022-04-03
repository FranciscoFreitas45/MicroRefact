package com.github.haseoo.courier.DTO;
 import lombok.Data;
import javax.persistence;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
public class MagazineModel {

 private  Long id;

 private  AddressModel address;

 private  Boolean active;

 private  List<LogisticianModel> logisticians;

 private  List<ParcelStateRecord> parcelStates;


}