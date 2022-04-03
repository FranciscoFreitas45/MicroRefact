package org.jeecgframework.web.cgform.enhance;
 import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.LogUtil;
import org.springframework.stereotype.Service;
import java.util.Map;
@Service("cgformJavaInterDemo")
public class CgformJavaInterDemo implements CgformEnhanceJavaInter{


@Override
public void execute(String tableName,Map map){
    LogUtil.info("============调用[java增强]成功!========tableName:" + tableName + "===map===" + map);
}


}