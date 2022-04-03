package com.dtdhehe.ptulife.service.impl;
 import com.dtdhehe.ptulife.entity.Comment;
import com.dtdhehe.ptulife.repository.CommentRepository;
import com.dtdhehe.ptulife.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService{

@Autowired
 private  CommentRepository commentRepository;


@Override
public Comment add(Comment comment){
    return commentRepository.save(comment);
}


@Override
public List<Comment> queryByPidAndPidIsNotNull(String id,Sort sort){
    return commentRepository.queryByPidAndPidIsNotNull(id, sort);
}


@Override
public Integer del(Comment comment){
    Integer result = 0;
    try {
        commentRepository.delete(comment);
        result = 1;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}


@Override
public List<Comment> queryByPidIsNull(String postId,Sort sort){
    return commentRepository.queryByPostIdAndPidIsNull(postId, sort);
}


@Override
public Integer getHotByComment(String pid){
    Integer count = 60;
    List<Comment> commentList = commentRepository.queryByPostId(pid);
    int size = commentList.size();
    if (size <= 2) {
        int currentCount = 3 * size;
        count = count + currentCount;
    } else if (size <= 5) {
        int currentCount = 4 * size;
        count = count + currentCount;
    } else {
        int currentCount = 5 * size;
        count = count + currentCount;
    }
    return count;
}


}