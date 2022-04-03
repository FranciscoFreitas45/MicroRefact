package com.sobey.cmop.mvc.Interface;
public interface RedmineIssueDao {

   public Object findOne(Object Object);
   public RedmineIssue findByIssueId(Integer issueId);
   public Object save(Object Object);
   public Object findAll(Object Object);
}