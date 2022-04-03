package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.servicedata.parcels.ParcelData;
import com.github.haseoo.courier.servicedata.places.MagazineData;
import com.github.haseoo.courier.servicedata.users.employees.CourierData;
import java.util.List;
public interface ParcelStateService {


public CourierData setAsPickedByCourier(Long courierId,Long parcelId,boolean wasPaid)
;

public CourierData assignParcelsToCourier(Long courierId,List<Long> parcelIds)
;

public ParcelData setParcelReturned(Long courierId,Long parcelId,boolean wasPaid)
;

public MagazineData addParcelsToMagazine(Long magazineId,List<Long> parcelIds)
;

public ParcelData setParcelAsDelivered(Long courierId,Long parcelId,boolean wasPaid)
;

public void removeCurrentState(Long id)
;

}