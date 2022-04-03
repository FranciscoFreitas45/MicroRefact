package org.danyuan.application.common.base;
 import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.NoRepositoryBean;
import org.danyuan.application.DTO.Pagination;
@NoRepositoryBean
public class BaseServiceImpl implements BaseService<T>{

@Autowired
 private BaseDao<T> baseDao;


@Override
public void saveAll(List<T> entities){
    baseDao.saveAll(entities);
}


@Override
public void trunc(){
    baseDao.deleteAllInBatch();
}


@Override
public List<T> findAllById(List<String> uuidlist){
    if (uuidlist == null) {
        return baseDao.findAll();
    } else {
        List<T> list = baseDao.findAllById(uuidlist);
        return list;
    }
}


@Override
public T findById(String id){
    if (id == null || "".equals(id)) {
        return null;
    }
    Optional<T> optional = baseDao.findById(id);
    if (optional.isPresent()) {
        return optional.get();
    }
    return null;
}


@Override
public T findOne(T entity){
    if (entity == null) {
        return null;
    }
    Example<T> example = Example.of(entity);
    Optional<T> optional = baseDao.findOne(example);
    if (optional.isPresent()) {
        return optional.get();
    }
    return null;
}


@Override
public T save(T entity){
    if (entity == null) {
        return null;
    }
    Field[] fields = entity.getClass().getSuperclass().getDeclaredFields();
    boolean fluxFlag = true;
    boolean fluxDeleteFlag = true;
    for (Field field : fields) {
        field.setAccessible(true);
        // # 要求实体类必须要有uuid 或者需要继承 @MappedSuperclass<BaseEntity>
        if ("uuid".equals(field.getName())) {
            try {
                if (field.get(entity) == null || "".equals(field.get(entity).toString())) {
                    field.set(entity, UUID.randomUUID().toString());
                }
                fluxFlag = false;
                // 成功赋值id 中断循环
                break;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if ("deleteFlag".equals(field.getName())) {
            try {
                if (field.get(entity) == null) {
                    field.set(entity, 0);
                }
                fluxDeleteFlag = false;
                // 成功赋值id 中断循环
                break;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    // 如果超类中没有 这访问entity
    if (fluxFlag) {
        fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            // # 要求实体类必须要有uuid 或者需要继承 @MappedSuperclass<BaseEntity>
            if ("uuid".equals(field.getName())) {
                try {
                    if (field.get(entity) == null || "".equals(field.get(entity).toString())) {
                        field.set(entity, UUID.randomUUID().toString());
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    if (fluxDeleteFlag) {
        fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            // # 要求实体类必须要有deleteFlag 或者需要继承 @MappedSuperclass<BaseEntity>
            if ("deleteFlag".equals(field.getName())) {
                try {
                    if (field.get(entity) == null) {
                        field.set(entity, 0);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    return baseDao.save(entity);
}


@Override
public void deleteAll(List<T> entities){
    baseDao.deleteAll(entities);
}


@Override
public Long count(T info){
    if (info == null) {
        return baseDao.count();
    } else {
        Example<T> example = Example.of(info);
        return baseDao.count(example);
    }
}


@Override
public void deleteById(String uuid){
    if (uuid == null) {
    } else {
        baseDao.deleteById(uuid);
    }
}


@Override
public Page<T> page(Pagination<T> vo){
    if (vo.getInfo() == null) {
        if (vo.getSortName() != null) {
            Order order;
            if (vo.getSortOrder().equals("desc")) {
                order = Order.desc(vo.getSortName());
            } else {
                order = Order.asc(vo.getSortName());
            }
            Sort sort = Sort.by(order);
            PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
            return baseDao.findAll(request);
        } else {
            PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize());
            return baseDao.findAll(request);
        }
    } else {
        Example<T> example = Example.of(vo.getInfo());
        if (vo.getSortName() != null) {
            Order order;
            if (vo.getSortOrder().equals("desc")) {
                order = Order.desc(vo.getSortName());
            } else {
                order = Order.asc(vo.getSortName());
            }
            Sort sort = Sort.by(order);
            PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
            return baseDao.findAll(example, request);
        } else {
            PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize());
            return baseDao.findAll(example, request);
        }
    }
}


@Override
public List<T> findAll(Pagination<T> vo){
    if (vo.getInfo() == null) {
        if (vo.getSortName() != null) {
            Order order;
            if (vo.getSortOrder().equals("desc")) {
                order = Order.desc(vo.getSortName());
            } else {
                order = Order.asc(vo.getSortName());
            }
            Sort sort = Sort.by(order);
            return baseDao.findAll(sort);
        } else {
            return baseDao.findAll();
        }
    } else {
        Example<T> example = Example.of(vo.getInfo());
        if (vo.getSortName() != null) {
            Order order;
            if (vo.getSortOrder().equals("desc")) {
                order = Order.desc(vo.getSortName());
            } else {
                order = Order.asc(vo.getSortName());
            }
            Sort sort = Sort.by(order);
            return baseDao.findAll(example, sort);
        } else {
            return baseDao.findAll(example);
        }
    }
}


@Override
public void delete(T entity){
    baseDao.delete(entity);
}


}