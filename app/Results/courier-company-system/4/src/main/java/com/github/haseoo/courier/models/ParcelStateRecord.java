package com.github.haseoo.courier.models;
 import com.github.haseoo.courier.enums.ParcelStateType;
import lombok.Data;
import javax.persistence;
import java.time.LocalDateTime;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import com.github.haseoo.courier.Request.ParcelModelRequest;
import com.github.haseoo.courier.Request.Impl.ParcelModelRequestImpl;
import com.github.haseoo.courier.DTO.ParcelModel;
import com.github.haseoo.courier.Request.MagazineModelRequest;
import com.github.haseoo.courier.Request.Impl.MagazineModelRequestImpl;
import com.github.haseoo.courier.DTO.MagazineModel;
@Entity
@Data
@Table(name = "ParcelStateRecord")
public class ParcelStateRecord {

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(nullable = false, insertable = false)
 private  Long id;

@Transient
 private  ParcelModel parcel;

@Column(nullable = false)
 private  ParcelStateType state;

@Column(nullable = false)
 private  LocalDateTime changeDate;

@Transient
 private  MagazineModel magazine;

@ManyToOne(fetch = LAZY)
@JoinColumn(name = "courierId")
 private  CourierModel courier;

@Column(name = "id")
 private Long id;

@Transient
 private ParcelModelRequest parcelmodelrequest = new ParcelModelRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private MagazineModelRequest magazinemodelrequest = new MagazineModelRequestImpl();;


public ParcelStateRecord defaultParcelStateRecord(){
    ParcelStateRecord parcelStateRecord = new ParcelStateRecord();
    parcelStateRecord.setChangeDate(LocalDateTime.now());
    parcelStateRecord.setState(ParcelStateType.AT_SENDER);
    return parcelStateRecord;
}


}