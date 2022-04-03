package com.ipe.common.service;
 import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ipe.common.dao.CustomerRepository;
import com.ipe.common.entity.IDEntity;
import com.ipe.module.core.web.util.RestRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class BaseService {


public void saveAndFlush(M m){
    getRepository().saveAndFlush(m);
}


public void flush(){
    getRepository().flush();
}


public CustomerRepository<M,PK> getRepository()


public M get(PK pk){
    return getRepository().findOne(pk);
}


public M save(M m){
    return (M) getRepository().save(m);
}


public void deleteById(PK pk){
    getRepository().delete(pk);
}


public long count(Specification<M> tSpecification){
    return getRepository().count(tSpecification);
}


public M findOne(Specification<M> tSpecification){
    return getRepository().findOne(tSpecification);
}


public Page<M> findEntity(RestRequest rest){
    PageRequest pageable = new PageRequest(rest.getStart(), rest.getLimit(), rest.getSorts());
    return getRepository().findAll(new Specification<M>() {

        @Override
        public Predicate toPredicate(Root<M> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
            if (StringUtils.isNotBlank(rest.getQueryParams())) {
                JSONObject jsonObject = JSON.parseObject(rest.getQueryParams());
                List<Predicate> list = new ArrayList<Predicate>();
                for (Map.Entry<String, Object> key : jsonObject.entrySet()) {
                    if (key.getValue() != null && !"".equals(key.getValue().toString()))
                        list.add(cb.like(root.get(key.getKey()).as(String.class), key.getValue().toString()));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
            return null;
        }
    }, pageable);
}


public List<M> findAll(Specification<M> tSpecification,Sort orders){
    return getRepository().findAll(tSpecification, orders);
}


public void delete(PK[] pk){
    if (pk != null) {
        for (PK p : pk) {
            getRepository().delete(p);
        }
    }
}


@Override
public Predicate toPredicate(Root<M> root,CriteriaQuery<?> cq,CriteriaBuilder cb){
    if (StringUtils.isNotBlank(rest.getQueryParams())) {
        JSONObject jsonObject = JSON.parseObject(rest.getQueryParams());
        List<Predicate> list = new ArrayList<Predicate>();
        for (Map.Entry<String, Object> key : jsonObject.entrySet()) {
            if (key.getValue() != null && !"".equals(key.getValue().toString()))
                list.add(cb.like(root.get(key.getKey()).as(String.class), key.getValue().toString()));
        }
        return cb.and(list.toArray(new Predicate[list.size()]));
    }
    return null;
}


}