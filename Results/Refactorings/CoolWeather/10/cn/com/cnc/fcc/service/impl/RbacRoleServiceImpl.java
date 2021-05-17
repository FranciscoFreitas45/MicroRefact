import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import cn.com.cnc.fcc.domain.RbacRole;
import cn.com.cnc.fcc.domain.RbacRoleRightRelation;
import cn.com.cnc.fcc.repository.RbacRightRepository;
import cn.com.cnc.fcc.repository.RbacRoleRepository;
import cn.com.cnc.fcc.repository.RbacRoleRightRelationRepository;
import cn.com.cnc.fcc.service.RbacRoleService;
import cn.com.cnc.fcc.service.dto.RbacRightDTO;
import cn.com.cnc.fcc.service.dto.RbacRoleDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
@Service
public class RbacRoleServiceImpl implements cn.com.cnc.fcc.service.RbacRoleService,RbacRoleService{

 private  Logger log;

@Resource
 private  RbacRightRepository rbacRightRepository;

@Resource
 private  RbacRoleRepository rbacRoleRepository;

@Resource
 private  RbacRoleRightRelationRepository rbacRoleRightRelationRepository;

@PersistenceContext
 private  EntityManager entityManager;

@Autowired
 private  DateUtil dateUtil;

 private  EntityManagerFactory emf;


public String getMaxRoleCodeSQL(){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT CASE when b.role_code > 9  THEN CONCAT('OA0',b.role_code) ");
    objSqlContent.append("  ELSE CONCAT('OA00',b.role_code) END  as \"roleCode\" 			");
    objSqlContent.append(" FROM   															");
    objSqlContent.append(" (SELECT (MAX(a.role_code) + 1) as role_code        				");
    objSqlContent.append(" FROM                   											");
    objSqlContent.append(" (SELECT substr(r.role_code,3) as role_code FROM `rbac_role` r    ");
    objSqlContent.append(" WHERE r.del_flag = 0) a) b                 						");
    return objSqlContent.toString();
}


@Override
@Transactional
public Integer updateRole(String selectListVal,RbacRole rbacRole){
    // 插入返回结果
    Integer updateRole = 0;
    // 时间
    ZonedDateTime time = dateUtil.getDBNowDate();
    RbacRoleRightRelation rbacRoleRightRelation = new RbacRoleRightRelation();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 实例化
    EntityManager em = emf.createEntityManager();
    try {
        /*取得排他时间*/
        Date updatetimeN = Date.from(rbacRole.getUpdDateTime().toInstant());
        /*排他时间格式转化*/
        Timestamp updatetimeNEW = new Timestamp(updatetimeN.getTime());
        // 检索当前数据是否存在
        // rbacRolesn = rbacRoleRepository.findById(rbacRole.getId()).get();
        Query query = em.createNativeQuery(getUpdataTimeSQL());
        query.setParameter("Id", rbacRole.getId());
        /*排他时间格式转化*/
        Timestamp updatetimeBef = (Timestamp) query.getSingleResult();
        // 日期比较
        if (!updatetimeNEW.equals(updatetimeBef)) {
            updateRole = 5;
            return updateRole;
        // throw new RuntimeException("已被更新");
        }
        // 编辑
        if (rbacRole.getDelFlag() == 0) {
            // 更新者
            rbacRole.setUpdOperCd(user.getUsername());
            // 更新时间
            rbacRole.setUpdDateTime(time);
            // 更新机能
            rbacRole.setUpdProgarmCd("RbacRoleController");
            rbacRole = rbacRoleRepository.save(rbacRole);
            if (null == rbacRole) {
                updateRole = 1;
                throw new RuntimeException("更新角色失败");
            }
            int delRelation = rbacRoleRightRelationRepository.deleteByRoleId(Integer.valueOf(rbacRole.getId().toString()));
            if (delRelation == 0) {
                updateRole = 2;
                throw new RuntimeException("删除角色权限关系失败");
            }
            // 删除标示
            rbacRoleRightRelation.setDelFlag(0);
            // 停用标示
            rbacRoleRightRelation.setStopFlag(0);
            // 插入时间
            rbacRoleRightRelation.setInsDateTime(time);
            // 更新时间
            rbacRoleRightRelation.setUpdDateTime(time);
            // 角色id
            rbacRoleRightRelation.setRoleId(Integer.valueOf(rbacRole.getId().toString()));
            // 权限id
            rbacRoleRightRelation.setRightId(Integer.valueOf(selectListVal));
            // 创建者
            rbacRoleRightRelation.setInsOperCd(user.getUsername());
            // 更新机能
            rbacRoleRightRelation.setUpdProgarmCd("RbacRoleController");
            // 自动变动时间
            rbacRoleRightRelation.setTriggerDateTime(time);
            rbacRoleRightRelation = rbacRoleRightRelationRepository.save(rbacRoleRightRelation);
            if (null == rbacRoleRightRelation) {
                updateRole = 2;
                throw new RuntimeException("更新角色权限关系");
            }
        }
        if (rbacRole.getDelFlag() == 1) {
            // 删除时间
            rbacRole.setDelDateTime(time);
            // 删除者
            rbacRole.setDelOperCd(user.getUsername());
            // 删除机能
            rbacRole.setDelProgarmCd("RbacRoleController");
            rbacRole = rbacRoleRepository.save(rbacRole);
            if (null == rbacRole) {
                updateRole = 3;
                throw new RuntimeException("更新删除失败(逻辑)");
            }
            int delRelation = rbacRoleRightRelationRepository.updateByRoleId(Integer.valueOf(rbacRole.getId().toString()));
            if (delRelation == 0) {
                updateRole = 4;
                throw new RuntimeException("删除角色权限关系失败(逻辑)");
            }
        }
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info("更新失败");
    } finally {
        // 关闭
        em.close();
    }
    return updateRole;
}


@SuppressWarnings("unchecked")
public List<RbacRoleDTO> getRoleRightInfo(Integer roleId){
    EntityManager em = emf.createEntityManager();
    List<RbacRoleDTO> rbacRole = new ArrayList<RbacRoleDTO>();
    try {
        Query query = em.createNativeQuery(getRoleRightSQL());
        query.setParameter("roleId", roleId);
        rbacRole = query.getResultList();
        query = null;
    } catch (Exception e) {
        throw new InternalServerErrorException("rbacRole could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return rbacRole;
}


@SuppressWarnings("unchecked")
public List<RbacRightDTO> getRightList(){
    EntityManager em = emf.createEntityManager();
    List<RbacRightDTO> rbacRight = new ArrayList<RbacRightDTO>();
    try {
        String sql = "SELECT r.id,r.right_name as rightName FROM rbac_right r where r.del_flag = 0";
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(RbacRightDTO.class);
        rbacRight = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("rightName", StandardBasicTypes.STRING).addScalar("id", StandardBasicTypes.LONG).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        throw new InternalServerErrorException("rbacRole could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return rbacRight;
}


@Override
@Transactional
@SuppressWarnings("unchecked")
public Integer createRole(String selectListVal,RbacRole rbacRole){
    // 插入返回结果
    Integer createRole = 0;
    // 时间
    ZonedDateTime time = dateUtil.getDBNowDate();
    // 角色表
    RbacRole rbacRoles = new RbacRole();
    RbacRoleRightRelation rbacRoleRightRelation = new RbacRoleRightRelation();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) authentication.getPrincipal();
    EntityManager em = emf.createEntityManager();
    List<String> list = new ArrayList<String>();
    try {
        // 查询角色编号
        Query query = em.createNativeQuery(getMaxRoleCodeSQL());
        list = query.getResultList();
        // 最大角色编号
        String maxCode = list.get(0);
        // 删除标示
        rbacRoles.setDelFlag(0);
        // 停用标示
        rbacRoles.setStopFlag(0);
        // 插入时间
        rbacRoles.setInsDateTime(time);
        // 更新时间
        rbacRoles.setUpdDateTime(time);
        // 角色Code
        rbacRoles.setRoleCode(rbacRole.getRoleCode());
        // 角色名称
        rbacRoles.setRoleName(rbacRole.getRoleName());
        // 创建者
        rbacRoles.setInsOperCd(user.getUsername());
        // 创建机能
        rbacRoles.setInsProgarmCd("RbacRoleController");
        // 自动变动时间
        rbacRoles.setTriggerDateTime(time);
        rbacRoles = rbacRoleRepository.save(rbacRoles);
        if (null == rbacRoles) {
            createRole = 1;
            throw new RuntimeException("插入角色失败");
        }
        // 删除标示
        rbacRoleRightRelation.setDelFlag(0);
        // 停用标示
        rbacRoleRightRelation.setStopFlag(0);
        // 插入时间
        rbacRoleRightRelation.setInsDateTime(time);
        // 更新时间
        rbacRoleRightRelation.setUpdDateTime(time);
        // 角色id
        rbacRoleRightRelation.setRoleId(Integer.valueOf(rbacRoles.getId().toString()));
        // 权限id
        rbacRoleRightRelation.setRightId(Integer.valueOf(selectListVal));
        // 创建者
        rbacRoleRightRelation.setInsOperCd(user.getUsername());
        // 创建机能
        rbacRoleRightRelation.setInsProgarmCd("RbacRoleController");
        // 自动变动时间
        rbacRoleRightRelation.setTriggerDateTime(time);
        rbacRoleRightRelation = rbacRoleRightRelationRepository.save(rbacRoleRightRelation);
        if (null == rbacRoleRightRelation) {
            createRole = 2;
            throw new RuntimeException("插入角色权限关系");
        }
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info("插入失败");
    } finally {
        // 关闭
        em.close();
    }
    return createRole;
}


public String getUpdataTimeSQL(){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" select upd_date_time from rbac_role		  ");
    objSqlContent.append(" where  id = :Id		  ");
    objSqlContent.append(" and del_flag = 0		  ");
    return objSqlContent.toString();
}


public String getRoleRightSQL(){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" select rr.id as \"id\", r.right_name as \"rightName\",");
    objSqlContent.append(" rr.right_id  as \"rightId\", rr.role_id as \"roleId\", ");
    objSqlContent.append(" o.upd_date_time  as \"updateTime\" ");
    objSqlContent.append(" FROM rbac_role_right_relation rr     ");
    objSqlContent.append(" INNER JOIN rbac_right r  ON          ");
    objSqlContent.append(" rr.right_id = r.id                   ");
    objSqlContent.append(" AND rr.del_flag = 0                  ");
    objSqlContent.append(" AND r.del_flag = 0                   ");
    objSqlContent.append(" INNER JOIN rbac_role o ON            ");
    objSqlContent.append(" rr.role_id = o.id     		      	");
    objSqlContent.append(" AND o.del_flag = 0     		      	");
    objSqlContent.append(" where rr.role_id = :roleId           ");
    return objSqlContent.toString();
}


}