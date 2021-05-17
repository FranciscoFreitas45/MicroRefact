import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;
import cn.offway.athena.domain.PhAdmin;
import cn.offway.athena.domain.PhBrandadmin;
import cn.offway.athena.domain.PhRoleadmin;
import cn.offway.athena.repository.PhAdminRepository;
import cn.offway.athena.repository.PhBrandadminRepository;
import cn.offway.athena.repository.PhRoleadminRepository;
import cn.offway.athena.service.PhAdminService;
@Service
public class PhAdminServiceImpl implements PhAdminService,cn.offway.athena.service.PhAdminService{

 private  Logger logger;

@Autowired
 private  PhAdminRepository phAdminRepository;

@Autowired
 private  PhRoleadminRepository phRoleadminRepository;

@Autowired
 private  PhBrandadminRepository phBrandadminRepository;

@Autowired
 private  PasswordEncoder passwordEncoder;

@Value("${ph.default.pwd}")
 private  String DEFAULT_PWD;


@SuppressWarnings("unchecked")
@Override
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
public void deleteAdmin(String ids){
    List<Long> idList = (List<Long>) ListUtils.toList(ids.split(","));
    phRoleadminRepository.deleteByAdminIds(idList);
    phBrandadminRepository.deleteByAdminIds(idList);
    phAdminRepository.deleteByIds(idList);
}


@Override
public void resetPwd(Long id){
    PhAdmin phAdmin = findOne(id);
    phAdmin.setPassword(passwordEncoder.encode(DEFAULT_PWD));
    save(phAdmin);
}


@Override
public boolean editPwd(Long id,String password,String newpassword){
    PhAdmin phAdmin = findOne(id);
    if (passwordEncoder.matches(password, phAdmin.getPassword())) {
        phAdmin.setPassword(passwordEncoder.encode(newpassword));
        save(phAdmin);
        return true;
    } else {
        return false;
    }
}


@Override
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
public void save(PhAdmin phAdmin,Long[] roleIds,String[] brandIds){
    Date now = new Date();
    Long adminId = phAdmin.getId();
    if (null != phAdmin.getId()) {
        PhAdmin phAdmin1 = findOne(adminId);
        phAdmin.setPassword(phAdmin1.getPassword());
        phAdmin.setCreatedtime(phAdmin1.getCreatedtime());
    } else {
        phAdmin.setCreatedtime(now);
        phAdmin.setPassword(passwordEncoder.encode(DEFAULT_PWD));
    }
    phAdmin = save(phAdmin);
    adminId = phAdmin.getId();
    phRoleadminRepository.deleteByAdminId(adminId);
    if (null != roleIds) {
        List<PhRoleadmin> phRoleadmins = new ArrayList<>();
        for (Long roleId : roleIds) {
            PhRoleadmin phRoleadmin = new PhRoleadmin();
            phRoleadmin.setAdminId(adminId);
            phRoleadmin.setCreatedtime(now);
            phRoleadmin.setRoleId(roleId);
            phRoleadmins.add(phRoleadmin);
        }
        phRoleadminRepository.save(phRoleadmins);
    }
    phBrandadminRepository.deleteByAdminId(adminId);
    if (null != brandIds) {
        List<PhBrandadmin> phBrandadmins = new ArrayList<>();
        for (String brandId : brandIds) {
            PhBrandadmin phBrandadmin = new PhBrandadmin();
            phBrandadmin.setAdminId(adminId);
            phBrandadmin.setCreatedtime(now);
            phBrandadmin.setBrandId(Long.parseLong(brandId));
            phBrandadmins.add(phBrandadmin);
        }
        phBrandadminRepository.save(phBrandadmins);
    }
}


@Override
public PhAdmin findOne(Long id){
    return phAdminRepository.findOne(id);
}


@Override
public Page<PhAdmin> findByPage(String username,String nickname,Pageable page){
    return phAdminRepository.findAll(new Specification<PhAdmin>() {

        @Override
        public Predicate toPredicate(Root<PhAdmin> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(username)) {
                params.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
            }
            if (StringUtils.isNotBlank(nickname)) {
                params.add(criteriaBuilder.like(root.get("nickname"), "%" + nickname + "%"));
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, page);
}


@Override
public Predicate toPredicate(Root<PhAdmin> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(username)) {
        params.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
    }
    if (StringUtils.isNotBlank(nickname)) {
        params.add(criteriaBuilder.like(root.get("nickname"), "%" + nickname + "%"));
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


}