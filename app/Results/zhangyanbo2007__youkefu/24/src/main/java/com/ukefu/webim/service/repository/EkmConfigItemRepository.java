package com.ukefu.webim.service.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmConfigItem;
public interface EkmConfigItemRepository extends JpaRepository<EkmConfigItem, String>{


public EkmConfigItem findByOrgi(String orgi)
;

}