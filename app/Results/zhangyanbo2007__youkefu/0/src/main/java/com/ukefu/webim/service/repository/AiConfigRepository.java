package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.AiConfig;
public interface AiConfigRepository extends JpaRepository<AiConfig, String>{


public List<AiConfig> findByOrgiAndAiid(String orgi,String aiid)
;

public List<AiConfig> findByOrgi(String orgi)
;

public List<AiConfig> findByIdAndOrgi(String aiid,String orgi)
;

public List<AiConfig> findByAiidAndOrgi(String aiid,String orgi)
;

}