package com.ukefu.webim.service.es;
 import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.ukefu.webim.web.model.Contacts;
public interface ContactsRepository extends ContactsEsCommonRepository, ElasticsearchRepository<Contacts, String>{


public int countByPhoneAndOrgi(String phone,String orgi)
;

}