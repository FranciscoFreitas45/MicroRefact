package cn.gson.oasys.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.dao.user.DeptDao;
import cn.gson.oasys.model.entity.user.Dept;
@Service
public class DeptUserService {

@Autowired
 private DeptDao deptdao;


public void setDept(Long deptId,Dept dept){
deptdao.setDept(deptId,dept);
}


public Dept getDept(Long deptId){
return deptdao.getDept(deptId);
}


}