package com.circle.dao.comment.impl;
 import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.circle.constant.CircleTable;
import com.circle.dao.comment.ICommentDao;
import com.circle.pojo.comment.Comment;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.Interface.ICommonDao;
@Repository
public class CommentDaoImpl implements ICommentDao{

 public  String SAVE_COMMENT_SQL;

@Resource
 private ICommonDao commonDao;


@Override
public boolean saveComment(Comment comment){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("orderId", comment.getOrderId());
    paramMap.put("userId", comment.getUserId());
    paramMap.put("circleId", comment.getCircleId());
    paramMap.put("orderDetailId", comment.getOrderDetailId());
    paramMap.put("commentText", comment.getCommentText());
    paramMap.put("serverCommentText", comment.getServerCommentText());
    paramMap.put("describeScore", comment.getDescribeScore());
    paramMap.put("serverScore", comment.getServerScore());
    paramMap.put("shipScore", comment.getShipScore());
    paramMap.put("userName", comment.getUserName());
    paramMap.put("goodId", comment.getGoodId());
    return commonDao.update(SAVE_COMMENT_SQL, paramMap) > 0 ? true : false;
}


}