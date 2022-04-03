package org.jeecgframework.web.cgform.service.enhance;
 import java.io.Serializable;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity;
public interface CgformEnhanceJavaServiceI extends CommonService{


public CgformEnhanceJavaEntity getCgformEnhanceJavaEntityByCodeFormId(String buttonCode,String formId)
;

public boolean checkClassOrSpringBeanIsExist(CgformEnhanceJavaEntity cgformEnhanceJavaEntity)
;

public Serializable save(T entity)
;

public List<CgformEnhanceJavaEntity> checkCgformEnhanceJavaEntity(CgformEnhanceJavaEntity cgformEnhanceJavaEntity)
;

public boolean doDelSql(CgformEnhanceJavaEntity t)
;

public boolean doUpdateSql(CgformEnhanceJavaEntity t)
;

public boolean doAddSql(CgformEnhanceJavaEntity t)
;

public void delete(T entity)
;

public void saveOrUpdate(T entity)
;

}