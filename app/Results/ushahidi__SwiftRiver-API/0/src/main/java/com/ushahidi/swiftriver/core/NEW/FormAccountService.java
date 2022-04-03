package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaFormDao;
import com.ushahidi.swiftriver.core.model.Form;
@Service
public class FormAccountService {

@Autowired
 private JpaFormDao jpaformdao;


public void setForms(long id,List<Form> forms){
jpaformdao.setForms(id,forms);
}


public List<Form> getForms(long id){
return jpaformdao.getForms(id);
}


}