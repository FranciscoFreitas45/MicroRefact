package com.ukefu.webim.service.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmKwSearchTag;
public interface EkmSearchTagRepository extends JpaRepository<EkmKwSearchTag, String>{


public EkmKwSearchTag findByTagAndOrgi(String tag,String orgi)
;

}