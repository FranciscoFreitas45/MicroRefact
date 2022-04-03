package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.ParcelModel;
import java.util.List;
import java.util.Optional;
public interface ParcelRepository {


public ParcelModel saveAndFlush(ParcelModel parcelModel)
;

public Optional<ParcelModel> getById(Long id)
;

public List<ParcelModel> getList()
;

public void delete(Long id)
;

}