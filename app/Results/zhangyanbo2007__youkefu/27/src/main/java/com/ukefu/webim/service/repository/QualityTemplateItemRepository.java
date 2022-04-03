package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QualityTemplateItem;
public interface QualityTemplateItemRepository extends JpaRepository<QualityTemplateItem, String>{


public List<QualityTemplateItem> findByTemplateidAndParentidAndOrgi(String templateid,String parentid,String orgi)
;

public List<QualityTemplateItem> findByTemplateidAndOrgi(String templateid,String orgi)
;

public List<QualityTemplateItem> findByOrgi(String orgi)
;

public Page<QualityTemplateItem> findByParentidAndOrgi(String parentid,String orgi,Pageable page)
;

public QualityTemplateItem findByNameAndOrgi(String name,String orgi)
;

public QualityTemplateItem findByTemplateidAndNameAndOrgi(String templateid,String name,String orgi)
;

public QualityTemplateItem findByTemplateidAndNameAndParentidAndOrgi(String templateid,String name,String parentid,String orgi)
;

public QualityTemplateItem findByIdAndOrgi(String id,String orgi)
;

public QualityTemplateItem findByNameAndParentidAndOrgi(String name,String parentid,String orgi)
;

}