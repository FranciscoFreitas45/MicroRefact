package com.fzshuai.service;
 import com.fzshuai.po.Comment;
import java.util.List;
public interface CommentService {


public Comment saveComment(Comment comment)
;

public List<Comment> listCommentByBlogId(Long blogId)
;

}