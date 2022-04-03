package org.jeecgframework.web.system.dao;
 import java.util.List;
import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.springframework.stereotype.Repository;
@Repository("jeecgDictDao")
public interface JeecgDictDao {


@Arguments({ "dicCode" })
@ResultType(DictEntity.class)
public List<DictEntity> querySystemDict(String dicCode)
;

@Arguments({ "dicTable", "dicCode", "dicText" })
@ResultType(DictEntity.class)
public List<DictEntity> queryCustomDict(String dicTable,String dicCode,String dicText)
;

}