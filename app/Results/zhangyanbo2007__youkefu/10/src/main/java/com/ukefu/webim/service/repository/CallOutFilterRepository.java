package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.CallOutFilter;
public interface CallOutFilterRepository extends JpaRepository<CallOutFilter, String>{


public Page<CallOutFilter> findByOrgi(String orgi,Pageable page)
;

public List<CallOutFilter> findByNameAndOrgi(String name,String orgi)
;

public List<CallOutFilter> findByActidAndOrgi(String actid,String orgi)
;

public CallOutFilter findByIdAndOrgi(String id,String orgi)
;

public Page<CallOutFilter> findAll(Specification<CallOutFilter> spec,Pageable pageable)
;

}