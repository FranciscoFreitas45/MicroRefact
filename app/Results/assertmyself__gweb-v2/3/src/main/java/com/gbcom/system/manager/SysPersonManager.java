package com.gbcom.system.manager;
 import com.gbcom.system.daoservice.SysDeptService;
import com.gbcom.system.daoservice.SysPersonDeptService;
import com.gbcom.system.daoservice.SysPersonService;
import com.gbcom.system.domain.SysDept;
import com.gbcom.system.domain.SysPerson;
import com.gbcom.system.domain.SysPersonDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
@Service
public class SysPersonManager {

@Autowired
 private  SysPersonService sysPersonService;

@Autowired
 private  SysPersonDeptService sysPersonDeptService;

@Autowired
 private  SysDeptService sysDeptService;


public void move(Long id,boolean isMoveUp){
    @SuppressWarnings("unused")
    SysPerson bean = sysPersonService.get(id);
    // bean.getSysPersonDepts();
    Set<SysPersonDept> depts = null;
    SysPersonDept dept = null;
    if (depts != null && depts.size() > 0) {
        // 取得当前用户所在的部门
        for (SysPersonDept tmp : depts) {
            dept = tmp;
        }
        String hql = "from SysPersonDept where dept = " + dept.getDept().getId();
        if (isMoveUp) {
            // 上移，取得比当前用户orderNo小的用户
            hql += " and orderNo < " + dept.getOrderNo();
        } else {
            // 下移，取得比当前用户orderNo大的用户
            hql += " and orderNo > " + dept.getOrderNo();
        }
        hql += " order by orderNo desc";
        List<SysPersonDept> list = sysPersonDeptService.find(hql);
        if (list != null && list.size() > 0) {
            SysPersonDept swapDept = list.get(0);
            Long swapOrderNo = swapDept.getOrderNo();
            swapDept.setOrderNo(dept.getOrderNo());
            dept.setOrderNo(swapOrderNo);
            sysPersonDeptService.update(swapDept);
            sysPersonDeptService.update(dept);
        }
    }
}


public SysDept getDeptByPersonId(Long personId){
    if (personId != null) {
        SysPerson person = sysPersonService.get(personId);
        Set<SysPersonDept> sysPersonDepts = person.getSysPersonDepts();
        if (sysPersonDepts.size() > 0) {
            SysPersonDept next = sysPersonDepts.iterator().next();
            return next.getDept();
        }
    }
    return null;
}


@SuppressWarnings("unchecked")
public List<SysPerson> getPersonByDept(Long departId,Boolean isOver,Boolean distinct){
    StringBuffer hql = new StringBuffer("from SysPerson t left join " + "t.sysPersonDepts t1 where 1=1 ");
    // 默认查询未离职的用户
    if (isOver != null && isOver) {
        hql.append(" and t.isOver = ").append(isOver);
    } else {
        hql.append(" and (t.isOver = null or t.isOver =").append(isOver).append(")");
    }
    if (departId != null) {
        hql.append(" and t1.dept = ").append(departId);
    }
    if (distinct != null && distinct) {
        hql.append(" and t.id not in (select person from SysUser)");
    }
    hql.append(" order by code asc");
    List list = sysPersonService.find(hql.toString());
    List<SysPerson> ret = new ArrayList<SysPerson>();
    for (int i = 0; i < list.size(); i++) {
        Object[] obj = (Object[]) list.get(i);
        ret.add((SysPerson) obj[0]);
    }
    return ret;
}


public void save(SysPerson bean){
    // 保存人员信息
    sysPersonService.save(bean);
    Long deptId = null;
    // bean.getDeptId();
    if (deptId != null && deptId > 0) {
        SysDept dept = sysDeptService.get(deptId);
        SysPersonDept entity = new SysPersonDept();
        entity.setDept(dept);
        entity.setPerson(bean);
        sysPersonDeptService.save(entity);
        // 更新orderNo字段
        entity.setOrderNo(entity.getId());
        sysPersonDeptService.save(entity);
    }
}


@SuppressWarnings("null")
public void update(SysPerson bean){
    // 更新已有的人员部门信息
    // bean.getSysPersonDepts();
    Set<SysPersonDept> set = null;
    for (SysPersonDept tmp : set) {
        // tmp.setDept(sysDeptService.get(bean.getDeptId()));
        sysPersonDeptService.save(tmp);
    }
    sysPersonService.save(bean);
}


public void moveDown(Long id){
    this.move(id, false);
}


public List<SysPersonDept> getPersonDept(SysPerson person){
    String hql = "from SysPersonDept where person = " + person.getId();
    return sysPersonDeptService.find(hql);
}


@SuppressWarnings("null")
public void delete(Long id){
    @SuppressWarnings("unused")
    SysPerson bean = sysPersonService.get(id);
    // bean.getSysPersonDepts().iterator();
    Iterator<SysPersonDept> it = null;
    while (it.hasNext()) {
        SysPersonDept entity = it.next();
        sysPersonDeptService.delete(entity);
    }
    sysPersonService.delete(id);
}


public void moveUp(Long id){
    this.move(id, true);
}


}