package com.dtdhehe.ptulife.scheduler;
 import com.dtdhehe.ptulife.entity.Comment;
import com.dtdhehe.ptulife.entity.HotLabel;
import com.dtdhehe.ptulife.entity.PtuAnswer;
import com.dtdhehe.ptulife.entity.PtuNews;
import com.dtdhehe.ptulife.service.AnswerService;
import com.dtdhehe.ptulife.service.CommentService;
import com.dtdhehe.ptulife.service.LabelService;
import com.dtdhehe.ptulife.service.NewsService;
import com.dtdhehe.ptulife.util.DateUtils;
import com.dtdhehe.ptulife.util.KeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import java.util.Iterator;
import java.util.List;
import com.dtdhehe.ptulife.Interface.NewsService;
import com.dtdhehe.ptulife.Interface.AnswerService;
import com.dtdhehe.ptulife.Interface.CommentService;
@Component
public class HotLabelScheduler {

 private  Logger logger;

@Autowired
 private  NewsService newsService;

@Autowired
 private  AnswerService answerService;

@Autowired
 private  LabelService labelService;

@Autowired
 private  CommentService commentService;


public void testTasks(){
    System.out.println("定时任务时间:" + DateUtils.getCurrentDateTime2());
}


@Transactional(rollbackFor = Exception.class)
public void updateHotLabel(){
    logger.info("开始更新热门标签");
    // 查询当前所有热门标签
    List<HotLabel> labelList = labelService.findAllLabel();
    try {
        // 先查询所有的新闻
        logger.info("查询全部新闻");
        List<PtuNews> newsList = newsService.queryAllNews();
        logger.info("查询到" + newsList.size() + "条记录");
        Iterator<PtuNews> newsIt = newsList.iterator();
        while (newsIt.hasNext()) {
            PtuNews news = newsIt.next();
            Iterator<HotLabel> labelIt = labelList.iterator();
            while (labelIt.hasNext()) {
                HotLabel label = labelIt.next();
                if (news.getNewsId().equals(label.getPostId())) {
                    // 如果相等，则更新热度
                    Integer hot = commentService.getHotByComment(news.getNewsId());
                    label.setLabelHot(hot);
                    labelService.save(label);
                }
            }
        }
        // 查询所有问答
        logger.info("查询所有问答");
        List<PtuAnswer> answerList = answerService.queryAllAnswer();
        logger.info("查询到" + answerList.size() + "条记录");
        Iterator<PtuAnswer> answerIt = answerList.iterator();
        while (answerIt.hasNext()) {
            PtuAnswer answer = answerIt.next();
            Iterator<HotLabel> labelIt = labelList.iterator();
            while (labelIt.hasNext()) {
                HotLabel label = labelIt.next();
                if (answer.getAnswerId().equals(label.getPostId())) {
                    // 如果相等，则更新热度
                    Integer hot = commentService.getHotByComment(answer.getAnswerId());
                    label.setLabelHot(hot);
                    labelService.save(label);
                }
            }
        }
    } catch (Exception e) {
        logger.error("更新失败");
        logger.error(e.getMessage());
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    } finally {
        logger.info("结束更新热门标签");
    }
}


}