package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.CallOutTask;
public interface CallOutTaskRepository extends JpaRepository<CallOutTask, String>{


public List<CallOutTask> findByNameAndOrgi(String name,String orgi)
;

public List<CallOutTask> findByActidAndOrgi(String actid,String orgi)
;

public CallOutTask findByIdAndOrgi(String id,String orgi)
;

public Page<CallOutTask> findAll(Specification<CallOutTask> spec,Pageable pageable)
;

}