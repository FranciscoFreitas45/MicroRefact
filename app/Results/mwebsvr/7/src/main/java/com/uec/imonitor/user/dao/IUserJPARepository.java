package com.uec.imonitor.user.dao;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uec.imonitor.user.bean.UserEntity;
public interface IUserJPARepository extends JpaRepository<UserEntity, Integer>{


@Query("from UserEntity a where a.userName = :userName")
public UserEntity findByUserName(String userName)
;

@Query("from UserEntity a where a.userName like %:queryStr% or a.name like %:queryStr%")
public Page<UserEntity> QueryByName(String queryStr,Pageable pageable)
;

@Query("from UserEntity a where a.userName like %:queryStr% or a.name like %:queryStr%")
public List<UserEntity> findByName(String queryStr)
;

}