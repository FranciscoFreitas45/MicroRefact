package cn.com.cnc.fcc.service.impl;
 import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import cn.com.cnc.fcc.domain.RbacUser;
import cn.com.cnc.fcc.domain.RbacUserRightRelation;
import cn.com.cnc.fcc.repository.RbacUserRepository;
import cn.com.cnc.fcc.repository.RbacUserRightRelationRepository;
import cn.com.cnc.fcc.service.RbacUserService;
import cn.com.cnc.fcc.service.dto.RbacUserDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.MD5Util;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
@Service
public class RbacUserServiceImpl implements RbacUserService{

 private  Logger log;

@PersistenceContext
 private  EntityManager entityManager;

@Autowired
 private  DateUtil dateUtil;

 private  RbacUserRepository rbacUserRepository;

 private  RbacUserRightRelationRepository rbacUserRightRelationRepository;

 private  EntityManagerFactory emf;

public RbacUserServiceImpl(EntityManagerFactory emf, RbacUserRepository rbacUserRepository, RbacUserRightRelationRepository rbacUserRightRelationRepository) {
    this.emf = emf;
    this.rbacUserRepository = rbacUserRepository;
    this.rbacUserRightRelationRepository = rbacUserRightRelationRepository;
}
@SuppressWarnings("unchecked")
public List<RbacUserDTO> getUserRoleInfo(Integer userId){
    EntityManager em = emf.createEntityManager();
    List<RbacUserDTO> rbacUser = new ArrayList<RbacUserDTO>();
    try {
        Query query = em.createNativeQuery(getUserRoleSQL());
        query.setParameter("userId", userId);
        rbacUser = query.getResultList();
        query = null;
    } catch (Exception e) {
        throw new InternalServerErrorException("rbacRole could not be found");
    } finally {
        em.close();
    }
    // ?????????
    return rbacUser;
}


@SuppressWarnings("unchecked")
public List<RbacUserDTO> getRoleList(){
    EntityManager em = emf.createEntityManager();
    List<RbacUserDTO> rbacRole = new ArrayList<RbacUserDTO>();
    try {
        String sql = "SELECT r.id,r.role_name as roleName FROM rbac_role r where r.del_flag = 0";
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(RbacUserDTO.class);
        rbacRole = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("roleName", StandardBasicTypes.STRING).addScalar("id", StandardBasicTypes.LONG).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        throw new InternalServerErrorException("rbacRole could not be found");
    } finally {
        em.close();
    }
    // ?????????
    return rbacRole;
}


@Override
@Transactional
public Integer updateUser(String selectListVal,RbacUser rbacUser){
    // ??????????????????
    Integer updateUser = 0;
    // ??????
    ZonedDateTime time = dateUtil.getDBNowDate();
    RbacUserRightRelation rbacUserRightRelation = new RbacUserRightRelation();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // ?????????
    EntityManager em = emf.createEntityManager();
    try {
        /*??????????????????*/
        Date updatetimeN = Date.from(rbacUser.getUpdDateTime().toInstant());
        /*????????????????????????*/
        Timestamp updatetimeNEW = new Timestamp(updatetimeN.getTime());
        // ??????????????????????????????
        // RbacUser newUser = rbacUserRepository.findById(rbacUser.getId()).get();
        Query query = em.createNativeQuery(getUpdataTimeSQL());
        query.setParameter("Id", rbacUser.getId());
        /*?????????????????????????????????*/
        Timestamp updatetimeBef = (Timestamp) query.getSingleResult();
        // ????????????
        if (!updatetimeNEW.equals(updatetimeBef)) {
            updateUser = 4;
            // ????????????
            return updateUser;
        }
        // ??????
        if (rbacUser.getDelFlag() == 0) {
            // ??????????????????
            rbacUser.setUpdDateTime(time);
            // ???????????????
            rbacUser.setUpdOperCd(user.getUsername());
            // ??????????????????
            rbacUser.setUpdProgarmCd("RbacUserController");
            // ????????????????????????
            if (rbacUser.getUserPassword().length() <= 18) {
                // ?????????????????????
                rbacUser.setUserPassword(MD5Util.toMd5(rbacUser.getUserPassword()));
            }
            rbacUser = rbacUserRepository.save(rbacUser);
            if (null == rbacUser) {
                updateUser = 1;
                throw new RuntimeException("??????????????????");
            }
            // ????????????????????????
            int delRelation = rbacUserRightRelationRepository.deleteByUserId(Integer.valueOf(rbacUser.getId().toString()));
            if (delRelation == 0) {
                updateUser = 2;
                throw new RuntimeException("??????????????????????????????");
            }
            // ????????????
            rbacUserRightRelation.setDelFlag(0);
            // ????????????
            rbacUserRightRelation.setStopFlag(0);
            // ????????????
            rbacUserRightRelation.setInsDateTime(time);
            // ????????????
            rbacUserRightRelation.setUpdDateTime(time);
            // ??????id
            rbacUserRightRelation.setRoleId((Integer.valueOf(selectListVal)));
            // ??????id
            rbacUserRightRelation.setUserId(Integer.valueOf(rbacUser.getId().toString()));
            // ?????????
            rbacUserRightRelation.setInsOperCd(user.getUsername());
            // ??????????????????
            rbacUserRightRelation.setUpdProgarmCd("RbacUserController");
            // ??????????????????
            rbacUserRightRelation.setTriggerDateTime(time);
            rbacUserRightRelation = rbacUserRightRelationRepository.save(rbacUserRightRelation);
            if (null == rbacUserRightRelation) {
                updateUser = 3;
                throw new RuntimeException("????????????????????????");
            }
        }
        // ??????
        if (rbacUser.getDelFlag() == 1) {
            // ??????????????????
            rbacUser.setDelDateTime(time);
            // ???????????????
            rbacUser.setDelOperCd(user.getUsername());
            // ??????????????????
            rbacUser.setDelProgarmCd("RbacUserController");
            rbacUser = rbacUserRepository.save(rbacUser);
            if (null == rbacUser) {
                updateUser = 5;
                // ????????????
                return updateUser;
            // throw new RuntimeException("??????????????????");
            }
        }
    } catch (Exception e) {
        // ????????????
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info(e.getMessage());
        return updateUser;
    } finally {
        // ??????
        em.close();
    }
    return updateUser;
}


public String getUpdataTimeSQL(){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" select upd_date_time from rbac_user		  ");
    objSqlContent.append(" where  id = :Id		  ");
    objSqlContent.append(" and del_flag = 0		  ");
    return objSqlContent.toString();
}


@Override
@Transactional
public Integer createUser(String selectListVal,RbacUser rbacUser){
    // ??????????????????
    Integer createUser = 0;
    // ??????
    ZonedDateTime time = dateUtil.getDBNowDate();
    // ?????????
    RbacUser rbacUsers = new RbacUser();
    RbacUserRightRelation rbacUserRightRelation = new RbacUserRightRelation();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) authentication.getPrincipal();
    String sql = "";
    // ?????????
    EntityManager em = emf.createEntityManager();
    try {
        // ???????????????????????????
        sql = "select count(id) from RbacUser where delFlag = 0 and userCode = :userCode";
        Query query = em.createQuery(sql);
        // ????????????????????????
        query.setParameter("userCode", rbacUser.getUserCode());
        // ??????Sql
        Long number = (Long) query.getSingleResult();
        // ????????????
        if (number > 0) {
            createUser = 3;
            return createUser;
        }
        // ????????????Id
        rbacUsers.setAppId(0);
        // ??????????????????
        rbacUsers.setDelFlag(0);
        // ??????????????????
        rbacUsers.setStopFlag(0);
        // ??????????????????
        rbacUsers.setInsDateTime(time);
        // ??????????????????
        rbacUsers.setUpdDateTime(time);
        // ???????????????
        rbacUsers.setInsOperCd(user.getUsername());
        // ??????????????????
        rbacUsers.setInsProgarmCd("RbacUserController");
        // ????????????????????????
        rbacUsers.setTriggerDateTime(time);
        // ??????????????????
        rbacUsers.setUserLoginCount(0);
        // ??????????????????
        rbacUsers.setUserPassword(MD5Util.toMd5(rbacUser.getUserPassword()));
        // ?????????
        rbacUsers.setUserCode(rbacUser.getUserCode());
        // ????????????
        rbacUsers.setUserName(rbacUser.getUserName());
        // ????????????
        rbacUsers.setUserMobile(rbacUser.getUserMobile());
        // ????????????
        rbacUsers.setUserMail(rbacUser.getUserMail());
        rbacUsers = rbacUserRepository.save(rbacUsers);
        if (null == rbacUsers) {
            createUser = 1;
            throw new RuntimeException("??????????????????");
        }
        // ????????????
        rbacUserRightRelation.setDelFlag(0);
        // ????????????
        rbacUserRightRelation.setStopFlag(0);
        // ????????????
        rbacUserRightRelation.setInsDateTime(time);
        // ????????????
        rbacUserRightRelation.setUpdDateTime(time);
        // ??????id
        rbacUserRightRelation.setRoleId((Integer.valueOf(selectListVal)));
        // ??????id
        rbacUserRightRelation.setUserId(Integer.valueOf(rbacUsers.getId().toString()));
        // ?????????
        rbacUserRightRelation.setInsOperCd(user.getUsername());
        // ??????????????????
        rbacUserRightRelation.setInsProgarmCd("RbacUserController");
        // ??????????????????
        rbacUserRightRelation.setTriggerDateTime(time);
        rbacUserRightRelation = rbacUserRightRelationRepository.save(rbacUserRightRelation);
        if (null == rbacUserRightRelation) {
            createUser = 2;
            throw new RuntimeException("????????????????????????");
        }
    } catch (Exception e) {
        // ????????????
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info(e.getMessage());
        return createUser;
    } finally {
        // ??????
        em.close();
    }
    return createUser;
}


public String getUserRoleSQL(){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" select rr.role_id  as \"roleId\", r.role_name as \"roleName\", ");
    objSqlContent.append(" u.upd_date_time  as \"updateTime\"		  ");
    objSqlContent.append(" FROM `rbac_user_right_relation` rr     ");
    objSqlContent.append(" INNER JOIN rbac_role r ON              ");
    objSqlContent.append(" rr.role_id = r.id                   	  ");
    objSqlContent.append(" AND rr.del_flag = 0                    ");
    objSqlContent.append(" AND r.del_flag = 0                     ");
    objSqlContent.append(" INNER JOIN rbac_user u                 ");
    objSqlContent.append(" ON rr.user_id = u.id                   ");
    objSqlContent.append(" AND u.del_flag = 0                     ");
    objSqlContent.append(" WHERE rr.user_id = :userId             ");
    return objSqlContent.toString();
}


}