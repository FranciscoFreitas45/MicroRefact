package com.zammc.repository;
 import com.zammc.domain.activity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long>, JpaSpecificationExecutor<ActivityEntity>{


}