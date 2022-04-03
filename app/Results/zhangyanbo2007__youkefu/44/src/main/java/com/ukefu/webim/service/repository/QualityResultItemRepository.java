package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QualityResultItem;
public interface QualityResultItemRepository extends JpaRepository<QualityResultItem, String>{


public List<QualityResultItem> findByResultidAndOrgi(String resultid,String orgi)
;

public List<QualityResultItem> findByOrgi(String orgi)
;

public QualityResultItem findByResultidAndItemidAndOrgi(String resultid,String itemid,String orgi)
;

public List<QualityResultItem> findByResultidAndParentidAndOrgi(String resultid,String parentid,String orgi)
;

public QualityResultItem findByIdAndOrgi(String id,String orgi)
;

}