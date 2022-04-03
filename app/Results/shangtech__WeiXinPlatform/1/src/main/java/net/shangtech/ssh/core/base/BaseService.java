package net.shangtech.ssh.core.base;
 import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import net.shangtech.DTO.BaseDao;
import net.shangtech.DTO.BaseDao;
import net.shangtech.DTO.BaseDao;
import net.shangtech.DTO.BaseDao;
import net.shangtech.DTO.BaseDao;
import net.shangtech.DTO.BaseDao;
import net.shangtech.DTO.BaseDao;
import net.shangtech.DTO.BaseDao;
import net.shangtech.DTO.BaseDao;
public class BaseService {


public void add(T entity){
    try {
        dao().insert(entity);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    // throw new RuntimeException("保存对象" + entity + "时出错");
    }
}


public Class<?> getEntityClass(){
    Class<?> entityClass = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    return entityClass;
}


public BaseDao<T> dao()


public Page<T> find(Page<T> page,String orderBy){
    try {
        page.setTotalCount(dao().countAll());
        page.setResult(dao().findAll(page.getStart(), page.getLimit(), orderBy));
        return page;
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}


public int count(String hql,Object values){
    try {
        return dao().count(hql, values);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
}


public void update(T entity){
    try {
        dao().update(entity);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
}


public T findUnique(String hql,Object values){
    return dao().findUnique(hql, values);
}


public Object gather(String queryString,Object values){
    return dao().gather(queryString, values);
}


public void delete(int id){
    try {
        dao().delete(id);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    // throw new RuntimeException("删除[ID="+id+"]的类型" + getEntityClass().getName() + "时出错");
    }
}


public List<Object> executeSql(String sql,Object values){
    return dao().executeSql(sql, values);
}


}