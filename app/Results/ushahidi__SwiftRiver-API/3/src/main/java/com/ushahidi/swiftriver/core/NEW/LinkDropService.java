package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaLinkDao;
import com.ushahidi.swiftriver.core.model.Link;
@Service
public class LinkDropService {

@Autowired
 private JpaLinkDao jpalinkdao;


public Link getOriginalUrl(long idLCK8){
return jpalinkdao.getOriginalUrl(idLCK8);
}


public void setOriginalUrl(long idLCK8,Link originalUrl){
jpalinkdao.setOriginalUrl(idLCK8,originalUrl);
}


}