package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.User;
public interface UserDao extends JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, Integer>{


public User findByRedmineUserId(Integer redmineUserId)
;

public Page<User> findAllByNameLike(String name,Pageable pageable)
;

public User findByLoginName(String loginName)
;

public User findByEmail(String email)
;

public List<User> findByType(Integer type)
;

}