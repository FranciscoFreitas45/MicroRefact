package com.zis.DTO;
 import java.util.List;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zis.shiro.bean.Permission;
import com.zis.shiro.bean.Role;
import com.zis.shiro.bean.User;
import com.zis.shiro.repository.PermissionDao;
import com.zis.shiro.repository.RoleDao;
import com.zis.shiro.repository.UserDao;
import com.zis.shop.bean.Company;
import com.zis.shop.repository.CompanyDao;
import com.zis.storage.entity.StorageRepoInfo;
import com.zis.storage.repository.StorageRepoInfoDao;
import com.zis.Interface.StorageRepoInfoDao;
import com.zis.DTO.StorageRepoInfoDao;
public class SysService {

 private  UserDao userDao;

 private  PermissionDao permissionDao;

 private  CompanyDao companyDao;

 private  RoleDao roleDao;

 private  StorageRepoInfoDao storageRepoInfoDao;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public User findtUserById(Integer userId){
    return this.userDao.findOne(userId);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findtUserById"))

.queryParam("userId",userId)
;
User aux = restTemplate.getForObject(builder.toUriString(),User.class);
return aux;
}


}