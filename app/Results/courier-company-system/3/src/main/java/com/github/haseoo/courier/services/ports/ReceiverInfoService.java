package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.models.ReceiverInfoModel;
import com.github.haseoo.courier.servicedata.parcels.ReceiverInfoData;
import com.github.haseoo.courier.servicedata.parcels.ReceiverInfoOperationData;
import java.util.List;
import java.util.function.Consumer;
public interface ReceiverInfoService {


public ReceiverInfoData getById(Long id)
;

public List<ReceiverInfoData> getList()
;

public void consume(ReceiverInfoOperationData receiverInfoOperationData,Consumer<ReceiverInfoModel> consumer)
;

}