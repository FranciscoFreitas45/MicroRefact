package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaLinkDao;
import com.ushahidi.swiftriver.core.model.Link;
@Service
public class LinkBucketDropLinkService {

@Autowired
 private JpaLinkDao jpalinkdao;


public void setLink(long idOZP4,Link link){
jpalinkdao.setLink(idOZP4,link);
}


public Link getLink(long idOZP4){
return jpalinkdao.getLink(idOZP4);
}


}