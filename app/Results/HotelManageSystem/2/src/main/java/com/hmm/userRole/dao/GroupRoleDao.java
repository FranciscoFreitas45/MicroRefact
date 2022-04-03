package com.hmm.userRole.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.userRole.entity.GroupRole;
@Repository
public interface GroupRoleDao extends JpaSpecificationExecutor<GroupRole>, PagingAndSortingRepository<GroupRole, Integer>{


@Query("from GroupRole o where o.groupId = ?1")
public GroupRole findByGroupId(String groupId)
;

@Query("from GroupRole o where o.groupName = ?1")
public GroupRole findByGroupName(String groupName)
;

}