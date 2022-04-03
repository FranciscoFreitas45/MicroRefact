package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.WorkOrderType;
public interface WorkOrderTypeRepository extends JpaRepository<WorkOrderType, String>{


public int countByNameAndOrgi(String name,String orgi)
;

public Page<WorkOrderType> findByOrgi(String orgi,Pageable page)
;

public WorkOrderType findByIdAndOrgi(String id,String orgi)
;

}