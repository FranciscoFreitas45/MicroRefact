package com.vino.scaffold.service.base;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.vino.scaffold.entity.Constants;
import com.vino.scaffold.entity.base.BaseEntity;
import com.vino.scaffold.repository.base.BaseRepository;
import com.vino.scaffold.shiro.entity.User;
import com.vino.scaffold.shiro.exception.UserDuplicateException;
@Transactional
public class AbstractBaseServiceImpl {

@Autowired
 protected  BaseRepository<T,PK> baseRepository;

@Autowired
 private  HttpSession session;


public User getCurrentUser(){
    return (User) session.getAttribute(Constants.CURRENT_USER);
}


public void setBaseRepository(BaseRepository<T,PK> baseRepository){
    this.baseRepository = baseRepository;
}


public T findOne(PK id){
    return baseRepository.findOne(id);
}


public void save(List<T> objs){
    baseRepository.save(objs);
}


public void deleteAll(){
    baseRepository.deleteAll();
}


public void update(T obj){
    baseRepository.save(obj);
}


public Page<T> findAll(Pageable pageable){
    return baseRepository.findAll(pageable);
}


@SuppressWarnings("unchecked")
public void delete(PK ids){
    for (PK id : ids) baseRepository.delete(id);
}


public BaseRepository<T,PK> getBaseRepository(){
    return baseRepository;
}


public List<T> find(PK ids){
    List<PK> idList = new ArrayList<PK>();
    for (PK id : ids) {
        idList.add(id);
    }
    return baseRepository.findAll(idList);
}


public void setSession(HttpSession session){
    this.session = session;
}


public HttpSession getSession(){
    return session;
}


public long getCount(){
    return baseRepository.count();
}


}