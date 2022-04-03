package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.ComplicationManagement;
import org.springframework.stereotype.Component;
@Component
public interface ComplicationManagementService {


public ComplicationManagement save(ComplicationManagement complicationManagement)
;

public ComplicationManagement findOne(Long id)
;

public void delete(ComplicationManagement one)
;

}