import cn.offway.athena.domain.PhBanner;
import cn.offway.athena.repository.PhBannerRepository;
import cn.offway.athena.service.PhBannerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PhBannerServiceImpl implements cn.offway.athena.service.PhBannerService,PhBannerService{

@Autowired
 private  PhBannerRepository bannerRepository;


@Override
public Long getMaxSort(){
    Optional<String> res = bannerRepository.getMaxSort();
    if (res.isPresent()) {
        return Long.valueOf(String.valueOf(res.get()));
    } else {
        return 0L;
    }
}


@Override
public PhBanner save(PhBanner banner){
    return bannerRepository.save(banner);
}


@Override
public PhBanner findOne(Long id){
    return bannerRepository.findOne(id);
}


@Override
public void resort(Long sort){
    bannerRepository.resort(sort);
}


@Override
public Page<PhBanner> findAll(String id,String remark,Pageable pageable){
    return bannerRepository.findAll(new Specification<PhBanner>() {

        @Override
        public Predicate toPredicate(Root<PhBanner> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(id)) {
                params.add(criteriaBuilder.equal(root.get("id"), id));
            }
            if (StringUtils.isNotBlank(remark)) {
                params.add(criteriaBuilder.like(root.get("remark"), "%" + remark + "%"));
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, pageable);
}


@Override
public void delete(Long id){
    bannerRepository.delete(id);
}


@Override
public Predicate toPredicate(Root<PhBanner> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(id)) {
        params.add(criteriaBuilder.equal(root.get("id"), id));
    }
    if (StringUtils.isNotBlank(remark)) {
        params.add(criteriaBuilder.like(root.get("remark"), "%" + remark + "%"));
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


}