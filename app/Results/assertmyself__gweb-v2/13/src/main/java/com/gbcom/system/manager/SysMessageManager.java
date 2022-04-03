package com.gbcom.system.manager;
 import java.util.List;
import com.gbcom.system.daoservice.SysMessageService;
import com.gbcom.system.domain.SysMessage;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysMessageManager {

@Autowired
 private  SysMessageService sysMessageService;


public int getUnreadNum(){
    return getUnreadNum(null);
}


public List<SysMessage> getMessages(Integer type){
    return getMessages(type, 2);
}


}