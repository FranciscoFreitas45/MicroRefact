package com.gbcom.system.daoservice;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbcom.system.domain.SysAreaNes;
import com.gbcom.system.utils.CollectionUtil;
import com.hc.core.orm.hibernate.EntityService;
@Service
public class SysAreaNesService extends EntityService<SysAreaNes, Long>{


public SysAreaNes getSysAreaNes(String gwId){
    String hql = "from SysAreaNes a where a.neID = ?";
    SysAreaNes sysAreaNes = findUnique(hql, new Object[] { gwId });
    return sysAreaNes;
}


public void deleteInvalid(){
    String hql = "from SysAreaNes a  where a.area  = null";
    List<SysAreaNes> list = find(hql);
    for (SysAreaNes each : list) {
        delete(each);
    }
}


public void deleteSysAreaNesByNeIds(String[] gwId){
    String neIds = CollectionUtil.listToString(Arrays.asList(gwId));
    String hql = "DELETE from SysAreaNes a WHERE a.neID IN( " + neIds + " )";
    Query query = getSession().createQuery(hql);
    query.executeUpdate();
}


public List<String> getAllHots(){
    List<String> neIdList = new ArrayList<String>();
    String hql = "from SysAreaNes";
    List<SysAreaNes> list = find(hql);
    for (SysAreaNes ne : list) {
        neIdList.add(ne.getNeID());
    }
    return neIdList;
}


@Autowired
public void setSessionFactory(SessionFactory sessionFactory){
    initDao(sessionFactory, SysAreaNes.class);
}


}