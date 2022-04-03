package com.cg.sprint.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cg.sprint.entity.Account;
public interface AccountDao extends JpaRepository<Account, Integer>{


@Query("select u from Account u where username=?1 and password=?2 ")
public Account getUser(String uName,String uPass)
;

@Query("select a from Account a where username=?1 and password=?2 ")
public Account validate(String uname,String pass)
;

}