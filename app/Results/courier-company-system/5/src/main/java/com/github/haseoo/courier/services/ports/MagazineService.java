package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.models.MagazineModel;
import com.github.haseoo.courier.servicedata.parcels.ParcelData;
import com.github.haseoo.courier.servicedata.places.AddressData;
import com.github.haseoo.courier.servicedata.places.MagazineAddOperationData;
import com.github.haseoo.courier.servicedata.places.MagazineData;
import com.github.haseoo.courier.servicedata.places.MagazineEditOperationData;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.Consumer;
public interface MagazineService {


public MagazineData add(MagazineAddOperationData magazineAddOperationData)
;

public MagazineData getById(Long id)
;

public MagazineData edit(Long id,MagazineEditOperationData magazineEditOperationData)
;

public List<MagazineData> getList()
;

public void consumeClosestMagazine(AddressData address,Consumer<MagazineModel> consumer)
;

public List<MagazineData> getActiveList()
;

public List<ParcelData> getParcelsToReturn(Long id)
;

public MagazineData addLogisitcians(Long magazineId,List<Long> logisticiansIds)
;

public List<ParcelData> getAssignedAtSenderParcels(Long id)
;

}