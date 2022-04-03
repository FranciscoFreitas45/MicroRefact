package com.dtdhehe.ptulife.service;
 import com.dtdhehe.ptulife.entity.Comment;
import org.springframework.data.domain.Sort;
import java.util.List;
public interface CommentService {


public Comment add(Comment comment)
;

public List<Comment> queryByPidAndPidIsNotNull(String id,Sort sort)
;

public Integer del(Comment comment)
;

public List<Comment> queryByPidIsNull(String postId,Sort sort)
;

public Integer getHotByComment(String pid)
;

}