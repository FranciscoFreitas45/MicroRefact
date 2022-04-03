package org.live.common.base;
 import java.io.Serializable;
import java.util.List;
public class BaseServiceImpl implements BaseService<T, ID>{


public BaseRepository<T,ID> getRepository()


@Override
public T get(ID id){
    return this.findOne(id);
}


public T findOne(ID id){
    if (id == null)
        return null;
    return this.getRepository().findOne(id);
}


@Override
public List<T> save(Iterable<T> entities){
    return this.getRepository().save(entities);
}


@Override
public List<T> findAll(){
    return this.getRepository().findAll();
}


@Override
public void delete(Iterable<T> entities){
    this.getRepository().delete(entities);
}


}