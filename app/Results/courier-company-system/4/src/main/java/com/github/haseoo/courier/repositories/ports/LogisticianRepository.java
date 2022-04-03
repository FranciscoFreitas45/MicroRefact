package com.github.haseoo.courier.repositories.ports;
 import com.github.haseoo.courier.models.LogisticianModel;
import java.util.List;
import java.util.Optional;
public interface LogisticianRepository {


public LogisticianModel saveAndFlush(LogisticianModel logisticianModel)
;

public Optional<LogisticianModel> getById(Long id)
;

public List<LogisticianModel> getList()
;

}