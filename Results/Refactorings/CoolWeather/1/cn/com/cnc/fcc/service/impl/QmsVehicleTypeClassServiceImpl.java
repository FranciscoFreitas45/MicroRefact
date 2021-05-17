import cn.com.cnc.fcc.domain.QmsVehicleTypeClass;
import cn.com.cnc.fcc.repository.QmsVehicleTypeClassRepository;
import cn.com.cnc.fcc.service.QmsVehicTypeClassService;
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
public class QmsVehicleTypeClassServiceImpl implements QmsVehicTypeClassService,cn.com.cnc.fcc.service.QmsVehicTypeClassService{

 private  Logger log;

 private  QmsVehicleTypeClassRepository qmsVehicleTypeClassRepository;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;


@Override
public Predicate toPredicate(Root<QmsVehicleTypeClass> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<Predicate>();
    try {
        // 判断模糊查询车型分类编码是否为空
        if (!"".equals(vehicleClass)) {
            predicates.add(criteriaBuilder.like(root.get("vehicleClass"), "%" + vehicleClass + "%"));
        }
        // 判断模糊查询车型分类名称是否为空
        if (!"".equals(vehicleClassName)) {
            predicates.add(criteriaBuilder.like(root.get("vehicleClassName"), "%" + vehicleClassName + "%"));
        }
        // 检索未删除的数据
        predicates.add(criteriaBuilder.equal(root.get("flagStatus"), "0"));
    } catch (Exception e) {
        log.info(e.getMessage());
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
}


@Override
public Page<QmsVehicleTypeClass> qmsVtcFindAll(String vehicleClass,String vehicleClassName,Pageable pageable){
    // 初始化结果集
    Page<QmsVehicleTypeClass> resultInfo = null;
    Specification querySpecifi = new Specification<QmsVehicleTypeClass>() {

        @Override
        public Predicate toPredicate(Root<QmsVehicleTypeClass> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            try {
                // 判断模糊查询车型分类编码是否为空
                if (!"".equals(vehicleClass)) {
                    predicates.add(criteriaBuilder.like(root.get("vehicleClass"), "%" + vehicleClass + "%"));
                }
                // 判断模糊查询车型分类名称是否为空
                if (!"".equals(vehicleClassName)) {
                    predicates.add(criteriaBuilder.like(root.get("vehicleClassName"), "%" + vehicleClassName + "%"));
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
    resultInfo = qmsVehicleTypeClassRepository.findAll(querySpecifi, pageable);
    // 返回结果
    return resultInfo;
}


}