package com.weflors.repository;
 import com.weflors.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
public interface UserRepository extends JpaRepository<UserEntity, Integer>{


@Modifying
@Transactional
@Query("update UserEntity set eMail = :userEmail, userName = :userName, user_lastname = :userLastName," + " login = :userLogin, phone = :userPhone  where userId = :userId")
public void updateUserInformation(String userEmail,String userName,String userLastName,String userLogin,String userPhone,Integer userId)
;

@Query("select b from UserEntity b where b.userId = :userId")
public UserEntity existById(Integer userId)
;

// hql
@Query("select b from UserEntity b where b.login = :login")
public UserEntity findByUsername(String username)
;

@Query("select b from UserEntity b where b.login = :login and b.eMail = :eMail")
public Optional<UserEntity> findUserByLoginAndEmail(String login,String eMail)
;

@Modifying
@Transactional
@Query("delete from UserEntity where userId = :userId")
public void deleteByUserId(Integer userId)
;

}