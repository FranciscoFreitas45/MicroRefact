package com.zammc.repository;
 import com.zammc.domain.user.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends JpaSpecificationExecutor<UserInfoEntity>, JpaRepository<UserInfoEntity, String>{


}