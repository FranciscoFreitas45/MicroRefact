package org.live.common.base;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import java.io.Serializable;
@NoRepositoryBean
public interface BaseRepository extends JpaSpecificationExecutor<T>, JpaRepository<T, ID>{


}