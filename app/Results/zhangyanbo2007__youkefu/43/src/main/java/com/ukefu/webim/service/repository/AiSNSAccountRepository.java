package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.AiSNSAccount;
public interface AiSNSAccountRepository extends JpaRepository<AiSNSAccount, String>{


public List<AiSNSAccount> findByOrgiAndAiid(String orgi,String aiid)
;

public List<AiSNSAccount> findByOrgi(String orgi)
;

public List<AiSNSAccount> findByOrgiAndSnsid(String orgi,String snsid)
;

}