package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SystemUpdatecon;
public interface SystemUpdateconRepository extends JpaRepository<SystemUpdatecon, String>{


public List<SystemUpdatecon> findByOrgi(String orgi)
;

public SystemUpdatecon findByIdAndOrgi(String id,String orgi)
;

}