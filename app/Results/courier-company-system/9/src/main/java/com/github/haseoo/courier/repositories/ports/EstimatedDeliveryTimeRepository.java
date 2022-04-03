package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.EstimatedDeliveryTimeModel;
public interface EstimatedDeliveryTimeRepository {


public EstimatedDeliveryTimeModel saveAndFlush(EstimatedDeliveryTimeModel estimatedDeliveryTimeModel)
;

public EstimatedDeliveryTimeModel getById(Long id)
;

}