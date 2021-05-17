import cn.com.cnc.fcc.domain.QmsSupplierClass;
import cn.com.cnc.fcc.repository.QmsSupplierClassRepository;
import cn.com.cnc.fcc.service.QmsSupplierClassService;
import cn.com.cnc.fcc.service.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class QmsSupplierClassServiceImpl implements QmsSupplierClassService,cn.com.cnc.fcc.service.QmsSupplierClassService{

 private  Logger log;

 private  QmsSupplierClassRepository qmsSupplierClassRepository;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;


@Override
public Page<QmsSupplierClass> qmsSupplierClassFindAll(String bianMa,String gongName,Pageable pageable){
    // 初始化结果集
    Page<QmsSupplierClass> resultInfo = null;
    Specification querySpecifi = new Specification<QmsSupplierClass>() {

        @Override
        public Predicate toPredicate(Root<QmsSupplierClass> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            try {
                // 判断模糊查询供应商编码是否为空
                if (!"".equals(bianMa)) {
                    predicates.add(criteriaBuilder.like(root.get("suppkierClass"), "%" + bianMa + "%"));
                }
                // 判断模糊查询供应商名称是否为空
                if (!"".equals(gongName)) {
                    predicates.add(criteriaBuilder.like(root.get("suppkierClassName"), "%" + gongName + "%"));
                }
                // 检索未删除的数据
                predicates.add(criteriaBuilder.equal(root.get("flagStatus"), "0"));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    };
    // 带检索条件查询数据
    resultInfo = qmsSupplierClassRepository.findAll(querySpecifi, pageable);
    // 返回结果
    return resultInfo;
}


@Override
public Predicate toPredicate(Root<QmsSupplierClass> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<Predicate>();
    try {
        // 判断模糊查询供应商编码是否为空
        if (!"".equals(bianMa)) {
            predicates.add(criteriaBuilder.like(root.get("suppkierClass"), "%" + bianMa + "%"));
        }
        // 判断模糊查询供应商名称是否为空
        if (!"".equals(gongName)) {
            predicates.add(criteriaBuilder.like(root.get("suppkierClassName"), "%" + gongName + "%"));
        }
        // 检索未删除的数据
        predicates.add(criteriaBuilder.equal(root.get("flagStatus"), "0"));
    } catch (Exception e) {
        log.info(e.getMessage());
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
}


}