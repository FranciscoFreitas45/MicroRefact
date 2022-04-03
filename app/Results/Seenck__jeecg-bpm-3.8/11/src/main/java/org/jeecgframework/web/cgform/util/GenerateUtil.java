package org.jeecgframework.web.cgform.util;
 import java.util.ArrayList;
import java.util.List;
import org.jeecgframework.core.enums.OnlineGenerateEnum;
public class GenerateUtil {


public List<OnlineGenerateEnum> getOnlineGenerateEnum(String type,String version,boolean isTree){
    List<OnlineGenerateEnum> list = new ArrayList<OnlineGenerateEnum>();
    for (OnlineGenerateEnum item : OnlineGenerateEnum.values()) {
        if (item.getFormType().equals(type) && item.getVersion().equals(version) && (item.isSupportTreegrid() || !isTree)) {
            list.add(item);
        }
    }
    return list;
}


}