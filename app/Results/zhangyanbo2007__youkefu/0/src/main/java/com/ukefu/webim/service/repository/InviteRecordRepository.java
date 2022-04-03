package com.ukefu.webim.service.repository;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.InviteRecord;
public interface InviteRecordRepository extends JpaRepository<InviteRecord, String>{


public Page<InviteRecord> findByUseridAndOrgi(String userid,String orgi,Pageable page)
;

public InviteRecord findByUseridAndAgentnoAndOrgi(String userid,String agentno,String orgi)
;

}