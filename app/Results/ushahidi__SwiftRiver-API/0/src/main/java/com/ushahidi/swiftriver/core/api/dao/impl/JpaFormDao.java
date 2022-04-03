package com.ushahidi.swiftriver.core.api.dao.impl;
 import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.FormDao;
import com.ushahidi.swiftriver.core.model.Form;
import com.ushahidi.swiftriver.core.model.FormField;
@Repository
public class JpaFormDao extends AbstractJpaDao<Form>implements FormDao{


@Override
public Form create(Form form){
    form = super.create(form);
    if (form.getFields() != null) {
        for (FormField field : form.getFields()) {
            field.setForm(form);
            em.persist(field);
        }
    }
    return form;
}


public Form getActionOnObj(Long idVMYL)

public void setActionOnObj(Long idVMYL,Form actionOnObj)

public void setForms(long id,List<Form> forms)

public List<Form> getForms(long id)

public void setForm(Long id,Form form)

public void setAccount(Long id,Account account)

}