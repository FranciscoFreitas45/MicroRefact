package cn.com.cnc.fcc.service.impl;
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
public class RbacRightServiceImpl implements RbacRightService{

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

public RbacRightServiceImpl(EntityManagerFactory emf) {
    this.emf = emf;
}
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
    // ??????????????????
    Integer updateRight = 0;
    // ??????
    ZonedDateTime time = dateUtil.getDBNowDate();
    // ?????????????????????
    RbacMenuRightRelation rbacMenuRightRelation = new RbacMenuRightRelation();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // ?????????
    // EntityManager em = emf.createEntityManager();
    try {
        // /*??????????????????*/
        // Date updatetimeN = Date.from(rbacRight.getUpdDateTime().toInstant());
        // /*????????????????????????*/
        // Timestamp updatetimeNEW = new Timestamp(updatetimeN.getTime());
        // //??????????????????????????????
        // //    		rbacRightn = rbacRightRepository.findById(rbacRight.getId()).get();
        // Query query = em.createNativeQuery(getUpdataTimeSQL());
        // query.setParameter("Id", rbacRight.getId());
        // 
        // /*????????????????????????*/
        // Timestamp updatetimeBef = (Timestamp)query.getSingleResult();
        // 
        // //????????????
        // if (!updatetimeNEW.equals(updatetimeBef)) {
        // updateRight = 4;
        // return updateRight;
        // }
        // ??????
        if (rbacRight.getDelFlag() == 0) {
            // ????????????
            rbacRight.setDelFlag(0);
            // ????????????
            rbacRight.setStopFlag(0);
            // ????????????
            rbacRight.setUpdDateTime(time);
            // // ??????Code
            // rbacRight.setRightCode(rbacRight.getRightCode());
            // ????????????
            rbacRight.setRightName(rbacRight.getRightName());
            // ?????????
            rbacRight.setUpdOperCd(user.getUsername());
            // ????????????
            rbacRight.setUpdProgarmCd("RbacRightController");
            // ??????????????????
            rbacRight.setTriggerDateTime(time);
            rbacRight = rbacRightRepository.save(rbacRight);
            if (null == rbacRight) {
                updateRight = 1;
                throw new RuntimeException("??????????????????");
            }
            int delRelation = rbacMenuRightRelationRepository.deleteByRightId(Integer.valueOf(rbacRight.getId().toString()));
            if (delRelation == 0) {
                updateRight = 2;
                throw new RuntimeException("??????????????????????????????");
            }
            for (int a = 0; a < menuList.size(); a++) {
                // ?????????????????????
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
                    throw new RuntimeException("?????????????????????????????????");
                }
            }
        }
        // ??????
        if (rbacRight.getDelFlag() == 1) {
            rbacRight.setDelOperCd(user.getUsername());
            rbacRight.setDelDateTime(time);
            rbacRight.setDelProgarmCd("RbacRightController");
            rbacRight = rbacRightRepository.save(rbacRight);
            if (null == rbacRight) {
                updateRight = 5;
                throw new RuntimeException("??????????????????(??????)");
            }
            int delRelation = rbacMenuRightRelationRepository.updateByRightId(Integer.valueOf(rbacRight.getId().toString()));
            if (delRelation == 0) {
                updateRight = 6;
                throw new RuntimeException("??????????????????????????????(??????)");
            }
        }
    } catch (Exception e) {
        // ????????????
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info(e.getMessage());
    }
    // ?????????
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
    // ??????????????????
    Integer createRight = 0;
    // ??????
    ZonedDateTime time = dateUtil.getDBNowDate();
    // ?????????????????????
    RbacMenuRightRelation rbacMenuRightRelation = new RbacMenuRightRelation();
    // ?????????
    RbacRight rbacRights = new RbacRight();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) authentication.getPrincipal();
    EntityManager em = emf.createEntityManager();
    List<String> list = new ArrayList<String>();
    try {
        // ??????????????????
        Query query = em.createNativeQuery(getMaxRightCodeSQL());
        list = query.getResultList();
        // ??????????????????
        String maxCode = list.get(0);
        // ????????????
        rbacRights.setDelFlag(0);
        // ????????????
        rbacRights.setStopFlag(0);
        // ????????????
        rbacRights.setInsDateTime(time);
        // ????????????
        rbacRights.setUpdDateTime(time);
        // ??????Code
        rbacRights.setRightCode(rbacRight.getRightCode());
        // ????????????
        rbacRights.setRightName(rbacRight.getRightName());
        // ?????????
        rbacRights.setInsOperCd(user.getUsername());
        // ????????????
        rbacRights.setInsProgarmCd("RbacRightController");
        // ??????????????????
        rbacRights.setTriggerDateTime(time);
        rbacRights = rbacRightRepository.save(rbacRights);
        if (null == rbacRights) {
            createRight = 1;
            throw new RuntimeException("??????????????????");
        }
        for (int a = 0; a < menuList.size(); a++) {
            // ?????????????????????
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
                throw new RuntimeException("?????????????????????????????????");
            }
        }
    } catch (Exception e) {
        // ????????????
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info(e.getMessage());
    } finally {
        // ??????
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
    // ?????????
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
    // ?????????
    List<RbacMenu> rbacMenu = new ArrayList<RbacMenu>();
    try {
        // ??????????????????
        String sql = "SELECT * FROM  rbac_menu WHERE del_flag = 0";
        Query query = entityManager.createNativeQuery(sql, RbacMenu.class);
        // ??????sql
        rbacMenu = query.getResultList();
        // ????????????
        if (null == rbacMenu) {
            throw new RuntimeException("??????????????????");
        }
    } catch (Exception e) {
        // ?????????
        rbacMenu = new ArrayList<RbacMenu>();
    } finally {
        // ??????
        entityManager.close();
    }
    // ?????????
    return rbacMenu;
}


}