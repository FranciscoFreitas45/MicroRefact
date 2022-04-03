package com.hmm.logistics.stock.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.logistics.stock.entity.InDetailed;
import com.hmm.logistics.stock.entity.Stock;
public interface IInDetailedService {


public boolean existsById(Long id)
;

public InDetailed findById(Long id)
;

public InDetailed save(InDetailed entity)
;

public long count()
;

public void deleteById(Long id)
;

public void deleteAll(Long[] ids)
;

public List<InDetailed> findAll(Specification<InDetailed> spec)
;

}