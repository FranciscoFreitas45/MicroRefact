package org.danyuan.application.resume.user.dao;
 import java.util.List;
import javax.transaction.Transactional;
import org.danyuan.application.common.base.BaseDao;
import org.danyuan.application.resume.user.po.SysUserBaseInfo;
import org.danyuan.application.softm.roles.po.SysRolesInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository("sysUserBaseDao")
public interface SysUserBaseDao extends BaseDao<SysUserBaseInfo>{


@Transactional
@Modifying
@Query(" update SysUserBaseInfo t set userName =:userName,email=:email,phone=:phone,organization=:organization,department=:department,updateTime = CURRENT_TIMESTAMP  where uuid =:uuid")
public void savec(String uuid,String userName,String email,String phone,String organization,String department)
;

@Query("   select t from  SysRolesInfo t where t.uuid in ( " + " select a.rolesId from SysUserRolesInfo a where a.userId =:uuid) ")
public List<SysRolesInfo> getRoleByUser(String uuid)
;

@Transactional
@Modifying
@Query(" update SysUserBaseInfo t set userName =:userName,persionName =:persionName,sex=:sex,email=:email,phone=:phone,age=:age,discription=:discription,updateTime = CURRENT_TIMESTAMP  where uuid =:uuid")
public void saveu(String uuid,String persionName,Integer age,String userName,String email,String phone,String sex,String discription)
;

@Transactional
@Modifying
@Query("update SysUserBaseInfo t set t.password = :password where t.uuid = :uuid")
public void changePassword(String password,String uuid)
;

}