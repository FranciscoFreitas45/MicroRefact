package com.gbcom.system.manager;
 import com.gbcom.system.daoservice.SysParameterService;
import com.gbcom.system.domain.SysParameter;
import com.gbcom.system.domain.bean.param.Constraint;
import com.gbcom.system.utils.Constants;
import com.hc.core.utils.StringHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gbcom.DTO.Constraint;
@Service
public class SysParameterManager {

@SuppressWarnings("unused")
 private  Logger logger;

@Autowired
 private  SysParameterService sysParameterService;


public SysParameter getGlobalSysParameter(String code){
    String hql = "from SysParameter where code='" + code + "'";
    SysParameter sysParameter = sysParameterService.findUnique(hql);
    return sysParameter;
}


public SysParameter getSysParameter(String code){
    SysParameter localParameter = sysParameterService.findUniqueByProperty("code", code);
    if (null != localParameter) {
        return localParameter;
    }
    return getGlobalSysParameter(code);
}


public String getDisplayValue(SysParameter sysParameter,Constraint constraint){
    String value = sysParameter.getValue();
    if ("Text".equals(constraint.getType())) {
        value = sysParameter.getClobvalue();
    } else if ("Boolean".equals(constraint.getType())) {
        if (Constants.FLAG_FALSE.equals(value)) {
            value = "否";
        } else {
            value = "是";
        }
    } else if ("Enum".equals(constraint.getType())) {
        Map<String, String> map = getEnumMap(constraint);
        value = map.get(value);
    }
    return value;
}


public List<SysParameter> getSysParameters(){
    List<SysParameter> result = new ArrayList<SysParameter>();
    List<SysParameter> sysParameters = sysParameterService.findByQuery("from SysParameter where project is null order by code");
    for (SysParameter sysParameter : sysParameters) {
        result.add(sysParameter);
    }
    return result;
}


public String getSysParameterValue(SysParameter sysParameter){
    String value = sysParameter.getClobvalue();
    if (StringHelper.isEmpty(value)) {
        value = sysParameter.getValue();
    }
    return value;
}


public Map<String,String> getEnumMap(Constraint constraint){
    Map<String, String> map = new HashMap<String, String>();
    if ("Enum".equals(constraint.getType())) {
        String definition = constraint.getDefinition();
        String[] entrySet = StringUtils.split(definition, ",");
        for (String entry : entrySet) {
            int index = entry.indexOf("=");
            if (-1 != index) {
                String key = entry.substring(0, index);
                String value = entry.substring(index + 1);
                map.put(key, value);
            } else {
                map.put(entry, entry);
            }
        }
    }
    return map;
}


}