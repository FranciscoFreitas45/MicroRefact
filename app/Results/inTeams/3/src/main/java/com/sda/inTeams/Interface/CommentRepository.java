package com.sda.inTeams.Interface;
public interface CommentRepository {

   public Object saveAll(Object Object);
   public List<Comment> findAllByTask(Task task);
}