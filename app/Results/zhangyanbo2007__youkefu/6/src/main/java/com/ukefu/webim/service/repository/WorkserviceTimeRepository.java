package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.WorkserviceTime;
public interface WorkserviceTimeRepository extends JpaRepository<WorkserviceTime, String>{


public List<WorkserviceTime> findByOrgi(String orgi)
;

public WorkserviceTime findByIdAndOrgi(String id,String orgi)
;

}