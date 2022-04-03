package com.example.steam.controller;
 import com.alibaba.fastjson.JSON;
import com.example.steam.entity.Comment;
import com.example.steam.service.CommentService;
import com.example.steam.utils.ResultMsg;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
@Controller
public class CommentController {

 private Logger log;

@Autowired
 private CommentService commentService;


@ResponseBody
@RequestMapping("/comment/{id}")
public String findOneCommentById(long id){
    return JSON.toJSONString(ResultMsg.SUCCESS(commentService.findOneCommentById(id)));
}


@ResponseBody
@RequestMapping("/comment/delete/{commentId}")
public String deleteComment(long commentId){
    return JSON.toJSONString(ResultMsg.SUCCESS(commentService.deleteComment(commentId)));
}


@ResponseBody
@RequestMapping("/comment/sum/{email}")
public String findCommentSumByEmail(String email){
    return JSON.toJSONString(ResultMsg.SUCCESS(commentService.findCommentSumByEmail(email)));
}


@ResponseBody
@RequestMapping("/comment/num/{gameId}")
public String findCommentNumberByGameId(long gameId){
    long start = System.currentTimeMillis();
    long num = commentService.findCommentListNumberByGameId(gameId).size();
    long end = System.currentTimeMillis();
    log.error(end - start + "");
    return JSON.toJSONString(ResultMsg.SUCCESS(num));
}


@ResponseBody
@RequestMapping("/commentDetail/{gameId}/time/{index}")
public String findComentDetailByIndexTime(long gameId,long index){
    return JSON.toJSONString(commentService.findRangeCommentDetailByTime(index, gameId));
}


@ResponseBody
@RequestMapping("/commentDetail/{gameId}/zan/{index}")
public String findComentDetailByIndexZan(long gameId,long index){
    return JSON.toJSONString(commentService.findRangeCommentDetailByZanNum(index, gameId));
}


@ResponseBody
@RequestMapping("/comment/zan/{commentId}")
public String addZan(long commentId){
    return JSON.toJSONString(ResultMsg.SUCCESS(commentService.addZanComment(commentId)));
}


@ResponseBody
@RequestMapping("/comment/statu/{gameId}")
public String findCommentStatuByGameId(long gameId){
    List<Long> list = commentService.findCommentDescriptionNumberByGameId(gameId);
    return JSON.toJSONString(ResultMsg.SUCCESS(commentService.getCommentStatu(list.get(0), list.get(1))));
}


@ResponseBody
@RequestMapping("/comment/happy/{commentId}")
public String addHappy(long commentId){
    return JSON.toJSONString(ResultMsg.SUCCESS(commentService.addHappyComment(commentId)));
}


@ResponseBody
@RequestMapping("/comment/showallcomment")
public String showRecentCommentByEmail(long page,String email){
    return JSON.toJSONString(ResultMsg.SUCCESS(commentService.findAllCommentByUserEmailOrderByTimeDesc(email, page)));
}


@ResponseBody
@RequestMapping("/comment/add")
public String addComment(String content,String email,int gameId,int recommendStatu){
    Comment comment = new Comment(content, email, gameId, recommendStatu);
    return JSON.toJSONString(commentService.addComment(comment));
}


@ResponseBody
@RequestMapping("/comment/edit/content")
public String updateCommentContent(long commentId,String content){
    return JSON.toJSONString(commentService.updateCommentContent(commentId, content));
}


@ResponseBody
@RequestMapping("/comment/cai/{commentId}")
public String addCai(long commentId){
    return JSON.toJSONString(ResultMsg.SUCCESS(commentService.addCaiComment(commentId)));
}


}