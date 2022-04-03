package com.github.haseoo.courier.DTO;
 import com.github.haseoo.courier.enums.ParcelStateType;
import lombok.Data;
import javax.persistence;
import java.time.LocalDateTime;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
public class ParcelStateRecord {

 private  Long id;

 private  ParcelModel parcel;

 private  ParcelStateType state;

 private  LocalDateTime changeDate;

 private  MagazineModel magazine;

 private  CourierModel courier;


public ParcelStateRecord defaultParcelStateRecord(){
    ParcelStateRecord parcelStateRecord = new ParcelStateRecord();
    parcelStateRecord.setChangeDate(LocalDateTime.now());
    parcelStateRecord.setState(ParcelStateType.AT_SENDER);
    return parcelStateRecord;
}


}