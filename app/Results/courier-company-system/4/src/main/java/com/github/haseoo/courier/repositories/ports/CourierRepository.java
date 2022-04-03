package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.CourierModel;
import java.util.List;
import java.util.Optional;
public interface CourierRepository {


public CourierModel saveAndFlush(CourierModel courierModel)
;

public Optional<CourierModel> getById(Long id)
;

public List<CourierModel> getList()
;

}