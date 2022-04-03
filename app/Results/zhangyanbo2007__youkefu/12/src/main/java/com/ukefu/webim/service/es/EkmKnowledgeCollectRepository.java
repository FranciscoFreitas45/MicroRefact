package com.ukefu.webim.service.es;
 import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.ukefu.webim.web.model.EkmKnowledgeCollect;
public interface EkmKnowledgeCollectRepository extends EkmKnowledgeCollectESRepository, ElasticsearchRepository<EkmKnowledgeCollect, String>{


}