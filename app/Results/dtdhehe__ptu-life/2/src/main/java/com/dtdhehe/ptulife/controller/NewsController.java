package com.dtdhehe.ptulife.controller;
 import com.alibaba.fastjson.JSONObject;
import com.dtdhehe.ptulife.entity.PtuNews;
import com.dtdhehe.ptulife.entity.PtuUser;
import com.dtdhehe.ptulife.service.LabelService;
import com.dtdhehe.ptulife.service.NewsService;
import com.dtdhehe.ptulife.service.UserService;
import com.dtdhehe.ptulife.util.CheckUserUtils;
import com.dtdhehe.ptulife.util.DateUtils;
import com.dtdhehe.ptulife.util.KeyUtils;
import com.dtdhehe.ptulife.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import com.dtdhehe.ptulife.Interface.UserService;
import com.dtdhehe.ptulife.Interface.LabelService;
import com.dtdhehe.ptulife.DTO.PtuUser;
import com.dtdhehe.ptulife.DTO.ResultVO;
@Controller
@RequestMapping("/news/newsController")
public class NewsController {

 private  Logger logger;

@Autowired
 private  NewsService newsService;

@Autowired
 private  UserService userService;

@Autowired
 private  LabelService labelService;


@RequestMapping("/newsPage")
public String newsPage(String newsId,Model model,HttpServletRequest request){
    if (StringUtils.isEmpty(newsId)) {
        throw new Exception("传入的id为空");
    }
    logger.info("查询的新闻id为:" + newsId);
    PtuNews ptuNews = newsService.queryNewsById(newsId);
    ptuNews.setNewsDate(DateUtils.date2ViewType(ptuNews.getNewsDate()));
    // 查出当前登录的用户
    PtuUser user = userService.findOne(request);
    // 查出该新闻的作者
    PtuUser ptuUser = userService.findByUserId(ptuNews.getUserId());
    String userStatusStr = CheckUserUtils.checkUserStatus(ptuUser.getUserStatus());
    model.addAttribute("ptuNews", ptuNews);
    model.addAttribute("currentUser", ptuUser);
    model.addAttribute("userStatusStr", userStatusStr);
    model.addAttribute("user", user);
    return "news/newspage";
}


@RequestMapping("/saveNews")
@ResponseBody
public ResultVO saveNews(String newsObject){
    PtuNews news = JSONObject.parseObject(newsObject, PtuNews.class);
    logger.info("开始保存新闻");
    ResultVO resultVO = new ResultVO();
    news.setNewsId(KeyUtils.getUniqueKey());
    news.setNewsDate(DateUtils.getCurrentDateTime());
    // 判断是否有上传封面,若未上传，指定默认封面
    if (StringUtils.isEmpty(news.getNewsIcon())) {
        news.setNewsIcon("/uploads/2019042217362536default.jpg");
    }
    try {
        PtuNews newsNew = newsService.save(news);
        if (newsNew != null) {
            logger.info("新闻保存成功");
            resultVO.setStatus("0");
            resultVO.setObject(newsNew);
            // 新闻保存成功，同时添加记录到hot表
            labelService.save(newsNew, newsNew.getClass());
        } else {
            logger.error("新闻保存失败");
            resultVO.setStatus("1");
            // 保存新闻  2297
            resultVO.setError_code("2297");
            resultVO.setError_msg("newsNew对象为空");
        }
    } catch (Exception e) {
        logger.error(e.getMessage());
        resultVO.setStatus("1");
        // 保存新闻  2297
        resultVO.setError_code("2297");
        resultVO.setError_msg(e.getMessage());
    }
    return resultVO;
}


@RequestMapping("/queryAllNews")
@ResponseBody
public ResultVO queryAllNews(Integer page,Integer size){
    logger.info("查询所有新闻");
    ResultVO resultVO = new ResultVO();
    List<Map<String, Object>> mapList;
    try {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "newsDate");
        // 分页查询新闻列表
        Page<Map<String, Object>> ptuNews = newsService.findAllWithHead(pageable);
        if (ptuNews.isLast()) {
            resultVO.setError_msg("最后一页啦");
        } else {
            resultVO.setError_msg("");
        }
        // 获得新闻List
        mapList = ptuNews.getContent();
        resultVO.setStatus("0");
        resultVO.setObject(mapList);
    } catch (Exception e) {
        logger.error(e.getMessage());
        resultVO.setStatus("1");
        // 新闻列表  9952
        resultVO.setError_code("9952");
        resultVO.setError_msg(e.getMessage());
    }
    return resultVO;
}


@RequestMapping("/editNews")
public String editNews(HttpServletRequest request,Model model){
    // 查出当前登录用户
    PtuUser ptuUser = userService.findOne(request);
    String userStatusStr = CheckUserUtils.checkUserStatus(ptuUser.getUserStatus());
    model.addAttribute("date", DateUtils.getCurrentDate());
    model.addAttribute("currentUser", ptuUser);
    model.addAttribute("userStatusStr", userStatusStr);
    return "news/editNews";
}


}