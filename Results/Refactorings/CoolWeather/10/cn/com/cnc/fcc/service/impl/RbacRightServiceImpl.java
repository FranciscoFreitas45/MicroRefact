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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import cn.com.cnc.fcc.domain.RbacMenu;
import cn.com.cnc.fcc.domain.RbacMenuRightRelation;
import cn.com.cnc.fcc.domain.RbacRight;
import cn.com.cnc.fcc.repository.RbacMenuRightRelationRepository;
import cn.com.cnc.fcc.repository.RbacRightRepository;
import cn.com.cnc.fcc.service.RbacRightService;
import cn.com.cnc.fcc.service.dto.RbacRightDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
@Service
public class RbacRightServiceImpl implements RbacRightService,cn.com.cnc.fcc.service.RbacRightService{

 private  Logger log;

@Resource
 private  RbacRightRepository rbacRightRepository;

@Resource
 private  RbacMenuRightRelationRepository rbacMenuRightRelationRepository;

@PersistenceContext
 private  EntityManager entityManager;

@Autowired
 private  DateUtil dateUtil;

 private  EntityManagerFactory emf;


public String getRightMenuSQL(){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT m.p_menu_id as \"pMenuId\",m.menu_name as \"menuName\" , ");
    objSqlContent.append(" r.id as \"id\" ,r.menu_id as \"menuId\" ,r.right_id as \"rightId\", ");
    objSqlContent.append(" t.upd_date_time as \"updateTime\"   	 ");
    objSqlContent.append(" FROM `rbac_menu_right_relation` r     ");
    objSqlContent.append(" INNER JOIN rbac_menu m ON             ");
    objSqlContent.append(" r.menu_id = m.id                      ");
    objSqlContent.append(" AND m.del_flag = 0                    ");
    objSqlContent.append(" AND m.stop_flag = 0                   ");
    objSqlContent.append(" AND r.del_flag = 0                    ");
    objSqlContent.append(" AND r.stop_flag = 0       		     ");
    objSqlContent.append(" INNER JOIN  rbac_right t        		 ");
    objSqlContent.append(" ON  t.id = r.right_id        		 ");
    objSqlContent.append(" AND t.del_flag = 0		      		 ");
    objSqlContent.append(" where r.right_id = :rightId           ");
    return objSqlContent.toString();
}


@Override
@Transactional
public Integer updateRight(List<String> menuList,RbacRight rbacRight){
    // 编辑返回结果
    Integer updateRight = 0;
    // 时间
    ZonedDateTime time = dateUtil.getDBNowDate();
    // 菜单权限关系表
    RbacMenuRightRelation rbacMenuRightRelation = new RbacMenuRightRelation();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 实例化
    // EntityManager em = emf.createEntityManager();
    try {
        // /*取得排他时间*/
        // Date updatetimeN = Date.from(rbacRight.getUpdDateTime().toInstant());
        // /*排他时间格式转化*/
        // Timestamp updatetimeNEW = new Timestamp(updatetimeN.getTime());
        // //检索当前数据是否存在
        // //    		rbacRightn = rbacRightRepository.findById(rbacRight.getId()).get();
        // Query query = em.createNativeQuery(getUpdataTimeSQL());
        // query.setParameter("Id", rbacRight.getId());
        // 
        // /*排他时间格式转化*/
        // Timestamp updatetimeBef = (Timestamp)query.getSingleResult();
        // 
        // //日期比较
        // if (!updatetimeNEW.equals(updatetimeBef)) {
        // updateRight = 4;
        // return updateRight;
        // }
        // 编辑
        if (rbacRight.getDelFlag() == 0) {
            // 删除标示
            rbacRight.setDelFlag(0);
            // 停用标示
            rbacRight.setStopFlag(0);
            // 更新时间
            rbacRight.setUpdDateTime(time);
            // // 权限Code
            // rbacRight.setRightCode(rbacRight.getRightCode());
            // 权限名称
            rbacRight.setRightName(rbacRight.getRightName());
            // 更新者
            rbacRight.setUpdOperCd(user.getUsername());
            // 更新机能
            rbacRight.setUpdProgarmCd("RbacRightController");
            // 自动变动时间
            rbacRight.setTriggerDateTime(time);
            rbacRight = rbacRightRepository.save(rbacRight);
            if (null == rbacRight) {
                updateRight = 1;
                throw new RuntimeException("更新权限失败");
            }
            int delRelation = rbacMenuRightRelationRepository.deleteByRightId(Integer.valueOf(rbacRight.getId().toString()));
            if (delRelation == 0) {
                updateRight = 2;
                throw new RuntimeException("删除权限菜单关系失败");
            }
            for (int a = 0; a < menuList.size(); a++) {
                // 菜单权限关系表
                rbacMenuRightRelation = new RbacMenuRightRelation();
                rbacMenuRightRelation.setDelFlag(0);
                rbacMenuRightRelation.setStopFlag(0);
                rbacMenuRightRelation.setInsDateTime(time);
                rbacMenuRightRelation.setUpdDateTime(time);
                rbacMenuRightRelation.setInsOperCd(user.getUsername());
                rbacMenuRightRelation.setMenuId(Integer.valueOf(menuList.get(a)));
                rbacMenuRightRelation.setRightId(Integer.valueOf(rbacRight.getId().toString()));
                rbacMenuRightRelation.setUpdProgarmCd("RbacRightController");
                rbacMenuRightRelation.setTriggerDateTime(time);
                rbacMenuRightRelation = rbacMenuRightRelationRepository.save(rbacMenuRightRelation);
                if (null == rbacMenuRightRelation) {
                    updateRight = 3;
                    throw new RuntimeException("更新菜单权限关系表失败");
                }
            }
        }
        // 删除
        if (rbacRight.getDelFlag() == 1) {
            rbacRight.setDelOperCd(user.getUsername());
            rbacRight.setDelDateTime(time);
            rbacRight.setDelProgarmCd("RbacRightController");
            rbacRight = rbacRightRepository.save(rbacRight);
            if (null == rbacRight) {
                updateRight = 5;
                throw new RuntimeException("删除权限失败(逻辑)");
            }
            int delRelation = rbacMenuRightRelationRepository.updateByRightId(Integer.valueOf(rbacRight.getId().toString()));
            if (delRelation == 0) {
                updateRight = 6;
                throw new RuntimeException("删除权限菜单关系失败(逻辑)");
            }
        }
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info(e.getMessage());
    }
    // 返回值
    return updateRight;
}


public String getMaxRightCodeSQL(){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT   CASE when b.right_code > 9  THEN CONCAT('RA0',b.right_code) ");
    objSqlContent.append(" ELSE CONCAT('RA00',b.right_code) END as \"maxRightCode\" 					");
    objSqlContent.append(" FROM   																");
    objSqlContent.append(" (SELECT (MAX(a.right_code) + 1) as right_code           				");
    objSqlContent.append(" FROM                   												");
    objSqlContent.append(" (SELECT substr(r.right_code,3) as right_code FROM `rbac_right` r     ");
    objSqlContent.append(" WHERE r.del_flag = 0) a) b                 ");
    return objSqlContent.toString();
}


@Override
@Transactional
@SuppressWarnings("unchecked")
public Integer createRight(List<String> menuList,RbacRight rbacRight){
    // 插入返回结果
    Integer createRight = 0;
    // 时间
    ZonedDateTime time = dateUtil.getDBNowDate();
    // 菜单权限关系表
    RbacMenuRightRelation rbacMenuRightRelation = new RbacMenuRightRelation();
    // 权限表
    RbacRight rbacRights = new RbacRight();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) authentication.getPrincipal();
    EntityManager em = emf.createEntityManager();
    List<String> list = new ArrayList<String>();
    try {
        // 查询权限编号
        Query query = em.createNativeQuery(getMaxRightCodeSQL());
        list = query.getResultList();
        // 最大权限编号
        String maxCode = list.get(0);
        // 删除标示
        rbacRights.setDelFlag(0);
        // 停用标示
        rbacRights.setStopFlag(0);
        // 插入时间
        rbacRights.setInsDateTime(time);
        // 更新时间
        rbacRights.setUpdDateTime(time);
        // 权限Code
        rbacRights.setRightCode(rbacRight.getRightCode());
        // 权限名称
        rbacRights.setRightName(rbacRight.getRightName());
        // 创建者
        rbacRights.setInsOperCd(user.getUsername());
        // 创建机能
        rbacRights.setInsProgarmCd("RbacRightController");
        // 自动变动时间
        rbacRights.setTriggerDateTime(time);
        rbacRights = rbacRightRepository.save(rbacRights);
        if (null == rbacRights) {
            createRight = 1;
            throw new RuntimeException("插入权限失败");
        }
        for (int a = 0; a < menuList.size(); a++) {
            // 菜单权限关系表
            rbacMenuRightRelation = new RbacMenuRightRelation();
            rbacMenuRightRelation.setDelFlag(0);
            rbacMenuRightRelation.setStopFlag(0);
            rbacMenuRightRelation.setInsDateTime(time);
            rbacMenuRightRelation.setUpdDateTime(time);
            rbacMenuRightRelation.setInsOperCd(user.getUsername());
            rbacMenuRightRelation.setMenuId(Integer.valueOf(menuList.get(a)));
            rbacMenuRightRelation.setRightId(Integer.valueOf(rbacRights.getId().toString()));
            rbacMenuRightRelation.setInsProgarmCd("RbacRightController");
            rbacMenuRightRelation.setTriggerDateTime(time);
            rbacMenuRightRelation = rbacMenuRightRelationRepository.save(rbacMenuRightRelation);
            if (null == rbacMenuRightRelation) {
                createRight = 2;
                throw new RuntimeException("插入菜单权限关系表失败");
            }
        }
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info(e.getMessage());
    } finally {
        // 关闭
        em.close();
    }
    return createRight;
}


@SuppressWarnings("unchecked")
public List<RbacRightDTO> getRightMenuInfo(Integer rightId){
    EntityManager em = emf.createEntityManager();
    List<RbacRightDTO> rbacRight = new ArrayList<RbacRightDTO>();
    try {
        Query query = em.createNativeQuery(getRightMenuSQL());
        query.setParameter("rightId", rightId);
        rbacRight = query.getResultList();
        query = null;
    } catch (Exception e) {
        throw new InternalServerErrorException("rbacRight could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return rbacRight;
}


public String getUpdataTimeSQL(){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" select upd_date_time from rbac_right		  ");
    objSqlContent.append(" where  id = :Id		  ");
    objSqlContent.append(" and del_flag = 0		  ");
    return objSqlContent.toString();
}


@SuppressWarnings("unchecked")
public List<RbacMenu> getMenuInfo(){
    // 实例化
    List<RbacMenu> rbacMenu = new ArrayList<RbacMenu>();
    try {
        // 获取权限列表
        String sql = "SELECT * FROM  rbac_menu WHERE del_flag = 0";
        Query query = entityManager.createNativeQuery(sql, RbacMenu.class);
        // 执行sql
        rbacMenu = query.getResultList();
        // 结果判断
        if (null == rbacMenu) {
            throw new RuntimeException("获取数据失败");
        }
    } catch (Exception e) {
        // 实例化
        rbacMenu = new ArrayList<RbacMenu>();
    } finally {
        // 关闭
        entityManager.close();
    }
    // 返回值
    return rbacMenu;
}


}