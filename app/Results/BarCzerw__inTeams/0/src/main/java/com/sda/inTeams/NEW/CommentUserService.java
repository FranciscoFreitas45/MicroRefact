package com.sda.inTeams.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.repository.CommentRepository;
import com.sda.inTeams.model.Comment.Comment;
@Service
public class CommentUserService {

@Autowired
 private CommentRepository commentrepository;


}