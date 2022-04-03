package com.fzshuai.service.impl;
 import com.fzshuai.dao.CommentRepository;
import com.fzshuai.po.Comment;
import com.fzshuai.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService{

@Autowired
 private  CommentRepository commentRepository;

 private  List<Comment> tempReplys;


public List<Comment> eachComment(List<Comment> comments){
    List<Comment> commentsView = new ArrayList<>();
    for (Comment comment : comments) {
        Comment c = new Comment();
        BeanUtils.copyProperties(comment, c);
        commentsView.add(c);
    }
    // 合并评论的各层子代到第一级子代集合中
    combineChildren(commentsView);
    return commentsView;
}


@Transactional
@Override
public Comment saveComment(Comment comment){
    Long parentCommentId = comment.getParentComment().getId();
    // 首先看是不是一级评论
    if (parentCommentId != -1) {
        // 如果不是，还需要找到它的父级评论，然后设置parentComment字段的值
        comment.setParentComment(commentRepository.findById(parentCommentId).orElse(null));
    } else {
        // 否则无需设置
        comment.setParentComment(null);
    }
    // 最后设置评论时间，保存评论
    comment.setCreateTime(new Date());
    return commentRepository.save(comment);
}


@Override
public List<Comment> listCommentByBlogId(Long blogId){
    Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
    // 首先根据博客id和指定的排序方式从数据库中找到对应的评论
    List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
    // 最后分级显示
    return eachComment(comments);
}


public void recursively(Comment comment){
    // 顶节点添加到临时存放集合
    tempReplys.add(comment);
    if (comment.getReplyComments().size() > 0) {
        List<Comment> replys = comment.getReplyComments();
        for (Comment reply : replys) {
            tempReplys.add(reply);
            if (reply.getReplyComments().size() > 0) {
                recursively(reply);
            }
        }
    }
}


public void combineChildren(List<Comment> comments){
    for (Comment comment : comments) {
        List<Comment> replys1 = comment.getReplyComments();
        for (Comment reply1 : replys1) {
            // 循环迭代，找出子代，存放在tempReplys中
            recursively(reply1);
        }
        // 修改顶级节点的reply集合为迭代处理后的集合
        comment.setReplyComments(tempReplys);
        // 清除临时存放区
        tempReplys = new ArrayList<>();
    }
}


}