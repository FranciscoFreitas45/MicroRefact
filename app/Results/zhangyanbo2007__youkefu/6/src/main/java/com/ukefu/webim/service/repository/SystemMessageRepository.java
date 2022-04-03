package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SystemMessage;
public interface SystemMessageRepository extends JpaRepository<SystemMessage, String>{


public List<SystemMessage> findByMsgtypeAndOrgi(String msgtype,String orgi)
;

public List<SystemMessage> findByOrgi(String orgi)
;

public SystemMessage findByIdAndOrgi(String id,String orgi)
;

}