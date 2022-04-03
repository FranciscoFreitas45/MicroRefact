package com.hmm.employee.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.employee.entity.Employee;
@Repository
public interface EmployeeDao extends PagingAndSortingRepository<Employee, Integer>, JpaSpecificationExecutor<Employee>{


@Modifying
@Query("update Employee e set e.password = ?1 where e.userName = ?2")
public void updatePassword(String password,String userName)
;

public Employee findByEmpName(String empName)
;

public Employee findByUserName(String userName)
;

@Query("from Employee o where o.empName = ?1 and o.empNo = ?2")
public Employee findByEmpNameAndEmpNo(String empName,String empNo)
;

@Query("from Employee o where o.empNo = ?1")
public Employee findByEmpNo(String empNo)
;

public void setEmploy(Integer emp_idR9B4,Employee employ);

public Employee getEmploy(Integer emp_idR9B4);

public void setEmploy(Integer emp_idB9PD,Employee employ);

public Employee getEmploy(Integer emp_idB9PD);

public void setEmployee(Integer dept_id,Set<Employee> employ);

public Set<Employee> getEmployee(Integer dept_id);

public void setEmployee(Integer emp_idIGL6,Employee employee);

public Employee getEmployee(Integer emp_idIGL6);

public void setEmploy(Integer emp_idPBMQ,Employee employ);

public Employee getEmploy(Integer emp_idPBMQ);

public Employee getEmploy(Integer emp_idSDYK);

public void setEmploy(Integer emp_idSDYK,Employee employ);

public Employee getEmploy(Integer emp_idOBPL);

public void setEmploy(Integer emp_idOBPL,Employee employ);

public Employee getWorker(Integer emp_id1OFM);

public void setWorker(Integer emp_id1OFM,Employee worker);

public void setSendWorker(Integer emp_idK9MW,Employee sendWorker);

public Employee getSendWorker(Integer emp_idK9MW);

public void setRoomWorker(Integer emp_idU6JN,Employee roomWorker);

public Employee getRoomWorker(Integer emp_idU6JN);

public void setEmployee(Integer emp_idWJ6N,Employee employee);

public Employee getEmployee(Integer emp_idWJ6N);

public void setEmployee(Integer emp_idQ9YD,Employee employee);

public Employee getEmployee(Integer emp_idQ9YD);

public void setEmpName(Integer id,String empName);

public void setEmpSex(Integer id,String empSex);

public void setJobtype(Integer id,String jobtype);

public void setTel(Integer id,String tel);

public void setAddress(Integer id,String address);

public void setIdcard(Integer id,String idcard);

public void setEntryDate(Integer id,Date entryDate);

public void setIntroduce(Integer id,String introduce);

public void setGroupRoles(Integer id,List<GroupRole> groupRoles);

public void setDepartmentes(Integer id,Department departmentes);

public void setEmpNo(Integer id,String empNo);

public void setUserName(Integer id,String userName);

public void setEmpImage(Integer id,String empImage);

}