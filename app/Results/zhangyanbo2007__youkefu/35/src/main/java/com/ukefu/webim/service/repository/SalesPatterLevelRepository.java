package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SalesPatterLevel;
public interface SalesPatterLevelRepository extends JpaRepository<SalesPatterLevel, String>{


public List<SalesPatterLevel> findByOrgi(String orgi)
;

public SalesPatterLevel findByIdAndOrgi(String id,String orgi)
;

public List<SalesPatterLevel> findAll(Specification<SalesPatterLevel> spec)
;

public List<SalesPatterLevel> findByProcessidAndOrgi(String processid,String orgi)
;

}