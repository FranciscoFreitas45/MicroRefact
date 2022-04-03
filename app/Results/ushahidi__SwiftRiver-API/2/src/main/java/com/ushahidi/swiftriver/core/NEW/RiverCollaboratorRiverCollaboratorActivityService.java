package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaRiverDao;
import com.ushahidi.swiftriver.core.model.RiverCollaborator;
@Service
public class RiverCollaboratorRiverCollaboratorActivityService {

@Autowired
 private JpaRiverDao jpariverdao;


public RiverCollaborator getActionOnObj(Long idD9W3){
return jpariverdao.getActionOnObj(idD9W3);
}


public void setActionOnObj(Long idD9W3,RiverCollaborator actionOnObj){
jpariverdao.setActionOnObj(idD9W3,actionOnObj);
}


}