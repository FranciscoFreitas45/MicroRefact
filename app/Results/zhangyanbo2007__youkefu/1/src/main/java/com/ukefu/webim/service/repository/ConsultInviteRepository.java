package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.CousultInvite;
public interface ConsultInviteRepository extends JpaRepository<CousultInvite, String>{


public List<CousultInvite> findBySnsaccountid(String paramsnsaccountid)
;

public List<CousultInvite> findByOrgi(String orgi)
;

public CousultInvite findBySnsaccountidAndOrgi(String paramsnsaccountid,String orgi)
;

}