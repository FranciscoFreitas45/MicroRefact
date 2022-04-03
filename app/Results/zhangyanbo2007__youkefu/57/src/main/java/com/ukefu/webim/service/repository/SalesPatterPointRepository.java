package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SalesPatterPoint;
public interface SalesPatterPointRepository extends JpaRepository<SalesPatterPoint, String>{


public List<SalesPatterPoint> findByOrgi(String orgi)
;

public SalesPatterPoint findByIdAndOrgi(String id,String orgi)
;

public List<SalesPatterPoint> findByQuestionidAndOrgi(String questionid,String orgi)
;

public List<SalesPatterPoint> findAll(Specification<SalesPatterPoint> spec)
;

}