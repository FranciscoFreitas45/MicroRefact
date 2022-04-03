package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.ComplicationManagementDao;
import org.jugbd.mnet.domain.ComplicationManagement;
@Service
public class ComplicationManagementRegisterService {

@Autowired
 private ComplicationManagementDao complicationmanagementdao;


public ComplicationManagement getComplicationManagement(Long id){
return complicationmanagementdao.getComplicationManagement(id);
}


public void setComplicationManagement(Long id,ComplicationManagement complicationManagement){
complicationmanagementdao.setComplicationManagement(id,complicationManagement);
}


}