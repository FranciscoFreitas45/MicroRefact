package com.ushahidi.swiftriver.core.api.dao.impl;
 import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.RiverDropFormDao;
import com.ushahidi.swiftriver.core.model.RiverDropForm;
import com.ushahidi.swiftriver.core.model.RiverDropFormField;
@Repository
public class JpaRiverDropFormDao extends AbstractJpaDao<RiverDropForm>implements RiverDropFormDao{


@Override
public RiverDropForm create(RiverDropForm dropForm){
    dropForm = super.create(dropForm);
    if (dropForm.getValues() != null) {
        for (RiverDropFormField fieldValue : dropForm.getValues()) {
            fieldValue.setDropForm(dropForm);
            em.persist(fieldValue);
        }
    }
    return dropForm;
}


@Override
public RiverDropForm update(RiverDropForm dropForm){
    String query = "DELETE FROM RiverDropFormField f WHERE f.dropForm = :dropForm";
    em.createQuery(query).setParameter("dropForm", dropForm).executeUpdate();
    if (dropForm.getValues() != null) {
        for (RiverDropFormField fieldValue : dropForm.getValues()) {
            fieldValue.setDropForm(dropForm);
            em.persist(fieldValue);
        }
    }
    em.refresh(dropForm);
    return dropForm;
}


}