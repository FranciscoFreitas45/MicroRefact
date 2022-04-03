package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SaleStatus;
public interface SaleStatusRepository extends JpaRepository<SaleStatus, String>{


public List<SaleStatus> findByOrgiAndCateAndActivityid(String orgi,String cate,String activityid)
;

public Page<SaleStatus> findByOrgiAndCate(String orgi,String cate,Pageable page)
;

public List<SaleStatus> findByOrgi(String cate)
;

public List<SaleStatus> findByOrgiAndActivityid(String orgi,String activityid)
;

public SaleStatus findByOrgiAndName(String orgi,String name)
;

public SaleStatus findByIdAndOrgi(String id,String orgi)
;

public List<SaleStatus> findAll(Specification<SaleStatus> spec)
;

}