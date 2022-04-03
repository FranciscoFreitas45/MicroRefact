package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.CallOutTask;
import com.ukefu.webim.web.model.QualityActivityTask;
public interface QualityActivityTaskRepository extends JpaRepository<QualityActivityTask, String>{


public List<QualityActivityTask> findByNameAndOrgi(String name,String orgi)
;

public List<QualityActivityTask> findByActidAndOrgi(String actid,String orgi)
;

public QualityActivityTask findByIdAndOrgi(String id,String orgi)
;

public Page<QualityActivityTask> findAll(Specification<QualityActivityTask> spec,Pageable pageable)
;

}