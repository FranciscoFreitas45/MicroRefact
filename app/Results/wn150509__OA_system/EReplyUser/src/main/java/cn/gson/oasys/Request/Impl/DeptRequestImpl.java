package cn.gson.oasys.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.DTO.Dept;
import cn.gson.oasys.Request.DeptRequest;
public class DeptRequestImpl implements DeptRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setDept(Dept dept,Long deptId){
 restTemplate.put("http://5/User/{id}/Dept/setDept",dept,deptId);
 return ;
}


public Dept getDept(Long deptId){
 Dept aux = restTemplate.getForObject("http://5/User/{id}/Dept/getDept",Dept.class,deptId);
return aux;
}


}