import cn.offway.athena.domain.PhUserInfo;
import cn.offway.athena.repository.PhUserInfoRepository;
import cn.offway.athena.service.PhUserInfoService;
import org.apache.commons.lang3.StringUtils;
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
public class PhUserInfoServiceImpl implements cn.offway.athena.service.PhUserInfoService,PhUserInfoService{

 private  Logger logger;

@Autowired
 private  PhUserInfoRepository phUserInfoRepository;


@Override
public PhUserInfo findByUnionid(String unionid){
    return phUserInfoRepository.findByUnionid(unionid);
}


@Override
public PhUserInfo save(PhUserInfo phUserInfo){
    return phUserInfoRepository.save(phUserInfo);
}


@Override
public PhUserInfo findOne(Long id){
    return phUserInfoRepository.findOne(id);
}


@Override
public Page<PhUserInfo> findByPage(String nickname,String unionid,String phone,String id,String real_name,String isAuth,Pageable page){
    return phUserInfoRepository.findAll(new Specification<PhUserInfo>() {

        @Override
        public Predicate toPredicate(Root<PhUserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(nickname)) {
                params.add(criteriaBuilder.like(root.get("nickname"), "%" + nickname + "%"));
            }
            if (StringUtils.isNotBlank(real_name)) {
                params.add(criteriaBuilder.like(root.get("realName"), "%" + real_name + "%"));
            }
            if (StringUtils.isNotBlank(phone)) {
                params.add(criteriaBuilder.equal(root.get("phone"), phone));
            }
            if (StringUtils.isNotBlank(unionid)) {
                params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
            }
            if (StringUtils.isNotBlank(id)) {
                params.add(criteriaBuilder.equal(root.get("id"), id));
            }
            if (StringUtils.isNotBlank(isAuth)) {
                params.add(criteriaBuilder.equal(root.get("isAuth"), isAuth));
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, page);
}


@Override
public Predicate toPredicate(Root<PhUserInfo> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(nickname)) {
        params.add(criteriaBuilder.like(root.get("nickname"), "%" + nickname + "%"));
    }
    if (StringUtils.isNotBlank(real_name)) {
        params.add(criteriaBuilder.like(root.get("realName"), "%" + real_name + "%"));
    }
    if (StringUtils.isNotBlank(phone)) {
        params.add(criteriaBuilder.equal(root.get("phone"), phone));
    }
    if (StringUtils.isNotBlank(unionid)) {
        params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
    }
    if (StringUtils.isNotBlank(id)) {
        params.add(criteriaBuilder.equal(root.get("id"), id));
    }
    if (StringUtils.isNotBlank(isAuth)) {
        params.add(criteriaBuilder.equal(root.get("isAuth"), isAuth));
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


}