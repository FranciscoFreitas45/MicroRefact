package com.hmm.logistics.stock.service;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.logistics.stock.entity.OutStorage;
public interface IOutStorageService {


public boolean existsById(Long id)
;

public OutStorage findById(Long id)
;

public OutStorage save(OutStorage entity)
;

public long count()
;

public void deleteById(Long id)
;

public void deleteAll(Long[] ids)
;

public Page<OutStorage> findAll(Specification<OutStorage> spec,Pageable pageable)
;

}