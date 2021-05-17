import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import cn.com.cnc.fcc.domain.QmsUnhealthy;
import cn.com.cnc.fcc.repository.QmsUnhealthyRepository;
import cn.com.cnc.fcc.service.QmsUnhealthyService;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
@Service
public class QmsUnhealthyServiceImpl implements cn.com.cnc.fcc.service.QmsUnhealthyService,QmsUnhealthyService{

 private  Logger log;

@Resource
 private  DateUtil dateUtil;

 private  EntityManagerFactory emf;

 private  QmsUnhealthyRepository qmsUnhealthyRepository;


public String getSelectParentNodeInfo(String unhealthyCd,String unhealthyName){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT t01.id as \"id\" , ");
    objSqlContent.append(" t01.parent_cd as \"parentCd\" , ");
    objSqlContent.append(" t01.unhealthy_cd as \"organizationCd\" , ");
    objSqlContent.append(" t01.unhealthy_name as \"organizationName\"  ");
    objSqlContent.append(" FROM qms_unhealthy t01 ");
    objSqlContent.append(" where 1=1 ");
    // 判断组织编码和组织名称是否都为空
    if ("".equals(unhealthyCd) && "".equals(unhealthyName)) {
        objSqlContent.append(" and t01.parent_cd = '0' or t01.parent_cd = '' or t01.parent_cd is null ");
    }
    // 判断组织编码是否为空
    if (!"".equals(unhealthyCd)) {
        objSqlContent.append(" and t01.unhealthy_cd  like :unhealthyCd ");
    }
    // 判断组织名称是否为空
    if (!"".equals(unhealthyName)) {
        objSqlContent.append(" and t01.unhealthy_name like :unhealthyName ");
    }
    // 判断组织编码和组织名称是否都为空
    if (!"".equals(unhealthyCd) || !"".equals(unhealthyName)) {
        objSqlContent.append("and NOT FIND_IN_SET(t01.parent_cd,(select  ");
        objSqlContent.append(" GROUP_CONCAT(t02.unhealthy_cd)  ");
        objSqlContent.append(" FROM qms_unhealthy t02  ");
        objSqlContent.append(" WHERE 1=1  ");
        // 判断组织编码是否为空
        if (!"".equals(unhealthyCd)) {
            objSqlContent.append(" and t02.unhealthy_cd  like :unhealthyCd ");
        }
        // 判断组织名称是否为空
        if (!"".equals(unhealthyName)) {
            objSqlContent.append(" and t02.unhealthy_name like :unhealthyName ");
        }
        objSqlContent.append("  ))  ");
    }
    // 返回值
    return objSqlContent.toString();
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
@Transactional
public Integer deleteNodeInfos(String id){
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    Integer resultNumber = 0;
    List<QmsUnhealthy> organizationInfo = new ArrayList<QmsUnhealthy>();
    List<QmsUnhealthy> organizationInfoCheck = new ArrayList<QmsUnhealthy>();
    try {
        // 查询
        Query query = em.createNativeQuery(deleteParentNodeInfo(id));
        // 查询
        Query query1 = em.createNativeQuery(deleteParentNodeInfoCheck(id));
        ResultTransformer transformer = Transformers.aliasToBean(QmsUnhealthy.class);
        // 赋值给DTO
        organizationInfoCheck = query1.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("unhealthyCd", StandardBasicTypes.STRING).addScalar("unhealthyName", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        // 赋值给DTO
        organizationInfo = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("unhealthyCd", StandardBasicTypes.STRING).addScalar("unhealthyName", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
        query1 = null;
        if (organizationInfoCheck.size() == 0) {
            // 删除查询到的所有数据
            qmsUnhealthyRepository.deleteAll(organizationInfo);
        } else {
            resultNumber = 2;
        }
    } catch (Exception e) {
        resultNumber = 1;
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    // 返回值
    return resultNumber;
}


public String getSelectAllInfo(){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" WITH RECURSIVE sub1 (id,unhealthy_cd,unhealthy_name,parent_cd) AS ( ");
    objSqlContent.append(" select	t01.id, ");
    objSqlContent.append(" t01.unhealthy_cd, ");
    objSqlContent.append(" t01.unhealthy_name, ");
    objSqlContent.append(" t01.parent_cd ");
    objSqlContent.append(" FROM qms_unhealthy t01 ");
    objSqlContent.append(" WHERE t01.parent_cd = '0' or t01.parent_cd = '' or t01.parent_cd is null ");
    objSqlContent.append(" UNION ALL ");
    objSqlContent.append(" SELECT t02.id, ");
    objSqlContent.append(" t02.unhealthy_cd, ");
    objSqlContent.append(" t02.unhealthy_name, ");
    objSqlContent.append(" t02.parent_cd ");
    objSqlContent.append(" FROM qms_unhealthy t02 ");
    objSqlContent.append(" INNER JOIN sub1 ON t02.parent_cd = sub1.unhealthy_cd ");
    objSqlContent.append(" ) ");
    objSqlContent.append(" SELECT sub1.id as \"id\" , ");
    objSqlContent.append(" sub1.parent_cd as \"parentCd\" , ");
    objSqlContent.append(" sub1.unhealthy_cd as \"organizationCd\" , ");
    objSqlContent.append(" sub1.unhealthy_name as \"organizationName\"  ");
    objSqlContent.append(" FROM sub1 ");
    objSqlContent.append(" where 1=1 ");
    // 返回值
    return objSqlContent.toString();
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<QmsOrganizationInfoDTO> organListInfo(){
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTO = new ArrayList<QmsOrganizationInfoDTO>();
    try {
        // 查询
        Query query = em.createNativeQuery(getSelectAllInfo());
        ResultTransformer transformer = Transformers.aliasToBean(QmsOrganizationInfoDTO.class);
        // 赋值给DTO
        qmsOrganizationInfoDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.BIG_INTEGER).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("organizationCd", StandardBasicTypes.STRING).addScalar("organizationName", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 发音日志
        log.info(e.getMessage());
    } finally {
        // 管理工厂类
        em.close();
    }
    // 返回值
    return qmsOrganizationInfoDTO;
}


public String getCheckIsUserSql(String id){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" WITH RECURSIVE sub1 (id,unhealthy_cd,is_use,parent_cd) AS ( ");
    objSqlContent.append(" select	t01.id, ");
    objSqlContent.append(" t01.unhealthy_cd, ");
    objSqlContent.append(" t01.is_use, ");
    objSqlContent.append(" t01.parent_cd ");
    objSqlContent.append(" FROM qms_unhealthy t01 ");
    objSqlContent.append(" WHERE t01.parent_cd =  '" + id + "'");
    objSqlContent.append(" UNION ALL ");
    objSqlContent.append(" SELECT t02.id, ");
    objSqlContent.append(" t02.unhealthy_cd, ");
    objSqlContent.append(" t02.is_use, ");
    objSqlContent.append(" t02.parent_cd ");
    objSqlContent.append(" FROM qms_unhealthy t02 ");
    objSqlContent.append(" INNER JOIN sub1 ON t02.parent_cd = sub1.unhealthy_cd ");
    objSqlContent.append(" ) ");
    objSqlContent.append(" SELECT sub1.id as \"id\" , ");
    objSqlContent.append(" sub1.parent_cd as \"parentCd\" , ");
    objSqlContent.append(" sub1.unhealthy_cd as \"unhealthyCd\" , ");
    objSqlContent.append(" sub1.is_use as \"isUse\"  ");
    objSqlContent.append(" FROM sub1 ");
    objSqlContent.append(" where 1=1 ");
    objSqlContent.append(" and sub1.is_use='1' ");
    return objSqlContent.toString();
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<QmsOrganizationInfoDTO> getParentNodeListInfo(String unhealthyCd,String unhealthyName){
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTO = new ArrayList<QmsOrganizationInfoDTO>();
    try {
        // 查询
        Query query = em.createNativeQuery(getSelectParentNodeInfo(unhealthyCd, unhealthyName));
        ResultTransformer transformer = Transformers.aliasToBean(QmsOrganizationInfoDTO.class);
        // 判断组织编码是否为空
        if (!"".equals(unhealthyCd)) {
            query.setParameter("unhealthyCd", "%" + unhealthyCd + "%");
        }
        // 判断组织名称是否为空
        if (!"".equals(unhealthyName)) {
            query.setParameter("unhealthyName", "%" + unhealthyName + "%");
        }
        // 赋值给DTO
        qmsOrganizationInfoDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.BIG_INTEGER).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("organizationCd", StandardBasicTypes.STRING).addScalar("organizationName", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 打印日志
        log.info(e.getMessage());
    } finally {
        // 关闭工厂
        em.close();
    }
    // 返回值
    return qmsOrganizationInfoDTO;
}


public String deleteParentNodeInfoCheck(String id){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" WITH RECURSIVE sub1 (id,unhealthy_cd,unhealthy_name,parent_cd) AS ( ");
    objSqlContent.append(" select	t01.id, ");
    objSqlContent.append(" t01.unhealthy_cd, ");
    objSqlContent.append(" t01.unhealthy_name, ");
    objSqlContent.append(" t01.parent_cd ");
    objSqlContent.append(" FROM qms_unhealthy t01 ");
    objSqlContent.append(" WHERE t01.parent_cd =  '" + id + "'");
    objSqlContent.append(" AND t01.is_use = '1' ");
    objSqlContent.append(" UNION ALL ");
    objSqlContent.append(" SELECT t02.id, ");
    objSqlContent.append(" t02.unhealthy_cd, ");
    objSqlContent.append(" t02.unhealthy_name, ");
    objSqlContent.append(" t02.parent_cd ");
    objSqlContent.append(" FROM qms_unhealthy t02 ");
    objSqlContent.append(" INNER JOIN sub1 ON t02.parent_cd = sub1.unhealthy_cd ");
    objSqlContent.append(" ) ");
    objSqlContent.append(" SELECT sub1.id as \"id\" , ");
    objSqlContent.append(" sub1.parent_cd as \"parentCd\" , ");
    objSqlContent.append(" sub1.unhealthy_cd as \"unhealthyCd\" , ");
    objSqlContent.append(" sub1.unhealthy_name as \"unhealthyName\"  ");
    objSqlContent.append(" FROM sub1 ");
    objSqlContent.append(" where 1=1 ");
    return objSqlContent.toString();
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<QmsUnhealthy> getCheckIsUser(String id){
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    List<QmsUnhealthy> organizationInfoCheck = new ArrayList<QmsUnhealthy>();
    try {
        // 查询
        Query query1 = em.createNativeQuery(getCheckIsUserSql(id));
        ResultTransformer transformer = Transformers.aliasToBean(QmsUnhealthy.class);
        // 赋值给DTO
        organizationInfoCheck = query1.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("unhealthyCd", StandardBasicTypes.STRING).addScalar("isUse", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    return organizationInfoCheck;
}


public String deleteParentNodeInfo(String id){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" WITH RECURSIVE sub1 (id,unhealthy_cd,unhealthy_name,parent_cd) AS ( ");
    objSqlContent.append(" select	t01.id, ");
    objSqlContent.append(" t01.unhealthy_cd, ");
    objSqlContent.append(" t01.unhealthy_name, ");
    objSqlContent.append(" t01.parent_cd ");
    objSqlContent.append(" FROM qms_unhealthy t01 ");
    objSqlContent.append(" WHERE t01.unhealthy_cd =  '" + id + "'");
    objSqlContent.append(" UNION ALL ");
    objSqlContent.append(" SELECT t02.id, ");
    objSqlContent.append(" t02.unhealthy_cd, ");
    objSqlContent.append(" t02.unhealthy_name, ");
    objSqlContent.append(" t02.parent_cd ");
    objSqlContent.append(" FROM qms_unhealthy t02 ");
    objSqlContent.append(" INNER JOIN sub1 ON t02.parent_cd = sub1.unhealthy_cd ");
    objSqlContent.append(" ) ");
    objSqlContent.append(" SELECT sub1.id as \"id\" , ");
    objSqlContent.append(" sub1.parent_cd as \"parentCd\" , ");
    objSqlContent.append(" sub1.unhealthy_cd as \"unhealthyCd\" , ");
    objSqlContent.append(" sub1.unhealthy_name as \"unhealthyName\"  ");
    objSqlContent.append(" FROM sub1 ");
    objSqlContent.append(" where 1=1 ");
    return objSqlContent.toString();
}


}