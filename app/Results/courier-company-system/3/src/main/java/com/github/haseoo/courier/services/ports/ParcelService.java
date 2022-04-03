package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.servicedata.parcels.ParcelAddData;
import com.github.haseoo.courier.servicedata.parcels.ParcelData;
import com.github.haseoo.courier.servicedata.parcels.ParcelEditData;
import java.time.LocalDate;
import java.util.List;
public interface ParcelService {


public ParcelData add(ParcelAddData parcelAddData)
;

public ParcelData moveDate(Long id,char[] pin,LocalDate newDate)
;

public ParcelData edit(Long id,ParcelEditData parcelEditData)
;

public ParcelData getById(Long id)
;

public List<ParcelData> getList()
;

public ParcelData setParcelToReturn(Long id)
;

public void delete(Long id)
;

}