package com.hmm.logistics.stock.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.finance.logisticst.domain.InStorage;
import com.hmm.logistics.stock.entity.DoSend;
import com.hmm.logistics.stock.entity.InDetailed;
public interface IDoSendService {


public boolean existsById(Long id)
;

public DoSend findById(Long id)
;

public DoSend save(DoSend entity)
;

public long count()
;

public void deleteById(Long id)
;

public void deleteAll(Long[] ids)
;

public List<DoSend> findAll()
;

}