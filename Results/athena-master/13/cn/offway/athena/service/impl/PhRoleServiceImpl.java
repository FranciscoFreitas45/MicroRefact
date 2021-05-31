import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;
import cn.offway.athena.domain.PhResource;
import cn.offway.athena.domain.PhRole;
import cn.offway.athena.domain.PhRoleresource;
import cn.offway.athena.repository.PhResourceRepository;
import cn.offway.athena.repository.PhRoleRepository;
import cn.offway.athena.repository.PhRoleadminRepository;
import cn.offway.athena.repository.PhRoleresourceRepository;
import cn.offway.athena.service.PhRoleService;
@Service
public class PhRoleServiceImpl implements cn.offway.athena.service.PhRoleService,PhRoleService{

 private  Logger logger;

@Autowired
 private  PhRoleRepository phRoleRepository;

@Autowired
 private  PhRoleresourceRepository phRoleresourceRepository;

@Autowired
 private  PhRoleadminRepository phRoleadminRepository;

@Autowired
 private  PhResourceRepository phResourceRepository;


public void getResources(Date now,Long phRoleId,List<PhRoleresource> roleresources,PhResource phResource){
    if (null != phResource.getParentId()) {
        PhResource phResource1 = phResourceRepository.findOne(phResource.getParentId());
        getResources(now, phRoleId, roleresources, phResource1);
    }
    PhRoleresource phRoleresource = new PhRoleresource();
    phRoleresource.setCreatedtime(now);
    phRoleresource.setResourceId(phResource.getId());
    phRoleresource.setRoleId(phRoleId);
    if (!roleresources.contains(phRoleresource)) {
        roleresources.add(phRoleresource);
    }
}


@SuppressWarnings("unchecked")
@Override
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
public void save(PhRole phRole,Long[] resourceIds){
    Date now = new Date();
    phRole.setCreatedtime(now);
    phRole = save(phRole);
    Long phRoleId = phRole.getId();
    if (null != resourceIds) {
        List<Long> idList = (List<Long>) ListUtils.toList(resourceIds);
        List<PhResource> phResources = phResourceRepository.findByIds(idList);
        List<PhRoleresource> roleresources = new ArrayList<>();
        for (PhResource phResource : phResources) {
            getResources(now, phRoleId, roleresources, phResource);
        }
        phRoleresourceRepository.deleteByRoleId(phRoleId);
        phRoleresourceRepository.save(roleresources);
    }
}


@Override
public PhRole findOne(Long id){
    return phRoleRepository.findOne(id);
}


@Override
public Page<PhRole> findByPage(String name,Pageable page){
    return phRoleRepository.findAll(new Specification<PhRole>() {

        @Override
        public Predicate toPredicate(Root<PhRole> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(name)) {
                params.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, page);
}


@Override
public List<PhRole> findAll(){
    return phRoleRepository.findAll();
}


@Override
public Predicate toPredicate(Root<PhRole> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(name)) {
        params.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


@SuppressWarnings("unchecked")
@Override
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
public void deleteRole(String ids){
    List<Long> idList = (List<Long>) ListUtils.toList(ids.split(","));
    phRoleadminRepository.deleteByRoleIds(idList);
    phRoleresourceRepository.deleteByRoleIds(idList);
    phRoleRepository.deleteByRoleIds(idList);
}


}