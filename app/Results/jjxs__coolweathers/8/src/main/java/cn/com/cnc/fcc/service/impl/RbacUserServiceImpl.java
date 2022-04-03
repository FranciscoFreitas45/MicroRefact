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
    // 返回值
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
    // 返回值
    return rbacRole;
}


@Override
@Transactional
public Integer updateUser(String selectListVal,RbacUser rbacUser){
    // 插入返回结果
    Integer updateUser = 0;
    // 时间
    ZonedDateTime time = dateUtil.getDBNowDate();
    RbacUserRightRelation rbacUserRightRelation = new RbacUserRightRelation();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 实例化
    EntityManager em = emf.createEntityManager();
    try {
        /*取得排他时间*/
        Date updatetimeN = Date.from(rbacUser.getUpdDateTime().toInstant());
        /*排他时间格式转化*/
        Timestamp updatetimeNEW = new Timestamp(updatetimeN.getTime());
        // 检索当前数据是否存在
        // RbacUser newUser = rbacUserRepository.findById(rbacUser.getId()).get();
        Query query = em.createNativeQuery(getUpdataTimeSQL());
        query.setParameter("Id", rbacUser.getId());
        /*取得数据库中的更新时间*/
        Timestamp updatetimeBef = (Timestamp) query.getSingleResult();
        // 日期比较
        if (!updatetimeNEW.equals(updatetimeBef)) {
            updateUser = 4;
            // 已被更新
            return updateUser;
        }
        // 更新
        if (rbacUser.getDelFlag() == 0) {
            // 设置更新时间
            rbacUser.setUpdDateTime(time);
            // 设置更新者
            rbacUser.setUpdOperCd(user.getUsername());
            // 设置更新机能
            rbacUser.setUpdProgarmCd("RbacUserController");
            // 判断是否修改密码
            if (rbacUser.getUserPassword().length() <= 18) {
                // 放入参数：密码
                rbacUser.setUserPassword(MD5Util.toMd5(rbacUser.getUserPassword()));
            }
            rbacUser = rbacUserRepository.save(rbacUser);
            if (null == rbacUser) {
                updateUser = 1;
                throw new RuntimeException("更新员工失败");
            }
            // 删除员工权限关系
            int delRelation = rbacUserRightRelationRepository.deleteByUserId(Integer.valueOf(rbacUser.getId().toString()));
            if (delRelation == 0) {
                updateUser = 2;
                throw new RuntimeException("删除员工角色关系失败");
            }
            // 删除标示
            rbacUserRightRelation.setDelFlag(0);
            // 停用标示
            rbacUserRightRelation.setStopFlag(0);
            // 插入时间
            rbacUserRightRelation.setInsDateTime(time);
            // 更新时间
            rbacUserRightRelation.setUpdDateTime(time);
            // 员工id
            rbacUserRightRelation.setRoleId((Integer.valueOf(selectListVal)));
            // 角色id
            rbacUserRightRelation.setUserId(Integer.valueOf(rbacUser.getId().toString()));
            // 创建者
            rbacUserRightRelation.setInsOperCd(user.getUsername());
            // 设置更新机能
            rbacUserRightRelation.setUpdProgarmCd("RbacUserController");
            // 自动变动时间
            rbacUserRightRelation.setTriggerDateTime(time);
            rbacUserRightRelation = rbacUserRightRelationRepository.save(rbacUserRightRelation);
            if (null == rbacUserRightRelation) {
                updateUser = 3;
                throw new RuntimeException("更新员工角色关系");
            }
        }
        // 删除
        if (rbacUser.getDelFlag() == 1) {
            // 设置删除时间
            rbacUser.setDelDateTime(time);
            // 设置删除者
            rbacUser.setDelOperCd(user.getUsername());
            // 设置删除机能
            rbacUser.setDelProgarmCd("RbacUserController");
            rbacUser = rbacUserRepository.save(rbacUser);
            if (null == rbacUser) {
                updateUser = 5;
                // 删除失败
                return updateUser;
            // throw new RuntimeException("删除员工失败");
            }
        }
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info(e.getMessage());
        return updateUser;
    } finally {
        // 关闭
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
    // 插入返回结果
    Integer createUser = 0;
    // 时间
    ZonedDateTime time = dateUtil.getDBNowDate();
    // 角色表
    RbacUser rbacUsers = new RbacUser();
    RbacUserRightRelation rbacUserRightRelation = new RbacUserRightRelation();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) authentication.getPrincipal();
    String sql = "";
    // 实例化
    EntityManager em = emf.createEntityManager();
    try {
        // 验证用户名是否重复
        sql = "select count(id) from RbacUser where delFlag = 0 and userCode = :userCode";
        Query query = em.createQuery(sql);
        // 放入参数：用户名
        query.setParameter("userCode", rbacUser.getUserCode());
        // 执行Sql
        Long number = (Long) query.getSingleResult();
        // 结果判断
        if (number > 0) {
            createUser = 3;
            return createUser;
        }
        // 设置商户Id
        rbacUsers.setAppId(0);
        // 设置删除标示
        rbacUsers.setDelFlag(0);
        // 设置停用标示
        rbacUsers.setStopFlag(0);
        // 设置创建时间
        rbacUsers.setInsDateTime(time);
        // 设置更新时间
        rbacUsers.setUpdDateTime(time);
        // 设置创建者
        rbacUsers.setInsOperCd(user.getUsername());
        // 设置创建机能
        rbacUsers.setInsProgarmCd("RbacUserController");
        // 设置自动变动时间
        rbacUsers.setTriggerDateTime(time);
        // 设置登陆次数
        rbacUsers.setUserLoginCount(0);
        // 设置用户密码
        rbacUsers.setUserPassword(MD5Util.toMd5(rbacUser.getUserPassword()));
        // 用户名
        rbacUsers.setUserCode(rbacUser.getUserCode());
        // 用户姓名
        rbacUsers.setUserName(rbacUser.getUserName());
        // 用户电话
        rbacUsers.setUserMobile(rbacUser.getUserMobile());
        // 用户邮件
        rbacUsers.setUserMail(rbacUser.getUserMail());
        rbacUsers = rbacUserRepository.save(rbacUsers);
        if (null == rbacUsers) {
            createUser = 1;
            throw new RuntimeException("插入员工失败");
        }
        // 删除标示
        rbacUserRightRelation.setDelFlag(0);
        // 停用标示
        rbacUserRightRelation.setStopFlag(0);
        // 插入时间
        rbacUserRightRelation.setInsDateTime(time);
        // 更新时间
        rbacUserRightRelation.setUpdDateTime(time);
        // 员工id
        rbacUserRightRelation.setRoleId((Integer.valueOf(selectListVal)));
        // 角色id
        rbacUserRightRelation.setUserId(Integer.valueOf(rbacUsers.getId().toString()));
        // 创建者
        rbacUserRightRelation.setInsOperCd(user.getUsername());
        // 设置创建机能
        rbacUserRightRelation.setInsProgarmCd("RbacUserController");
        // 自动变动时间
        rbacUserRightRelation.setTriggerDateTime(time);
        rbacUserRightRelation = rbacUserRightRelationRepository.save(rbacUserRightRelation);
        if (null == rbacUserRightRelation) {
            createUser = 2;
            throw new RuntimeException("插入员工角色关系");
        }
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info(e.getMessage());
        return createUser;
    } finally {
        // 关闭
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