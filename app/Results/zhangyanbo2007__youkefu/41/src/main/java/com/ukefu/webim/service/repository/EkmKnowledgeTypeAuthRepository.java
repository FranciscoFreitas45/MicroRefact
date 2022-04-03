package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmKnowledgeTypeAuth;
public interface EkmKnowledgeTypeAuthRepository extends JpaRepository<EkmKnowledgeTypeAuth, String>{


public List<EkmKnowledgeTypeAuth> findByKnowledgebaseidAndOrgi(String knowledgebaseid,String orgi)
;

public EkmKnowledgeTypeAuth findByUseridAndKnowledgetypeidAndOrgi(String userid,String Knowledgetypeid,String orgi)
;

public List<EkmKnowledgeTypeAuth> findByOrgi(String orgi)
;

public List<EkmKnowledgeTypeAuth> findByKnowledgetypeidAndOrgi(String knowledgetypeid,String orgi)
;

public List<EkmKnowledgeTypeAuth> findByUseridAndViewAndOrgi(String userid,boolean view,String orgi)
;

public List<EkmKnowledgeTypeAuth> findByKnowledgetypeidAndKnowledgebaseidAndOrgi(String knowledgetypeid,String Knowledgebaseid,String orgi)
;

public List<EkmKnowledgeTypeAuth> findByUseridAndOrgi(String userid,String orgi)
;

public EkmKnowledgeTypeAuth findByIdAndOrgi(String id,String orgi)
;

public List<EkmKnowledgeTypeAuth> findByUseridAndKnowledgebaseidAndViewAndOrgi(String userid,String knowledgebaseid,boolean view,String orgi)
;

public List<EkmKnowledgeTypeAuth> findByUseridAndKnowledgebaseidAndOrgi(String userid,String knowledgebaseid,String orgi)
;

}