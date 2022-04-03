package com.example.steam.service;
 import com.alibaba.fastjson.JSON;
import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.CommentDao;
import com.example.steam.entity.Comment;
import com.example.steam.entity.User;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.CommentKey;
import com.example.steam.utils;
import com.example.steam.vo.CommentDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.SensitiveWordService;
import com.example.steam.Interface.UserService;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.UserGameService;
import com.example.steam.Interface.SensitiveWordUtil;
import com.example.steam.DTO.User;
import com.example.steam.DTO.ResultMsg;
import com.example.steam.DTO.RankScoreValue;
@Service
public class CommentService implements InitializingBean{

 private  long RECENT_CONMENT_SIZE;

 private  long POPULAR_COMMENT_SIZE;

 private Logger log;

@Autowired
 private CommentDao commentDao;

@Autowired
 private RedisService redisService;

@Autowired
 private SensitiveWordService sensitiveWordService;

@Autowired
 private UserService userService;

@Autowired
 private ImageService imageService;

@Autowired
 private UserGameService userGameService;

@Autowired
 private GameService gameService;

@Autowired
 private SensitiveWordUtil sensitiveWordUtil;

@Autowired
 private ApplicationContext applicationContext;


public Comment findOneCommentById(long id){
    Comment comment = redisService.get(CommentKey.COMMENT_ID, id + "", Comment.class);
    if (comment != null) {
        return comment;
    }
    comment = commentDao.findOneCommentById(id);
    if (comment == null) {
        return null;
    }
    redisService.set(CommentKey.COMMENT_ID, id + "", comment);
    return comment;
}


public int deleteComment(long commentId){
    Comment comment = redisService.get(CommentKey.COMMENT_ID, commentId + "", Comment.class);
    if (comment == null) {
        return -1;
    }
    int result = commentDao.deleteComment(commentId);
    redisService.del(CommentKey.COMMENT_ID, commentId + "");
    CommentRank commentRank = new CommentRank();
    commentRank.setId(commentId);
    commentRank.setGameId(comment.getGameId());
    redisService.zrem(CommentKey.COMMENT_RANK_TIME, CommentKey.COMMENT_RANK_TIME_KEY, commentRank);
    redisService.zrem(CommentKey.COMMENT_RANK_ZANNUM, CommentKey.COMMENT_RANK_ZANNUM_KEY, commentRank);
    return result;
}


public int addZanComment(long commentId){
    Comment comment = redisService.get(CommentKey.COMMENT_ID, commentId + "", Comment.class);
    comment.setZanNum(comment.getZanNum() + 1);
    CommentRank commentRank = new CommentRank(comment.getId(), comment.getGameId());
    double x = redisService.zincr(CommentKey.COMMENT_RANK_ZANNUM, CommentKey.COMMENT_RANK_ZANNUM_KEY, commentRank);
    log.error(x + "");
    int result = ((CommentService) applicationContext.getBean("commentService")).updateComment(comment);
    return result;
}


public int findCommentSumByEmail(String email){
    return userService.findByEmail(email).getCommentNum();
}


public List<Long> findCommentListNumberByGameId(long gameId){
    // long count=0;
    long cursor = 0;
    List<CommentDetail> commentDetailList = new LinkedList<>();
    List<Long> commentIdList = new LinkedList<>();
    int sum = ((CommentService) applicationContext.getBean("commentService")).findCommentSum();
    while (cursor < sum) {
        Set<CommentRank> commentRankSet = redisService.zrange(CommentKey.COMMENT_RANK_TIME, CommentKey.COMMENT_RANK_TIME_KEY, cursor, cursor + 100, CommentRank.class);
        Iterator<CommentRank> iterator = commentRankSet.iterator();
        while (iterator.hasNext()) {
            CommentRank commentRank = iterator.next();
            if (commentRank.getGameId() == gameId) {
                // count++;
                commentIdList.add(commentRank.getId());
            }
        }
        cursor += 100;
    }
    return commentIdList;
}


public CommentDetail findCommentDetailById(long id){
    Comment comment = ((CommentService) applicationContext.getBean("commentService")).findOneCommentById(id);
    User user = userService.findByEmail(comment.getEmail());
    CommentDetail commentDetail = new CommentDetail();
    commentDetail.setAvatar(imageService.findImageUrlById(user.getAvatar()));
    commentDetail.setBuyGames(user.getBuyGames());
    commentDetail.setCaiNum(comment.getCaiNum());
    commentDetail.setZanNum(comment.getZanNum());
    commentDetail.setCommentDate(comment.getCommentDate());
    commentDetail.setCommmentNum(user.getCommentNum());
    commentDetail.setContent(comment.getContent());
    commentDetail.setNickName(user.getNickName());
    commentDetail.setEmail(user.getEmail());
    commentDetail.setPlayTime(user.getPlayTime());
    commentDetail.setRecommendStatu(comment.getRecommendStatu());
    commentDetail.setId(comment.getId());
    commentDetail.setUserId(user.getId());
    commentDetail.setHappyNum(comment.getHappy());
    commentDetail.setGameId(comment.getGameId());
    return commentDetail;
}


public List<CommentDetail> findAllCommentByUserEmailOrderByTimeDesc(String email,long page){
    long start = page * POPULAR_COMMENT_SIZE;
    long end = (page + 1) * POPULAR_COMMENT_SIZE;
    List<CommentDetail> commentDetailList = new LinkedList<>();
    List<Comment> commentList = commentDao.findComentsByEmailOrderByTimeDesc(start, end, email);
    for (Comment comment : commentList) {
        CommentDetail commentDetail = new CommentDetail();
        commentDetail.setGameId(comment.getGameId());
        commentDetail.setPlayTime(userGameService.findOneUserGameByEmailAndGameId(email, comment.getGameId()).getPlayTime());
        commentDetail.setRecommendStatu(comment.getRecommendStatu());
        commentDetail.setContent(comment.getContent());
        commentDetail.setAvatar(gameService.findGameById(comment.getGameId()).getPosterImage());
        commentDetail.setCommentDate(comment.getCommentDate());
        commentDetail.setZanNum(comment.getZanNum());
        commentDetailList.add(commentDetail);
    }
    return commentDetailList;
}


public List<CommentDetail> findALlCommentDetailByTime(){
    long cursor = 0;
    List<CommentDetail> commentDetailList = new LinkedList<>();
    int sum = ((CommentService) applicationContext.getBean("commentService")).findCommentSum();
    while (cursor < sum) {
        Set<CommentRank> commentRankSet = redisService.zrange(CommentKey.COMMENT_RANK_TIME, CommentKey.COMMENT_RANK_TIME_KEY, cursor, cursor + 100, CommentRank.class);
        Iterator<CommentRank> iterator = commentRankSet.iterator();
        while (iterator.hasNext()) {
            CommentRank commentRank = iterator.next();
            CommentDetail commentDetail = ((CommentService) applicationContext.getBean("commentService")).findCommentDetailById(commentRank.getId());
            commentDetailList.add(commentDetail);
        }
        cursor += 100;
    }
    return commentDetailList;
}


public int updateComment(Comment comment){
    redisService.set(CommentKey.COMMENT_ID, comment.getId() + "", comment);
    return commentDao.updateComment(comment);
}


public String getCommentStatu(long goodCommentNum,long unGoodCommentNum){
    double statu = (double) goodCommentNum / (double) (goodCommentNum + unGoodCommentNum);
    if (goodCommentNum == 0 && unGoodCommentNum == 0) {
        return "无评测";
    }
    if (statu >= 0.8) {
        return "好评如潮";
    } else if (statu >= 0.6 && statu < 0.8) {
        return "好评较多";
    } else if (statu >= 0.4 && statu < 0.6) {
        return "褒贬不一";
    } else {
        return "差评较多";
    }
}


public ResultMsg findRangeCommentDetailByTime(long page,long gameId){
    long count = 0;
    long cursor = 0;
    List<CommentDetail> commentDetailList = new LinkedList<>();
    int sum = ((CommentService) applicationContext.getBean("commentService")).findCommentSum();
    while (cursor < sum) {
        Set<CommentRank> commentRankSet = redisService.zrange(CommentKey.COMMENT_RANK_TIME, CommentKey.COMMENT_RANK_TIME_KEY, cursor, cursor + 100, CommentRank.class);
        Iterator<CommentRank> iterator = commentRankSet.iterator();
        while (iterator.hasNext()) {
            CommentRank commentRank = iterator.next();
            if (commentRank.getGameId() == gameId && commentDetailList.size() < RECENT_CONMENT_SIZE) {
                if (count >= page * RECENT_CONMENT_SIZE && count < page * RECENT_CONMENT_SIZE + RECENT_CONMENT_SIZE) {
                    CommentDetail commentDetail = ((CommentService) applicationContext.getBean("commentService")).findCommentDetailById(commentRank.getId());
                    commentDetailList.add(commentDetail);
                }
                count++;
            }
        }
        cursor += 100;
    }
    return ResultMsg.SUCCESS(commentDetailList);
}


public List<Long> findCommentDescriptionNumberByGameId(long gameId){
    long goodCount = 0;
    long unGoodCount = 0;
    List<String> list = new LinkedList<>();
    List<Long> result = new LinkedList<>();
    List<Long> commentIdList = ((CommentService) applicationContext.getBean("commentService")).findCommentListNumberByGameId(gameId);
    for (Long id : commentIdList) {
        list.add(id + "");
    }
    List<Comment> commentList = redisService.getPipelineBatch(CommentKey.COMMENT_ID, list, Comment.class);
    for (Comment comment : commentList) {
        if (comment.getRecommendStatu() == 1) {
            goodCount++;
        } else {
            unGoodCount++;
        }
    }
    result.add(goodCount);
    result.add(unGoodCount);
    return result;
}


public ResultMsg findRangeCommentDetailByZanNum(long page,long gameId){
    long count = 0;
    long cursor = 0;
    List<CommentDetail> commentDetailList = new LinkedList<>();
    int sum = ((CommentService) applicationContext.getBean("commentService")).findCommentSum();
    while (cursor < sum) {
        Set<CommentRank> commentRankSet = redisService.zrange(CommentKey.COMMENT_RANK_ZANNUM, CommentKey.COMMENT_RANK_ZANNUM_KEY, cursor, cursor + 100, CommentRank.class);
        Iterator<CommentRank> iterator = commentRankSet.iterator();
        while (iterator.hasNext()) {
            CommentRank commentRank = iterator.next();
            if (commentRank.getGameId() == gameId && commentDetailList.size() < POPULAR_COMMENT_SIZE) {
                if (count >= page * POPULAR_COMMENT_SIZE && count < page * POPULAR_COMMENT_SIZE + POPULAR_COMMENT_SIZE) {
                    CommentDetail commentDetail = ((CommentService) applicationContext.getBean("commentService")).findCommentDetailById(commentRank.getId());
                    commentDetailList.add(commentDetail);
                }
                count++;
            }
        }
        cursor += 100;
    }
    return ResultMsg.SUCCESS(commentDetailList);
}


public int addHappyComment(long commentId){
    Comment comment = redisService.get(CommentKey.COMMENT_ID, commentId + "", Comment.class);
    comment.setHappy(comment.getHappy() + 1);
    int result = ((CommentService) applicationContext.getBean("commentService")).updateComment(comment);
    return result;
}


public int addCaiComment(long commentId){
    Comment comment = redisService.get(CommentKey.COMMENT_ID, commentId + "", Comment.class);
    comment.setCaiNum(comment.getCaiNum() + 1);
    int result = ((CommentService) applicationContext.getBean("commentService")).updateComment(comment);
    return result;
}


public int findCommentSum(){
    Integer sum = redisService.get(CommentKey.COMMENT_SUM, CommentKey.COMMENT_SUM_KEY, Integer.class);
    if (sum != null) {
        return sum;
    }
    sum = commentDao.commentSum();
    redisService.set(CommentKey.COMMENT_SUM, CommentKey.COMMENT_SUM_KEY, sum);
    return sum;
}


@Override
public void afterPropertiesSet(){
    sensitiveWordService.addSensitiveWord("傻子");
    sensitiveWordService.addSensitiveWord("傻子傻瓜");
    int sum = ((CommentService) applicationContext.getBean("commentService")).findCommentSum();
    log.error(sum + "");
    sum = 17;
    for (int i = 0; i < sum; i++) {
        Comment comment = ((CommentService) applicationContext.getBean("commentService")).findOneCommentById(i + 1);
        if (comment == null) {
            log.error("评论为空");
            continue;
        }
        CommentRank commentRank = new CommentRank();
        commentRank.setId(comment.getId());
        commentRank.setGameId(comment.getGameId());
        RankScoreValue zanRank = new RankScoreValue();
        zanRank.setScore(comment.getZanNum());
        zanRank.setValue(commentRank);
        RankScoreValue timeRank = new RankScoreValue();
        timeRank.setScore(comment.getCommentDate().getTime());
        timeRank.setValue(commentRank);
        redisService.zadd(CommentKey.COMMENT_RANK_ZANNUM, CommentKey.COMMENT_RANK_ZANNUM_KEY, zanRank);
        redisService.zadd(CommentKey.COMMENT_RANK_TIME, CommentKey.COMMENT_RANK_TIME_KEY, timeRank);
    }
}


public long findLastCommentId(){
    DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.MASTER);
    return commentDao.findLastCommentId();
}


@Transactional(rollbackFor = Exception.class)
public long addComment(Comment comment){
    try {
        comment.setContent(sensitiveWordUtil.replaceSensitiveWord(comment.getContent(), StaticField.SENSITIVE));
    } catch (IOException e) {
        log.info(e.getMessage());
    }
    commentDao.addComment(comment);
    userService.updateCommnetNum(comment.getEmail());
    /**
     * 加入缓存，以及排名
     */
    redisService.set(CommentKey.COMMENT_ID, comment.getId() + "", comment);
    redisService.incKey(CommentKey.COMMENT_SUM, CommentKey.COMMENT_SUM_KEY);
    CommentRank commentRank = new CommentRank();
    commentRank.setId(comment.getId());
    commentRank.setGameId(comment.getGameId());
    RankScoreValue zanRank = new RankScoreValue();
    zanRank.setScore(comment.getZanNum());
    zanRank.setValue(commentRank);
    RankScoreValue timeRank = new RankScoreValue();
    timeRank.setScore(comment.getCommentDate().getTime());
    timeRank.setValue(commentRank);
    redisService.zadd(CommentKey.COMMENT_RANK_ZANNUM, CommentKey.COMMENT_RANK_ZANNUM_KEY, zanRank);
    redisService.zadd(CommentKey.COMMENT_RANK_TIME, CommentKey.COMMENT_RANK_TIME_KEY, timeRank);
    return comment.getId();
}


public ResultMsg updateCommentContent(long commentId,String newContent){
    Comment comment = ((CommentService) applicationContext.getBean("commentService")).findOneCommentById(commentId);
    try {
        newContent = sensitiveWordUtil.replaceSensitiveWord(newContent, StaticField.SENSITIVE);
    } catch (IOException e) {
        log.info(e.getMessage());
    }
    comment.setContent(newContent);
    ((CommentService) applicationContext.getBean("commentService")).updateComment(comment);
    return ResultMsg.SUCCESS;
}


}