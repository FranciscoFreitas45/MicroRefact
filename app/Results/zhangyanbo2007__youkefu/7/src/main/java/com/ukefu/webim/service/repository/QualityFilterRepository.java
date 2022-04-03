package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.CallOutFilter;
import com.ukefu.webim.web.model.QualityFilter;
public interface QualityFilterRepository extends JpaRepository<QualityFilter, String>{


public Page<QualityFilter> findByOrgi(String orgi,Pageable page)
;

public List<QualityFilter> findByNameAndOrgi(String name,String orgi)
;

public List<QualityFilter> findByActidAndOrgi(String actid,String orgi)
;

public QualityFilter findByIdAndOrgi(String id,String orgi)
;

public Page<QualityFilter> findAll(Specification<QualityFilter> spec,Pageable pageable)
;

}