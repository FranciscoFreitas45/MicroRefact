package com.github.haseoo.courier.models;
 import lombok.Data;
import javax.persistence;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import com.github.haseoo.courier.Request.LogisticianModelRequest;
import com.github.haseoo.courier.Request.Impl.LogisticianModelRequestImpl;
import com.github.haseoo.courier.DTO.LogisticianModel;
import com.github.haseoo.courier.Request.ParcelStateRecordRequest;
import com.github.haseoo.courier.Request.Impl.ParcelStateRecordRequestImpl;
import com.github.haseoo.courier.DTO.ParcelStateRecord;
@Entity
@Data
@Table(name = "Magazine")
public class MagazineModel {

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(insertable = false, nullable = false)
 private  Long id;

@OneToOne(cascade = ALL)
 private  AddressModel address;

@Column(nullable = false)
 private  Boolean active;

@Transient
 private  List<LogisticianModel> logisticians;

@Transient
 private  List<ParcelStateRecord> parcelStates;

@Transient
 private LogisticianModelRequest logisticianmodelrequest = new LogisticianModelRequestImpl();;

@Transient
 private ParcelStateRecordRequest parcelstaterecordrequest = new ParcelStateRecordRequestImpl();;


}