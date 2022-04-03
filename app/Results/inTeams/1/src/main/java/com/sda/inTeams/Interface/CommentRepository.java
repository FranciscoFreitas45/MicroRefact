package com.sda.inTeams.Interface;
import com.sda.inTeams.DTO.*;
import java.util.*;
public interface CommentRepository {

   public List<Comment> findAllByCreator(User user);
}