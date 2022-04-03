package com.sda.inTeams.Interface;
public interface CommentRepository {

   public List<Comment> findAllByCreator(User user);
}