package com.ukefu.webim.service.es;
 import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.ukefu.webim.service.es.EkmKnowledgeMasterESRepository;
import com.ukefu.webim.web.model.EkmKnowledgeMaster;
public interface EkmKnowledgeMasterRepository extends ElasticsearchRepository<EkmKnowledgeMaster, String>, EkmKnowledgeMasterESRepository{


}