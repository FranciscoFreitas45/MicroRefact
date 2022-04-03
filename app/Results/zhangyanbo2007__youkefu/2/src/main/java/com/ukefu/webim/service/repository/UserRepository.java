package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.User;
public interface UserRepository extends JpaRepository<User, String>{


public User findByMobileAndPasswordAndDatastatus(String mobile,String password,boolean datastatus)
;

public List<User> findByOrganAndDatastatusAndOrgi(String paramString,boolean datastatus,String orgi)
;

public List<User> findByOrgiAndCallcenterAndDatastatus(String orgi,boolean callcenter,boolean datastatus)
;

public Page<User> findByOrganInAndAgentAndDatastatusAndUsertypeIsNull(List<String> organIdList,boolean agent,boolean datastatus,Pageable pageRequest)
;

public List<User> findAll(Specification<User> spec)
;

public int countByOrgiAndOrgan(String orgi,String organ)
;

public Page<User> findByDatastatusAndOrganAndOrgi(boolean b,String organ,String orgi,Pageable pageRequest)
;

public User findByUsernameAndDatastatus(String username,boolean datastatus)
;

public List<User> findByOrganInAndAgentAndDatastatus(List<String> organIdList,boolean b,boolean status)
;

public User findById(String id)
;

public User findByUsernameAndOrgi(String paramString,String orgi)
;

public Page<User> findByDatastatusAndOrgiAndOrgid(boolean b,String orgi,String orgid,Pageable pageRequest)
;

public User findBySuperuser(boolean superuser)
;

public List<User> findByOrgiAndAgentAndDatastatus(String orgi,boolean agent,boolean status)
;

public List<User> findByOrgiAndOrganAndDatastatus(String orgi,String organ,boolean datastatus)
;

public Page<User> findByIdAndOrgi(String id,String orgi,Pageable paramPageable)
;

public Page<User> findByOrgiAndAgentAndDatastatusAndUsertypeIsNull(String orgi,boolean agent,boolean datastatus,Pageable pageRequest)
;

public Page<User> findByDatastatusAndOrgi(boolean datastatus,String orgi,Pageable paramPageable)
;

public List<User> findByOrgiAndDatastatusAndOrgid(String orgi,boolean b,String orgid)
;

public List<User> findByOrganAndOrgiAndDatastatus(String paramString,String orgi,boolean b)
;

public List<User> findByOrgi(String orgi)
;

public User findByUsernameAndPasswordAndDatastatus(String username,String password,boolean datastatus)
;

public User findByUsernameAndPassword(String paramString1,String password)
;

public List<User> findByOrgiAndCallcenterAndDatastatusAndOrgan(String orgi,boolean callcenter,boolean datastatus,String organ)
;

public List<User> findByIdInAndOrgiAndDatastatus(List<String> usersids,String orgi,boolean b)
;

public Page<User> findByOrgidAndAgentAndDatastatusAndUsertype(String orgid,boolean agent,boolean datastatus,String type,Pageable pageRequest)
;

public List<User> findByIdInAndOrganInAndDatastatus(List<String> usersids,List<String> organIdList,boolean status)
;

public List<User> findByOrganInAndDatastatus(List<String> organIdList,boolean b)
;

public List<User> findByOrgidAndAgentAndDatastatus(String orgid,boolean agent,boolean datastatus)
;

public User findByMobileAndDatastatus(String mobile,boolean datastatus)
;

public Page<User> findByDatastatusAndOrgiAndOrgidAndUsernameLike(boolean b,String orgi,String orgid,String string,Pageable pageRequest)
;

public Page<User> findByOrganInAndDatastatusAndUsernameLike(List<String> organIdList,boolean b,String username,Pageable pageRequest)
;

public User findByEmailAndDatastatus(String email,boolean datastatus)
;

public Page<User> findByOrgiAndDatastatus(String orgi,boolean b,Pageable pageRequest)
;

public long countByOrgiAndAgent(String orgi,boolean agent)
;

public Page<User> findByDatastatusAndOrgiAndUsernameLike(boolean datastatus,String orgi,String username,Pageable paramPageable)
;

public void setDisabledesk(String id,boolean disabledesk);

public void setEmail(String id,String email);

public boolean isAgent(String id);

public void setPassword(String id,String password);

public void setCreatetime(String id,Date createtime);

public void setUpdatetime(String id,Date updatetime);

public void setRoleList(String id,List<Role> roleList);

public void setRoleAuthMap(String id,Map<String,Object> roleAuthMap);

public void setUsername(String id,String username);

public boolean isSuperuser(String id);

public void setSessionid(String id,String sessionid);

public void setId(String id,String id);

public void setOrgi(String id,String orgi);

}