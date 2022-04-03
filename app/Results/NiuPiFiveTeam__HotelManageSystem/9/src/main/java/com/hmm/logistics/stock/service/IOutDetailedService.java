package com.hmm.logistics.stock.service;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.logistics.stock.entity.OutDetailed;
public interface IOutDetailedService {


public boolean existsById(Long id)
;

public OutDetailed findById(Long id)
;

public OutDetailed save(OutDetailed entity)
;

public long count()
;

public void deleteById(Long id)
;

public void deleteAll(Long[] ids)
;

public Page<OutDetailed> findAll(Specification<OutDetailed> spec,Pageable pageable)
;

}