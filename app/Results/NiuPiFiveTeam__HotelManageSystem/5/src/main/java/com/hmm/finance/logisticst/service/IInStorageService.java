package com.hmm.finance.logisticst.service;
 import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hmm.finance.logisticst.domain.InStorage;
import com.hmm.finance.logisticst.domain.InStorageDTO;
import com.hmm.finance.logisticst.domain.InStorageDetailedDTO;
public interface IInStorageService {


public void saveInStorageDetailedPrice(String listString)
;

public Page<InStorageDetailedDTO> findInStorageDetailedByInStorageId(String inStorageId,Pageable pageable)
;

public Page<InStorageDTO> findCompleteInStorage(Pageable pageable)
;

public void save(InStorage inStorage)
;

public void claim(String taskId,String employeeId)
;

public List<InStorageDTO> findTodoTasks(String employeeId,Pageable pageable)
;

public void complete(String taskId,Map<String,Object> variables)
;

public void startWorkflow(String inStorageId)
;

}