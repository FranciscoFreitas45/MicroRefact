package com.ukefu.webim.service.es;
 import java.util.List;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ukefu.webim.web.model.EkmKnowledgeCollect;
public interface EkmKnowledgeCollectESRepository {


public List<EkmKnowledgeCollect> findByKnowledgeidAndOrgi(String knowledgeid,String orgi)
;

public List<EkmKnowledgeCollect> findByCreaterAndKnowledgeidAndOrgi(String creater,String knowledgeid,String orgi)
;

public EkmKnowledgeCollect findByCreaterAndKnowledgeidAndStatusAndOrgi(String creater,String knowledgeid,String status,String orgi)
;

public EkmKnowledgeCollect findByCreaterAndKbidAndStatusAndOrgi(String creater,String kbid,String status,String orgi)
;

public List<EkmKnowledgeCollect> findByStatusAndCreaterAndOrgi(String status,String creater,String orgi)
;

public List<EkmKnowledgeCollect> findByOrgi(String orgi)
;

public List<EkmKnowledgeCollect> findByCreaterAndStatusAndOrgi(String creater,String status,String orgi)
;

public List<EkmKnowledgeCollect> findByIdAndOrgi(String id,String orgi)
;

public void delete(List<EkmKnowledgeCollect> ekmKnowledgeCollectList)
;

public List<EkmKnowledgeCollect> findByCreaterAndOrgi(String creater,String orgi)
;

public List<EkmKnowledgeCollect> findByKnowledgeowerAndStatusAndOrgi(String knowledgeower,String status,String orgi)
;

public List<EkmKnowledgeCollect> findByCreaterAndStatusAndFolderidAndOrgi(String creater,String status,String folderid,String orgi)
;

}