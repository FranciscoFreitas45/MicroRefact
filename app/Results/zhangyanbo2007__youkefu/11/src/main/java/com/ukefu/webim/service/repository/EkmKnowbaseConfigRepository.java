package com.ukefu.webim.service.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmKnowbaseConfig;
public interface EkmKnowbaseConfigRepository extends JpaRepository<EkmKnowbaseConfig, String>{


public EkmKnowbaseConfig findByKnowbaseidAndOrgi(String knowbaseid,String Orgi)
;

public EkmKnowbaseConfig findByBasehost(String basehost)
;

}