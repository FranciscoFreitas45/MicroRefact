package com.sobey.cmop.mvc.dao;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.RedmineIssue;
public interface RedmineIssueDao extends JpaSpecificationExecutor<RedmineIssue>, PagingAndSortingRepository<RedmineIssue, Integer>{


public RedmineIssue findByIssueId(Integer issueId)
;

}