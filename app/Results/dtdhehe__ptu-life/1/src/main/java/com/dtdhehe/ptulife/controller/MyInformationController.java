package com.dtdhehe.ptulife.controller;
 import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dtdhehe.ptulife.entity.Market;
import com.dtdhehe.ptulife.entity.PtuAnswer;
import com.dtdhehe.ptulife.entity.PtuNews;
import com.dtdhehe.ptulife.entity.PtuUser;
import com.dtdhehe.ptulife.service;
import com.dtdhehe.ptulife.util.DateUtils;
import com.dtdhehe.ptulife.util.MyBeanUtils;
import com.dtdhehe.ptulife.util.PasswordUtils;
import com.dtdhehe.ptulife.util.RedisUtils;
import com.dtdhehe.ptulife.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util;
import com.dtdhehe.ptulife.Interface.NewsService;
import com.dtdhehe.ptulife.Interface.AnswerService;
import com.dtdhehe.ptulife.Interface.OrgCodeService;
import com.dtdhehe.ptulife.Interface.MarketService;
@Controller
@RequestMapping("/myInformationController")
public class MyInformationController {

 private  Logger logger;

@Autowired
 private  UserService userService;

@Autowired
 private  NewsService newsService;

@Autowired
 private  AnswerService answerService;

@Autowired
 private  OrgCodeService orgCodeService;

@Autowired
 private  MarketService marketService;

@Resource
 private  RedisUtils redisUtils;


@RequestMapping("/revisePassword")
@ResponseBody
public ResultVO revisePassword(HttpServletRequest request,String object){
    // 查出当前登录用户
    PtuUser ptuUser = userService.findOne(request);
    ResultVO resultVO = new ResultVO();
    JSONArray jsonArray = JSONArray.parseArray(object);
    Iterator<Object> it = jsonArray.iterator();
    Map<String, Object> mapObject = new HashMap<>();
    while (it.hasNext()) {
        JSONObject obj = (JSONObject) it.next();
        mapObject.put((String) obj.get("name"), obj.get("value"));
    }
    // 判断新密码不能为空
    if (StringUtils.isEmpty(mapObject.get("newPwd"))) {
        logger.info("新密码不能为空");
        resultVO.setError_msg("新密码不能为空");
        resultVO.setStatus("1");
        return resultVO;
    }
    String oldPwd = PasswordUtils.getPWD((String) mapObject.get("oldPwd"));
    String newPwd = PasswordUtils.getPWD((String) mapObject.get("newPwd"));
    String surePwd = PasswordUtils.getPWD((String) mapObject.get("surePwd"));
    // 判断原密码是否正确
    if (!oldPwd.equals(ptuUser.getUserPwd())) {
        logger.info("原密码错误");
        resultVO.setError_msg("原密码错误");
        resultVO.setStatus("1");
        return resultVO;
    }
    // 判断新密码两次输入是否一致
    if (!newPwd.equals(surePwd)) {
        logger.info("密码不一致");
        resultVO.setError_msg("新密码两次输入不一致");
        resultVO.setStatus("1");
        return resultVO;
    }
    try {
        // 修改密码
        logger.info("旧密码:" + ptuUser.getUserPwd());
        logger.info("新密码:" + newPwd);
        ptuUser.setUserPwd(newPwd);
        PtuUser ptuUserNew = userService.update(ptuUser);
        if (ptuUserNew != null) {
            // 修改成功
            logger.info("密码修改成功");
            redisUtils.del(ptuUser.getUserId());
            resultVO.setError_msg("密码修改成功");
            resultVO.setStatus("0");
            resultVO.setObject(ptuUserNew);
        } else {
            // 修改失败
            logger.info("密码修改失败");
            resultVO.setError_msg("密码修改失败");
            resultVO.setStatus("1");
        }
    } catch (Exception e) {
        logger.error(e.getMessage());
        resultVO.setError_msg("密码修改失败");
        resultVO.setStatus("1");
        return resultVO;
    }
    return resultVO;
}


@RequestMapping("/getFormInfo")
public String getFormInfo(HttpServletRequest request,Model model){
    // 查出当前登录用户
    PtuUser ptuUser = userService.findOne(request);
    logger.info("当前用户是:" + ptuUser);
    model.addAttribute("currentUser", ptuUser);
    String infoName = request.getParameter("infoName");
    if ("baseInfo".equals(infoName) || "myInformation".equals(infoName)) {
        return "form/baseFormInfo";
    } else if ("otherInfo".equals(infoName)) {
        return "form/otherFormInfo";
    } else if ("pwdInfo".equals(infoName)) {
        return "form/pwdFormInfo";
    } else if ("myNews".equals(infoName)) {
        return "table/myNewsTable";
    } else if ("myAnswer".equals(infoName)) {
        return "table/myAnswerTable";
    } else if ("myGoods".equals(infoName)) {
        return "table/myGoodsTable";
    }
    return "";
}


@RequestMapping("/reviseOtherInfo")
@ResponseBody
public ResultVO reviseOtherInfo(HttpServletRequest request,String object){
    // 查出当前登录用户
    PtuUser ptuUser = userService.findOne(request);
    ResultVO resultVO = new ResultVO();
    JSONArray jsonArray = JSONArray.parseArray(object);
    Iterator<Object> it = jsonArray.iterator();
    Map<String, Object> mapObject = new HashMap<>();
    while (it.hasNext()) {
        JSONObject obj = (JSONObject) it.next();
        mapObject.put((String) obj.get("name"), obj.get("value"));
    }
    try {
        PtuUser userMap = JSONObject.parseObject(JSONObject.toJSONString(mapObject), PtuUser.class);
        // 不拷贝null属性
        MyBeanUtils.copyProperties(userMap, ptuUser);
        PtuUser ptuUserNew = userService.update(ptuUser);
        if (ptuUserNew != null) {
            // 修改成功
            logger.info("其他信息修改成功");
            redisUtils.del(ptuUser.getUserId());
            resultVO.setError_msg("其他信息修改成功");
            resultVO.setStatus("0");
            resultVO.setObject(ptuUserNew);
        } else {
            // 修改失败
            logger.info("其他信息修改失败");
            resultVO.setError_msg("其他信息修改失败");
            resultVO.setStatus("0");
            resultVO.setObject(ptuUserNew);
        }
    } catch (Exception e) {
        logger.error(e.getMessage());
        resultVO.setError_msg("其他信息修改失败");
        resultVO.setStatus("1");
        return resultVO;
    }
    return resultVO;
}


@RequestMapping("/getMyInformation")
public String getMyInformation(HttpServletRequest request,Model model){
    // 查出当前登录用户
    PtuUser ptuUser = userService.findOne(request);
    logger.info("当前用户是:" + ptuUser);
    model.addAttribute("currentUser", ptuUser);
    return "information/myInformation";
}


@RequestMapping("/delMyGoods")
@ResponseBody
public ResultVO delMyGoods(String id){
    ResultVO resultVO = new ResultVO();
    logger.info("要删除的商品id为:" + id);
    if (StringUtils.isEmpty(id)) {
        logger.info("传入的商品id为空");
        resultVO.setError_msg("商品id为null");
        resultVO.setStatus("1");
        return resultVO;
    }
    try {
        marketService.delGoodsById(id);
        resultVO.setStatus("0");
        resultVO.setError_msg("删除成功");
    } catch (Exception e) {
        logger.error("删除商品失败,answerId=" + id);
        logger.error(e.getMessage());
        resultVO.setError_msg("删除商品失败");
        resultVO.setStatus("1");
        return resultVO;
    }
    return resultVO;
}


@RequestMapping("/getAnswerTable")
@ResponseBody
public JSONObject getAnswerTable(HttpServletRequest request,Integer rows,Integer page,String answerTitle){
    // 查出当前登录用户
    PtuUser ptuUser = (PtuUser) request.getSession().getAttribute("loginUser");
    logger.info("当前用户是:" + ptuUser);
    // 对查询条件判断是否为空
    if (StringUtils.isEmpty(answerTitle)) {
        answerTitle = "";
    }
    try {
        Pageable pageable = PageRequest.of(page, rows, Sort.Direction.DESC, "answerDate");
        Page<PtuAnswer> answerPage = answerService.queryAnswerByUserId(ptuUser.getUserId(), answerTitle, pageable);
        List<PtuAnswer> answerList = answerPage.getContent();
        for (PtuAnswer answer : answerList) {
            answer.setAnswerDate(DateUtils.date2ViewType(answer.getAnswerDate(), "datetime"));
        }
        long total = answerPage.getTotalElements();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", answerList);
        JSONObject json = new JSONObject(map);
        return json;
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    return null;
}


@RequestMapping("/getGoodsTable")
@ResponseBody
public JSONObject getGoodsTable(HttpServletRequest request,Integer rows,Integer page,String goodsName){
    // 查出当前登录用户
    PtuUser ptuUser = userService.findOne(request);
    logger.info("当前用户是:" + ptuUser);
    // 对查询条件判断是否为空
    if (StringUtils.isEmpty(goodsName)) {
        goodsName = "";
    }
    try {
        Pageable pageable = PageRequest.of(page, rows, Sort.Direction.DESC, "createTime");
        Page<Market> marketPage = marketService.queryGoodsById(ptuUser.getUserId(), goodsName, pageable);
        List<Market> marketList = marketPage.getContent();
        long total = marketPage.getTotalElements();
        for (Market market : marketList) {
            market.setCreateTime(DateUtils.date2ViewType(market.getCreateTime(), "datetime"));
        }
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", marketList);
        JSONObject json = new JSONObject(map);
        return json;
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    return null;
}


@RequestMapping("/delMyNews")
@ResponseBody
public ResultVO delMyNews(String newsId){
    ResultVO resultVO = new ResultVO();
    logger.info("要删除的新闻id为:" + newsId);
    if (StringUtils.isEmpty(newsId)) {
        logger.error("传入的新闻id为空");
        resultVO.setError_msg("新闻id为null");
        resultVO.setStatus("1");
        return resultVO;
    }
    try {
        newsService.delNewsById(newsId);
        resultVO.setStatus("0");
        resultVO.setError_msg("删除成功");
    } catch (Exception e) {
        logger.error("删除新闻失败,newsId=" + newsId);
        logger.error(e.getMessage());
        resultVO.setError_msg("删除新闻失败");
        resultVO.setStatus("1");
        return resultVO;
    }
    return resultVO;
}


@RequestMapping("/delMyAnswer")
@ResponseBody
public ResultVO delMyAnswer(String answerId){
    ResultVO resultVO = new ResultVO();
    logger.info("要删除的问答id为:" + answerId);
    if (StringUtils.isEmpty(answerId)) {
        logger.info("传入的问答id为空");
        resultVO.setError_msg("新闻id为null");
        resultVO.setStatus("1");
        return resultVO;
    }
    try {
        answerService.delAnswerById(answerId);
        resultVO.setStatus("0");
        resultVO.setError_msg("删除成功");
    } catch (Exception e) {
        logger.error("删除问答失败,answerId=" + answerId);
        logger.error(e.getMessage());
        resultVO.setError_msg("删除问答失败");
        resultVO.setStatus("1");
        return resultVO;
    }
    return resultVO;
}


@RequestMapping("/reviseBaseInfo")
@ResponseBody
public ResultVO reviseBaseInfo(HttpServletRequest request,String object){
    // 查出当前登录用户
    PtuUser ptuUser = userService.findOne(request);
    ResultVO resultVO = new ResultVO();
    JSONArray jsonArray = JSONArray.parseArray(object);
    Iterator<Object> it = jsonArray.iterator();
    Map<String, Object> mapObject = new HashMap<>();
    while (it.hasNext()) {
        JSONObject obj = (JSONObject) it.next();
        mapObject.put((String) obj.get("name"), obj.get("value"));
    }
    // 判断用户身份标识不能为空
    if (StringUtils.isEmpty(mapObject.get("userStatus"))) {
        logger.info("用户身份标识不能为空");
        resultVO.setError_msg("用户身份标识不能为空");
        resultVO.setStatus("1");
        return resultVO;
    }
    // 判断用户昵称不能为空
    if (StringUtils.isEmpty(mapObject.get("nickName"))) {
        logger.info("用户昵称不能为空");
        resultVO.setError_msg("用户昵称不能为空");
        resultVO.setStatus("1");
        return resultVO;
    }
    try {
        logger.info("当前用户对象:" + ptuUser);
        PtuUser userMap = JSONObject.parseObject(JSONObject.toJSONString(mapObject), PtuUser.class);
        // 不拷贝null属性
        MyBeanUtils.copyProperties(userMap, ptuUser);
        // 查出当前机构名称
        String orgName = orgCodeService.getOrgNameByOrgStatus((String) mapObject.get("orgStatus"));
        ptuUser.setOrgName(orgName);
        PtuUser ptuUserNew = userService.update(ptuUser);
        if (ptuUserNew != null) {
            // 修改成功
            logger.info("基础信息修改成功");
            redisUtils.del(ptuUser.getUserId());
            resultVO.setError_msg("基础信息修改成功");
            resultVO.setStatus("0");
            resultVO.setObject(ptuUserNew);
        } else {
            // 修改失败
            logger.info("基础信息修改失败");
            resultVO.setError_msg("基础信息修改失败");
            resultVO.setStatus("0");
            resultVO.setObject(ptuUserNew);
        }
    } catch (Exception e) {
        logger.error(e.getMessage());
        resultVO.setError_msg("基础信息修改失败");
        resultVO.setStatus("1");
        return resultVO;
    }
    return resultVO;
}


@RequestMapping("/getNewsTable")
@ResponseBody
public JSONObject getNewsTable(HttpServletRequest request,Integer rows,Integer page,String newsTitle){
    // 查出当前登录用户
    PtuUser ptuUser = (PtuUser) request.getSession().getAttribute("loginUser");
    logger.info("当前用户是:" + ptuUser);
    // 对查询条件判断是否为空
    if (StringUtils.isEmpty(newsTitle)) {
        newsTitle = "";
    }
    try {
        Pageable pageable = PageRequest.of(page, rows, Sort.Direction.DESC, "newsDate");
        Page<PtuNews> newsPage = newsService.queryNewsByUserId(ptuUser.getUserId(), newsTitle, pageable);
        List<PtuNews> newsList = newsPage.getContent();
        for (PtuNews news : newsList) {
            news.setNewsDate(DateUtils.date2ViewType(news.getNewsDate(), "datetime"));
        }
        long total = newsPage.getTotalElements();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", newsList);
        JSONObject json = new JSONObject(map);
        return json;
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    return null;
}


}