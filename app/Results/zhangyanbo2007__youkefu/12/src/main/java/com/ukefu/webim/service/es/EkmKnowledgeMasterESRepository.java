package com.ukefu.webim.service.es;
 import java.util.List;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ukefu.webim.web.model.EkmKnowledgeMaster;
import com.ukefu.webim.web.model.User;
public interface EkmKnowledgeMasterESRepository {


public List<EkmKnowledgeMaster> findByDatastatusAndOrgi(BoolQueryBuilder boolQueryBuilder,boolean datastatus,String orgi)
;

public List<EkmKnowledgeMaster> findByOrganAndDatastatusAndOrgi(String organ,boolean datastatus,String orgi)
;

public Page<EkmKnowledgeMaster> findByKnowtypeidAuth(boolean datastatus,List<String> EkmKnowledgeMasterType,String knowbaseid,String orgi,BoolQueryBuilder ranyQueryBuilder,Pageable pageable)
;

public EkmKnowledgeMaster findByTitleAndOrgi(String title,String orgi)
;

public List<EkmKnowledgeMaster> findByOrgi(String orgi)
;

public Page<EkmKnowledgeMaster> findBySearchKnowledge(boolean datastatus,String q,String knowbaseid,String knowledgetype,String orgi,Pageable pageable)
;

public Page<EkmKnowledgeMaster> findByCreaterAndDatastatusAndOrgi(String creater,boolean datastatus,String orgi)
;

public Page<EkmKnowledgeMaster> findByDatastatusAndKnowbaseidAndOrgi(boolean datastatus,String knowbaseid,String orgi,BoolQueryBuilder ranyQueryBuilder,Pageable pageable)
;

public List<EkmKnowledgeMaster> findByKnowbaseidAndDatastatusAndOrgi(String knowbaseid,boolean datastatus,String orgi)
;

public Page<EkmKnowledgeMaster> getByDimentypeidAndDatastatusAndOrgi(String dimentypeid,boolean datastatus,String orgi,Pageable pageable)
;

public Page<EkmKnowledgeMaster> getByDimenidAndDatastatusAndOrgi(String dimenid,boolean datastatus,String orgi,Pageable pageable)
;

public Page<EkmKnowledgeMaster> findByKnowbaseidAndKnowledgetypeidAndDatastatusAndOrgi(String knowbaseid,String knowledgetypeid,boolean datastatus,String orgi,BoolQueryBuilder ranyQueryBuilder,Pageable page)
;

public Page<EkmKnowledgeMaster> findByKnowledge(BoolQueryBuilder boolQueryBuilder,boolean datastatus,List<String> EkmKnowledgeMasterType,String orgi,User user,Pageable pageable)
;

public Page<EkmKnowledgeMaster> findByPubstatusAndDatastatusAndCreaterAndOrgi(String pubstatus,boolean datastatus,String creater,String orgi,Pageable pageable)
;

public EkmKnowledgeMaster findByIdAndOrgi(String id,String orgi)
;

public Page<EkmKnowledgeMaster> findByAllKnowledge(boolean datastatus,String orgi,Pageable pageable)
;

public List<EkmKnowledgeMaster> findByOrgiAndDatastatus(String orgi,boolean datastatus)
;

}