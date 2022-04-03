package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.WorkTime;
public interface WorkTimeRepository extends JpaRepository<WorkTime, String>{


public int countByNameAndOrgi(String name,String orgi)
;

public List<WorkTime> findByOrgi(String hostid,String orgi)
;

public WorkTime findByIdAndOrgi(String id,String orgi)
;

}