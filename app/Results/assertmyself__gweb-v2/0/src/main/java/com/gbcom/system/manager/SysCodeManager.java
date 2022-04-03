package com.gbcom.system.manager;
 import com.gbcom.system.daoservice.SysCodeDetailService;
import com.gbcom.system.domain.SysCodeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SysCodeManager {

@Autowired
 private  SysCodeDetailService sysCodeDetailService;


public SysCodeDetail getCodeDetailByName(String mainCode,String detailName){
    String hql = "from SysCodeDetail t where t.sysCode.code = '" + mainCode + "' and t.name = '" + detailName + "'";
    List<SysCodeDetail> list = sysCodeDetailService.findByQuery(hql);
    if (list.size() > 0) {
        return list.iterator().next();
    }
    return null;
}


public SysCodeDetail getCodeDetailByCode(String mainCode,String detailCode){
    String hql = "from SysCodeDetail t where t.sysCode.code = '" + mainCode + "' and t.code = '" + detailCode + "'";
    List<SysCodeDetail> list = sysCodeDetailService.findByQuery(hql);
    if (list.size() > 0) {
        return list.iterator().next();
    }
    return null;
}


public SysCodeDetail getCodeListById(Long codeDetailId){
    return sysCodeDetailService.get(codeDetailId);
}


public List<SysCodeDetail> getCodeListByCode(String code){
    String hql = "from SysCodeDetail t where t.sysCode.code = '" + code + "' order by t.treeId";
    return sysCodeDetailService.findByQuery(hql);
}


}