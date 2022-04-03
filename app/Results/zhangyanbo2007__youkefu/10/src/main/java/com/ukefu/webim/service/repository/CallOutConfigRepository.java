package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.CallOutConfig;
public interface CallOutConfigRepository extends JpaRepository<CallOutConfig, String>{


public List<CallOutConfig> findByOrgi(String orgi)
;

public List<CallOutConfig> findByDataidAndOrgi(String dataid,String orgi)
;

public CallOutConfig findByIdAndOrgi(String id,String orgi)
;

}