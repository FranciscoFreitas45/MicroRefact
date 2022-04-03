package com.ushahidi.swiftriver.core.api.dao.impl;
 import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.ActivityDao;
import com.ushahidi.swiftriver.core.model.Activity;
@Repository
public class JpaActivityDao extends AbstractJpaDao<Activity>implements ActivityDao{


public List<Activity> find(long accountId,Integer count,Long lastId,Boolean newer,Boolean followers){
    String qlString = "SELECT ac " + "FROM Activity ac " + "JOIN ac.account a ";
    qlString += (followers) ? "JOIN a.followers f JOIN f.follower a2 WHERE a2.id = :accountId " : "WHERE a.id = :accountId ";
    if (newer != null && newer) {
        if (lastId != null) {
            qlString += "AND ac.id > :lastId ";
        }
        qlString += "ORDER BY ac.id ASC ";
    } else {
        if (lastId != null) {
            qlString += "AND ac.id < :lastId ";
        }
        qlString += "ORDER BY ac.id DESC ";
    }
    TypedQuery<Activity> query = em.createQuery(qlString, Activity.class);
    query.setParameter("accountId", accountId);
    if (lastId != null) {
        query.setParameter("lastId", lastId).setMaxResults(count);
    }
    List<Activity> activities = query.getResultList();
    if (activities.size() == 0)
        return null;
    return activities;
}


public void setActionDateAdd(long id,Date actionDateAdd)

}