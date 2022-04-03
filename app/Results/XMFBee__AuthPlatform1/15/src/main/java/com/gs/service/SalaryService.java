package com.gs.service;
 import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import java.util.List;
public interface SalaryService extends BaseService<String, Salary>{


public User queryBySalary(String userId)
;

public boolean addInsert(List<Salary> salarys)
;

public List<Salary> querySalary(Pager pager,String start,String end)
;

public int countSalary(User user,String start,String end)
;

}