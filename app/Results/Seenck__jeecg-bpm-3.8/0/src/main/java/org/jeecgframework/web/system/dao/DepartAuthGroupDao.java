package org.jeecgframework.web.system.dao;
 import java.util.List;
import java.util.Map;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.web.system.pojo.base.TSUser;
@MiniDao
public interface DepartAuthGroupDao {


@ResultType(Map.class)
public MiniDaoPage<Map<String,Object>> getDepartAuthGroupByUserId(int page,int rows,String userId)
;

@ResultType(Map.class)
public MiniDaoPage<Map<String,Object>> getDepartAuthRole(int page,int rows,String userId)
;

@ResultType(Map.class)
public MiniDaoPage<Map<String,Object>> getDepartAuthGroupList(int page,int rows)
;

@ResultType(TSUser.class)
public MiniDaoPage<TSUser> getUserByDepartCode(int page,int rows,String orgCode,TSUser u)
;

@Sql("select departname from t_s_depart where id in (select org_id from t_s_user_org where user_id =:userid)")
@ResultType(String.class)
public List<String> getUserDepartNames(String userid)
;

@Sql("select dag.*,r.id,r.rolecode,r.rolename,r.depart_ag_id,r.role_type from t_s_depart_auth_group dag,t_s_role r where dag.id = r.depart_ag_id")
public List<Map<String,Object>> chkDepartAuthRole()
;

@Sql("select dag.* from t_s_depart_auth_group as dag join t_s_depart_authg_manager as dam on dam.group_id=dag.id where user_id = :userId")
public List<Map<String,Object>> chkDepartAuthGroupList(String userId)
;

}