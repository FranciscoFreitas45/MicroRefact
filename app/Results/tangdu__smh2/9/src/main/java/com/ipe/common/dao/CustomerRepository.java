package com.ipe.common.dao;
 import com.ipe.common.entity.IDEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import java.io.Serializable;
@NoRepositoryBean
public interface CustomerRepository extends JpaSpecificationExecutor<T>, JpaRepository<T, PK>{


}