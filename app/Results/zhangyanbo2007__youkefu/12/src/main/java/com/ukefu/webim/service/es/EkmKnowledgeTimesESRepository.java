package com.ukefu.webim.service.es;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ukefu.webim.web.model.EkmKnowledgeTimes;
import com.ukefu.webim.web.model.User;
public interface EkmKnowledgeTimesESRepository {


public List<EkmKnowledgeTimes> findByKbidAndVersionAndOrgi(String kbid,int version,String orgi)
;

public Page<EkmKnowledgeTimes> findByOrgi(String orgi,User user,List<String> ekmKnowledgeMasterid,Pageable pageable)
;

public List<EkmKnowledgeTimes> findByKbidAndOrgi(String kbid,String orgi)
;

public void delete(List<EkmKnowledgeTimes> ekmKnowledgeTimes)
;

}