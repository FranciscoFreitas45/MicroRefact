import cn.offway.athena.domain.PhConfig;
import cn.offway.athena.repository.PhConfigRepository;
import cn.offway.athena.service.PhConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class PhConfigServiceImpl implements PhConfigService,cn.offway.athena.service.PhConfigService{

 private  Logger logger;

@Autowired
 private  PhConfigRepository phConfigRepository;


@Override
public PhConfig save(PhConfig phConfig){
    return phConfigRepository.save(phConfig);
}


@Override
public PhConfig findOne(String name){
    return phConfigRepository.findOne(new Specification<PhConfig>() {

        @Override
        public Predicate toPredicate(Root<PhConfig> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            params.add(criteriaBuilder.equal(root.get("name"), name));
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    });
}


@Override
public Predicate toPredicate(Root<PhConfig> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    params.add(criteriaBuilder.equal(root.get("name"), name));
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


@Override
public String findContentByName(String name){
    return phConfigRepository.findContentByName(name);
}


}