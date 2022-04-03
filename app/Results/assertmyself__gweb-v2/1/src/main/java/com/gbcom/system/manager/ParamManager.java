package com.gbcom.system.manager;
 import com.gbcom.system.daoservice.SysParameterService;
import com.gbcom.system.domain.SysParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ParamManager {

@Autowired
 private  SysParameterService sysParameterService;


public SysParameter getParameterByCode(String code){
    return sysParameterService.findUnique("from SysParameter where code = " + code);
}


public String getParamValueByCode(String code){
    SysParameter parameter = getParameterByCode(code);
    return parameter.getValue();
}


public String getParamLongValueByCode(String code){
    SysParameter parameter = getParameterByCode(code);
    return parameter.getClobvalue();
}


}