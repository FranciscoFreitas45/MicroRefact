package com.gs.dao;
 import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SalaryDAO extends BaseDAO<String, Salary>{


public User queryBySalary(String userId)
;

public void addInsert(List<Salary> salarys)
;

public int countByBlurred(Salary salary,User user)
;

public List<Salary> querySalary(Pager pager,String start,String end)
;

public int countSalary(User user,String start,String end)
;

public List<Salary> blurredQuery(Pager pager,Salary salary)
;

}