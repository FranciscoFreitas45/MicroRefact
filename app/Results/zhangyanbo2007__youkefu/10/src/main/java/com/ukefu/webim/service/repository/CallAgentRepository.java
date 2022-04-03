package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.CallAgent;
public interface CallAgentRepository extends JpaRepository<CallAgent, String>{


public List<CallAgent> findByOrgi(String orgi)
;

public List<CallAgent> findByOrgiAndActid(String orgi,String actid)
;

public List<CallAgent> findByNameAndOrgi(String name,String orgi)
;

public List<CallAgent> findByActidAndOrgi(String actid,String orgi)
;

public CallAgent findByIdAndOrgi(String id,String orgi)
;

}