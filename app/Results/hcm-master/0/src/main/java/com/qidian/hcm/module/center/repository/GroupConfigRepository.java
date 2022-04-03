package com.qidian.hcm.module.center.repository;
 import com.qidian.hcm.module.center.entity.GroupConfig;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GroupConfigRepository extends JpaRepository<GroupConfig, Long>{


public GroupConfig findByGroupId(Long groupId)
;

public GroupConfig findByTenantName(String tenentName)
;

}