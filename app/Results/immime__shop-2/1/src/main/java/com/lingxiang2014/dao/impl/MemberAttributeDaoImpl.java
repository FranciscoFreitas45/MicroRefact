package com.lingxiang2014.dao.impl;
 import java.util.List;
import javax.persistence.FlushModeType;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.dao.MemberAttributeDao;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.MemberAttribute;
import com.lingxiang2014.entity.MemberAttribute.Type;
@Repository("memberAttributeDaoImpl")
public class MemberAttributeDaoImpl extends BaseDaoImpl<MemberAttribute, Long>implements MemberAttributeDao{


public List<MemberAttribute> findList(){
    String jpql = "select memberAttribute from MemberAttribute memberAttribute where memberAttribute.isEnabled = true order by memberAttribute.order asc";
    return entityManager.createQuery(jpql, MemberAttribute.class).setFlushMode(FlushModeType.COMMIT).getResultList();
}


public Integer findUnusedPropertyIndex(){
    for (int i = 0; i < Member.ATTRIBUTE_VALUE_PROPERTY_COUNT; i++) {
        String jpql = "select count(*) from MemberAttribute memberAttribute where memberAttribute.propertyIndex = :propertyIndex";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("propertyIndex", i).getSingleResult();
        if (count == 0) {
            return i;
        }
    }
    return null;
}


@Override
public void remove(MemberAttribute memberAttribute){
    if (memberAttribute != null && (memberAttribute.getType() == Type.text || memberAttribute.getType() == Type.select || memberAttribute.getType() == Type.checkbox)) {
        String propertyName = Member.ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + memberAttribute.getPropertyIndex();
        String jpql = "update Member members set members." + propertyName + " = null";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
        super.remove(memberAttribute);
    }
}


}