import cn.offway.athena.domain.PhGoodsCategory;
import cn.offway.athena.repository.PhGoodsCategoryRepository;
import cn.offway.athena.service.PhGoodsCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Service
public class PhGoodsCategoryServiceImpl implements cn.offway.athena.service.PhGoodsCategoryService,PhGoodsCategoryService{

 private  Logger logger;

@Autowired
 private  PhGoodsCategoryRepository phGoodsCategoryRepository;


@Override
public Page<PhGoodsCategory> findByPid(Long pid,Pageable pageable){
    return phGoodsCategoryRepository.findAll(new Specification<PhGoodsCategory>() {

        @Override
        public Predicate toPredicate(Root<PhGoodsCategory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            params.add(criteriaBuilder.equal(root.get("goodsType"), pid));
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, pageable);
}


@Override
public PhGoodsCategory save(PhGoodsCategory phGoodsCategory){
    return phGoodsCategoryRepository.save(phGoodsCategory);
}


@Override
public PhGoodsCategory findOne(Long id){
    return phGoodsCategoryRepository.findOne(id);
}


@Override
public void delByPid(Long pid){
    phGoodsCategoryRepository.deleteByPid(pid);
}


@Override
public void del(Long id){
    phGoodsCategoryRepository.delete(id);
}


@Override
public void resort(Long sort,Long theId){
    phGoodsCategoryRepository.resort(sort, theId);
}


@Override
public List<PhGoodsCategory> findAll(){
    return phGoodsCategoryRepository.findAll();
}


@Override
public Predicate toPredicate(Root<PhGoodsCategory> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    params.add(criteriaBuilder.equal(root.get("goodsType"), pid));
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


}