package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.ParcelTypeModel;
import java.util.List;
import java.util.Optional;
public interface ParcelTypeRepository {


public ParcelTypeModel saveAndFlush(ParcelTypeModel parcelTypeModel)
;

public Optional<ParcelTypeModel> getById(Long id)
;

public List<ParcelTypeModel> getList()
;

public void delete(Long id)
;

public List<ParcelTypeModel> getActiveTypes()
;

}