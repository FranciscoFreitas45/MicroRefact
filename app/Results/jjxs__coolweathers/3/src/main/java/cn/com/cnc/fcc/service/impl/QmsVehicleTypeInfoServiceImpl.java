package cn.com.cnc.fcc.service.impl;
 import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.domain.QmsVehicleTypeInfo;
import cn.com.cnc.fcc.repository.QmsVehicleTypeInfoRepository;
import cn.com.cnc.fcc.service.QmsVehicleTypeInfoService;
import cn.com.cnc.fcc.service.dto.VehicleTypeInfoDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
@Service
public class QmsVehicleTypeInfoServiceImpl implements QmsVehicleTypeInfoService{

 private  Logger log;

 private  QmsVehicleTypeInfoRepository qmsVehicleTypeInfoRepository;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;

 private  EntityManagerFactory emf;

public QmsVehicleTypeInfoServiceImpl(QmsVehicleTypeInfoRepository qmsVehicleTypeInfoRepository, EntityManagerFactory emf) {
    this.qmsVehicleTypeInfoRepository = qmsVehicleTypeInfoRepository;
    this.emf = emf;
}
@Override
@Transactional
public Integer updateVehicleTypeFlag(QmsVehicleTypeInfo qmsVehicleTypeInfo){
    // 删除返回结果
    Integer resultNumber = 0;
    Integer deleteNumber = 0;
    // 时间
    ZonedDateTime time = dateUtil.getDBNowDate();
    // 初始化参数
    List<QmsVehicleTypeInfo> qmsVehicleTypeInfoLis = new ArrayList<QmsVehicleTypeInfo>();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    try {
        // 判断该数据是否有更新
        qmsVehicleTypeInfoLis = qmsVehicleTypeInfoRepository.findByIdAndFlagStatus(qmsVehicleTypeInfo.getId(), "0");
        // 判断数据是否被更新
        if (qmsVehicleTypeInfoLis.size() == 0) {
            // 数据被更新不能删除
            resultNumber = 1;
        } else {
            // 判断数据是否被修改
            if (dateUtil.compareByDataFormatterTo(qmsVehicleTypeInfo.getModifyTime(), qmsVehicleTypeInfoLis.get(0).getModifyTime(), dataFormat)) {
                // 删除数据
                deleteNumber = qmsVehicleTypeInfoRepository.updateIdModifyTime(qmsVehicleTypeInfo.getVehicleType(), user.getUsername().toString(), time);
                // 判断是否删除成功
                if (deleteNumber != 0) {
                    // 返回成功
                    resultNumber = 0;
                }
            }
        }
    } catch (Exception e) {
        // 打印日志
        log.info(e.getMessage());
    }
    // 返回结果
    return resultNumber;
}


public String getVehicleTypePopInfoSql(String vehicleType,String vehicleTypeName){
    StringBuffer sql = new StringBuffer();
    sql.append(" select vti.id, vti.vehicle_type AS vehicleType, vti.vehicle_type_name AS vehicleTypeName,  ");
    sql.append(" vtc.vehicle_class_name AS vehicleClassName, vti.remark  ");
    sql.append(" from qms_vehicle_type_info vti  ");
    sql.append(" LEFT JOIN qms_vehicle_type_class vtc  ");
    sql.append(" ON vti.vehicle_class_id = vtc.id  ");
    sql.append(" WHERE 1=1  ");
    if (!"".equals(vehicleType) && vehicleType != null)
        sql.append(" AND vti.vehicle_type LIKE '%" + vehicleType + "%' ");
    if (!"".equals(vehicleTypeName) && vehicleTypeName != null)
        sql.append("AND vti.vehicle_type_name LIKE '%" + vehicleTypeName + "%' ");
    return sql.toString();
}


@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
@Override
public Page<QmsVehicleTypeInfo> qmsVehicleTypeFindAll(String carType,String carTypeName,Pageable pageable){
    // 初始化结果集
    Page<QmsVehicleTypeInfo> resultInfo = null;
    Specification querySpecifi = new Specification<QmsVehicleTypeInfo>() {

        @Override
        public Predicate toPredicate(Root<QmsVehicleTypeInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            try {
                // 判断模糊查询车型是否为空
                if (!"".equals(carType)) {
                    predicates.add(criteriaBuilder.like(root.get("vehicleType"), "%" + carType + "%"));
                }
                // 判断模糊查询车型名称是否为空
                if (!"".equals(carTypeName)) {
                    predicates.add(criteriaBuilder.like(root.get("vehicleTypeName"), "%" + carTypeName + "%"));
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
    resultInfo = this.qmsVehicleTypeInfoRepository.findAll(querySpecifi, pageable);
    // 返回结果
    return resultInfo;
}


@SuppressWarnings({ "unchecked", "deprecation" })
public List<VehicleTypeInfoDTO> getVehicleTypePopInfo(String vehicleType,String vehicleTypeName){
    List<VehicleTypeInfoDTO> qmsMaterielSupplierDtos = new ArrayList<VehicleTypeInfoDTO>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getVehicleTypePopInfoSql(vehicleType, vehicleTypeName);
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(VehicleTypeInfoDTO.class);
        qmsMaterielSupplierDtos = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("vehicleType", StandardBasicTypes.STRING).addScalar("vehicleTypeName", StandardBasicTypes.STRING).addScalar("vehicleClassName", StandardBasicTypes.STRING).addScalar("remark", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        System.out.println(e);
        throw new InternalServerErrorException("QmsVehicleTypeInfo could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return qmsMaterielSupplierDtos;
}


@Override
public Predicate toPredicate(Root<QmsVehicleTypeInfo> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<>();
    try {
        // 判断模糊查询车型是否为空
        if (!"".equals(carType)) {
            predicates.add(criteriaBuilder.like(root.get("vehicleType"), "%" + carType + "%"));
        }
        // 判断模糊查询车型名称是否为空
        if (!"".equals(carTypeName)) {
            predicates.add(criteriaBuilder.like(root.get("vehicleTypeName"), "%" + carTypeName + "%"));
        }
        // 检索未删除的数据
        predicates.add(criteriaBuilder.equal(root.get("flagStatus"), "0"));
    } catch (Exception e) {
        log.info(e.getMessage());
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
}


}