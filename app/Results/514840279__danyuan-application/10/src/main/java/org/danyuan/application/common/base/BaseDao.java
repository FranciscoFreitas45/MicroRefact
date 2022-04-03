package org.danyuan.application.common.base;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface BaseDao extends JpaSpecificationExecutor<T>, JpaRepository<T, String>{


}