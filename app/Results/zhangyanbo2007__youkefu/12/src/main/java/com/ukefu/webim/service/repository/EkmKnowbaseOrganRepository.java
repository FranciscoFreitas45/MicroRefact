package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmKnowbaseOrgan;
public interface EkmKnowbaseOrganRepository extends JpaRepository<EkmKnowbaseOrgan, String>{


public List<EkmKnowbaseOrgan> findByKnowbaseidAndOrgi(String knowbaseid,String orgi)
;

public List<EkmKnowbaseOrgan> findByOrganidAndOrgi(String organid,String orgi)
;

public EkmKnowbaseOrgan findByIdAndOrgi(String id,String orgi)
;

public EkmKnowbaseOrgan findByOrganidAndKnowbaseidAndOrgi(String organid,String knowbaseid,String orgi)
;

}