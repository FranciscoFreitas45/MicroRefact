package org.jeecgframework.web.cgform.service.template;
 import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;
import java.io.Serializable;
import java.util.List;
public interface CgformTemplateServiceI extends CommonService{


public List<CgformTemplateEntity> getTemplateListByType(String type)
;

public CgformTemplateEntity findByCode(String code)
;

public Serializable save(T entity)
;

public boolean doDelSql(CgformTemplateEntity t)
;

public boolean doUpdateSql(CgformTemplateEntity t)
;

public boolean doAddSql(CgformTemplateEntity t)
;

public void delete(T entity)
;

public void saveOrUpdate(T entity)
;

}