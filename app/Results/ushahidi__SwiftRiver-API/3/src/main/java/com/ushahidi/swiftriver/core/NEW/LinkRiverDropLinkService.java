package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaLinkDao;
import com.ushahidi.swiftriver.core.model.Link;
@Service
public class LinkRiverDropLinkService {

@Autowired
 private JpaLinkDao jpalinkdao;


public void setLink(long idM175,Link link){
jpalinkdao.setLink(idM175,link);
}


public Link getLink(long idM175){
return jpalinkdao.getLink(idM175);
}


}