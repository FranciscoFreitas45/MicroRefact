package com.github.haseoo.courier.DTO;
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
public class ParcelStateRecord {

 private  Long id;

 private  ParcelModel parcel;

 private  ParcelStateType state;

 private  LocalDateTime changeDate;

 private  MagazineModel magazine;

 private  CourierModel courier;

 private Long id;

 private ParcelModelRequest parcelmodelrequest = new ParcelModelRequestImpl();;

 private Long id;

 private MagazineModelRequest magazinemodelrequest = new MagazineModelRequestImpl();;


public ParcelStateRecord defaultParcelStateRecord(){
    ParcelStateRecord parcelStateRecord = new ParcelStateRecord();
    parcelStateRecord.setChangeDate(LocalDateTime.now());
    parcelStateRecord.setState(ParcelStateType.AT_SENDER);
    return parcelStateRecord;
}


}