package com.vino.scaffold.repository.base;
 import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import com.vino.scaffold.entity.base.BaseEntity;
@NoRepositoryBean
public interface BaseRepository extends JpaSpecificationExecutor<T>, JpaRepository<T, PK>{


public Page<T> findAll(Pageable pageable)
;

}