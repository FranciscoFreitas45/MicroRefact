package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaFormDao;
import com.ushahidi.swiftriver.core.model.Form;
@Service
public class FormFormActivityService {

@Autowired
 private JpaFormDao jpaformdao;


public Form getActionOnObj(Long idVMYL){
return jpaformdao.getActionOnObj(idVMYL);
}


public void setActionOnObj(Long idVMYL,Form actionOnObj){
jpaformdao.setActionOnObj(idVMYL,actionOnObj);
}


}