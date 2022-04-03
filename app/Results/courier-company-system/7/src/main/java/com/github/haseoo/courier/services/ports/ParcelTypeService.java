package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.servicedata.parcels.ParcelTypeAddOperationData;
import com.github.haseoo.courier.servicedata.parcels.ParcelTypeData;
import com.github.haseoo.courier.servicedata.parcels.ParcelTypeEditOperationData;
import java.util.List;
public interface ParcelTypeService {


public ParcelTypeData add(ParcelTypeAddOperationData parcelType)
;

public ParcelTypeData edit(Long id,ParcelTypeEditOperationData parcelType)
;

public ParcelTypeData getById(Long id)
;

public List<ParcelTypeData> getList(boolean active)
;

public void delete(Long id)
;

}