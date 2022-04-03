package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SceneItem;
public interface SceneItemRepository extends JpaRepository<SceneItem, String>{


public List<SceneItem> findByOrgiAndSceneidAndItemtype(String orgi,String sceneid,String itemtype)
;

public List<SceneItem> findByOrgiAndSceneid(String orgi,String sceneid)
;

public List<SceneItem> findByOrgiAndItemtype(String orgi,String itemtype)
;

public SceneItem findByIdAndOrgi(String id,String orgi)
;

}