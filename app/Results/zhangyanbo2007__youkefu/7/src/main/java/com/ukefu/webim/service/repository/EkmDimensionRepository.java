package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmDimension;
public interface EkmDimensionRepository extends JpaRepository<EkmDimension, String>{


public List<EkmDimension> findByDatastatusAndOrgi(boolean datastatus,String orgi)
;

public EkmDimension findByNameAndDatastatusAndOrgi(String Name,boolean datastatus,String orgi)
;

public Page<EkmDimension> findAll(Specification<EkmDimension> spec,Pageable pageable)
;

public EkmDimension findByIdAndOrgi(String id,String orgi)
;

}