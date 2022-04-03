package io.delivery.service;
 import io.delivery.entity.Office;
import java.util.List;
public interface OfficeService {


public List<Office> getOfficeList()
;

public Office create(Office office)
;

public Office delete(Long id)
;

}