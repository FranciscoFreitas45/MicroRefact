package com.gs.service.impl;
 import com.gs.bean.Checkin;
import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.dao.SalaryDAO;
import com.gs.service.SalaryService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.common.bean.Pager;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SalaryServiceImpl implements SalaryService{

@Resource
 private  SalaryDAO salaryDAO;


public List<Salary> queryByPagerDisable(Pager pager){
    return salaryDAO.queryByPagerDisable(pager);
}


public int batchInsert(List<Salary> list){
    return salaryDAO.batchInsert(list);
}


public int batchUpdate(List<Salary> list){
    return salaryDAO.batchUpdate(list);
}


public Salary query(Salary salary){
    return salaryDAO.query(salary);
}


public int count(User user){
    return salaryDAO.count(user);
}


public int insert(Salary salary){
    return salaryDAO.insert(salary);
}


public int update(Salary salary){
    return salaryDAO.update(salary);
}


public int active(String id){
    return salaryDAO.active(id);
}


@Override
public List<Salary> queryAll(String status){
    return salaryDAO.queryAll(status);
}


public List<Salary> blurredQuery(Pager pager,Salary salary){
    return salaryDAO.blurredQuery(pager, salary);
}


public int delete(Salary salary){
    return salaryDAO.delete(salary);
}


public int batchDelete(List<Salary> list){
    return salaryDAO.batchDelete(list);
}


@Override
public User queryBySalary(String userId){
    return salaryDAO.queryBySalary(userId);
}


public List<Salary> queryByStatus(String status){
    return salaryDAO.queryAll(status);
}


@Transactional
public boolean addInsert(List<Salary> salaries){
    boolean flag = false;
    if (salaries != null) {
        salaryDAO.addInsert(salaries);
        flag = true;
    }
    return flag;
}


public int inactive(String id){
    return salaryDAO.inactive(id);
}


public int countByBlurred(Salary salary){
    return 0;
}


@Override
public List<Salary> querySalary(Pager pager,String start,String end){
    return salaryDAO.querySalary(pager, start, end);
}


@Override
public int countSalary(User user,String start,String end){
    return salaryDAO.countSalary(user, start, end);
}


public int deleteById(String id){
    return salaryDAO.deleteById(id);
}


public int countByDisable(User user){
    return salaryDAO.countByDisable(user);
}


public Salary queryById(String id){
    return salaryDAO.queryById(id);
}


public List<Salary> queryByPager(Pager pager){
    return salaryDAO.queryByPager(pager);
}


}