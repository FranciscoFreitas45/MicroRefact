package com.ukefu.webim.service.es;
 import java.util.Date;
import java.util.List;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ukefu.webim.web.model.EkmKnowledge;
import com.ukefu.webim.web.model.User;
public interface EkmKnowledgeESRepository {


public Page<EkmKnowledge> findByKnowtypeidAuth(boolean datastatus,List<String> ekmKnowledgeType,String knowbaseid,String orgi,Pageable pageable)
;

public EkmKnowledge findByTitleAndOrgi(String title,String orgi)
;

public Page<EkmKnowledge> findByDatastatusAndOrgiAndOwn(boolean datastatus,String orgi,User user,String own,Pageable pageable)
;

public Page<EkmKnowledge> findBySearchKnowledge(boolean datastatus,String q,String tag,String knowledgetype,String orgi,User user,Date begin,Date end,Pageable pageable)
;

public List<EkmKnowledge> findByCreaterAndDatastatusAndOrgi(String creater,boolean datastatus,String orgi)
;

public Page<EkmKnowledge> findByDatastatusAndKnowbaseidAndOrgi(boolean datastatus,String knowbaseid,String orgi,Pageable pageable)
;

public List<EkmKnowledge> findByKnowbaseidAndDatastatusAndOrgi(String knowbaseid,boolean datastatus,String orgi)
;

public Page<EkmKnowledge> getByDimentypeidAndDatastatusAndOrgi(String dimentypeid,boolean datastatus,String orgi,Pageable pageable)
;

public Page<EkmKnowledge> getByDimenidAndDatastatusAndOrgi(String dimenid,boolean datastatus,String orgi,Pageable pageable)
;

public Page<EkmKnowledge> findByKnowbaseidAndKnowledgetypeidAndDatastatusAndOrgi(String knowbaseid,String knowledgetypeid,boolean datastatus,String orgi,Pageable page)
;

public Page<EkmKnowledge> findByKnowledge(BoolQueryBuilder boolQueryBuilder,boolean datastatus,List<String> ekmKnowledgeType,String orgi,User user,Pageable pageable)
;

public EkmKnowledge findByIdAndOrgi(String id,String orgi)
;

public Page<EkmKnowledge> findByPubstatusAndDatastatusAndOrgiAndOwn(String pubstatus,boolean datastatus,String orgi,String own,User user,Pageable pageable)
;

public List<EkmKnowledge> findByOrgiAndDatastatus(String orgi,boolean datastatus)
;

}