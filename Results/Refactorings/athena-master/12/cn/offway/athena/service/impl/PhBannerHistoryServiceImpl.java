import cn.offway.athena.domain.PhBannerHistory;
import cn.offway.athena.repository.PhBannerHistoryRepository;
import cn.offway.athena.service.PhBannerHistoryService;
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
public class PhBannerHistoryServiceImpl implements cn.offway.athena.service.PhBannerHistoryService,PhBannerHistoryService{

 private  Logger logger;

@Autowired
 private  PhBannerHistoryRepository phBannerHistoryRepository;


@Override
public List<PhBannerHistory> findList(String id){
    return phBannerHistoryRepository.findAll(new Specification<PhBannerHistory>() {

        @Override
        public Predicate toPredicate(Root<PhBannerHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> params = new ArrayList<Predicate>();
            params.add(cb.equal(root.get("bannerId"), id));
            Predicate[] predicates = new Predicate[params.size()];
            query.where(params.toArray(predicates));
            return null;
        }
    });
}


@Override
public List<PhBannerHistory> save(List<PhBannerHistory> entities){
    return phBannerHistoryRepository.save(entities);
}


@Override
public PhBannerHistory findOne(Long id){
    return phBannerHistoryRepository.findOne(id);
}


@Override
public Predicate toPredicate(Root<PhBannerHistory> root,CriteriaQuery<?> query,CriteriaBuilder cb){
    List<Predicate> params = new ArrayList<Predicate>();
    params.add(cb.equal(root.get("bannerId"), id));
    Predicate[] predicates = new Predicate[params.size()];
    query.where(params.toArray(predicates));
    return null;
}


@Override
public void delete(Long id){
    phBannerHistoryRepository.delete(id);
}


}