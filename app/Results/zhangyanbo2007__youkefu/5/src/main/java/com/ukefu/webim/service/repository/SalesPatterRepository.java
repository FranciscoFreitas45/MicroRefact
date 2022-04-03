package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SalesPatter;
public interface SalesPatterRepository extends JpaRepository<SalesPatter, String>{


public List<SalesPatter> findByOrgi(String orgi)
;

public SalesPatter findByIdAndOrgi(String id,String orgi)
;

}