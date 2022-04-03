package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmExperts;
public interface EkmExpertsRepository extends JpaRepository<EkmExperts, String>{


public EkmExperts findByRoleidAndOrgi(String userid,String orgi)
;

public EkmExperts findByUseridAndOrgi(String userid,String orgi)
;

public EkmExperts findByIdAndOrgi(String id,String orgi)
;

public List<EkmExperts> findByOrgiAndDatastatus(String orgi,boolean datastatus)
;

}