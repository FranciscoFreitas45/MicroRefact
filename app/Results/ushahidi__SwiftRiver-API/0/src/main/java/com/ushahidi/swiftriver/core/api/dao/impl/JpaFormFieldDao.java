package com.ushahidi.swiftriver.core.api.dao.impl;
 import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.FormFieldDao;
import com.ushahidi.swiftriver.core.model.FormField;
@Repository
public class JpaFormFieldDao extends AbstractJpaDao<FormField>implements FormFieldDao{


}