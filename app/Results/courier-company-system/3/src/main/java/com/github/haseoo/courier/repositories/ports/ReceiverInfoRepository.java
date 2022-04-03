package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.ReceiverInfoModel;
import com.github.haseoo.courier.querydata.ReceiverInfoQueryData;
import java.util.List;
import java.util.Optional;
public interface ReceiverInfoRepository {


public ReceiverInfoModel saveAndFlush(ReceiverInfoModel receiverInfoModel)
;

public Optional<ReceiverInfoModel> getById(Long id)
;

public List<ReceiverInfoModel> getList()
;

public Optional<ReceiverInfoModel> receiverInfoExists(ReceiverInfoQueryData receiverInfoQueryData)
;

}