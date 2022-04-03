package com.dtdhehe.ptulife.controller;
 import com.alibaba.fastjson.JSONObject;
import com.dtdhehe.ptulife.entity.PtuAnswer;
import com.dtdhehe.ptulife.entity.PtuUser;
import com.dtdhehe.ptulife.service.AnswerService;
import com.dtdhehe.ptulife.service.LabelService;
import com.dtdhehe.ptulife.service.UserService;
import com.dtdhehe.ptulife.util.CheckUserUtils;
import com.dtdhehe.ptulife.util.DateUtils;
import com.dtdhehe.ptulife.util.KeyUtils;
import com.dtdhehe.ptulife.vo.AnswerDto;
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
import com.dtdhehe.ptulife.Interface.UserService;
import com.dtdhehe.ptulife.Interface.LabelService;
import com.dtdhehe.ptulife.DTO.ResultVO;
import com.dtdhehe.ptulife.DTO.PtuUser;
@Controller
@RequestMapping("/answer/answerController")
public class AnswerController {

 private  Logger logger;

@Autowired
 private  AnswerService answerService;

@Autowired
 private  UserService userService;

@Autowired
 private  LabelService labelService;


@RequestMapping("/saveAnswer")
@ResponseBody
public ResultVO saveAnswer(String answerObject){
    PtuAnswer ptuAnswer = JSONObject.parseObject(answerObject, PtuAnswer.class);
    logger.info("开始保存问答");
    ResultVO resultVO = new ResultVO();
    try {
        ptuAnswer.setAnswerId(KeyUtils.getUniqueKey());
        ptuAnswer.setAnswerDate(DateUtils.getCurrentDateTime());
        // 判断是否有上传封面,若未上传，指定默认封面
        if (StringUtils.isEmpty(ptuAnswer.getAnswerIcon())) {
            ptuAnswer.setAnswerIcon("/uploads/2019042217362536default.jpg");
        }
        PtuAnswer answerNew = answerService.save(ptuAnswer);
        if (answerNew != null) {
            logger.info("开始保存问答");
            resultVO.setStatus("0");
            resultVO.setObject(answerNew);
            // 问答保存成功，同时添加记录到hot表
            labelService.save(answerNew, answerNew.getClass());
        } else {
            logger.error("问答保存失败");
            resultVO.setStatus("1");
            resultVO.setError_msg("answerNew对象为空");
        }
    } catch (Exception e) {
        logger.error(e.getMessage());
        resultVO.setStatus("1");
        resultVO.setError_msg(e.getMessage());
    }
    return resultVO;
}


@RequestMapping("/queryAllAnswer")
@ResponseBody
public ResultVO queryAllAnswer(Integer page,Integer size){
    logger.info("查询所有问答");
    ResultVO resultVO = new ResultVO();
    List<AnswerDto> answerList;
    try {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "answerDate");
        // 分页查询问答列表
        Page<AnswerDto> ptuAnswers = answerService.queryAllAnswerWithHead(pageable);
        if (ptuAnswers.isLast()) {
            resultVO.setError_msg("最后一页啦");
        } else {
            resultVO.setError_msg("");
        }
        // 获得新闻List
        answerList = ptuAnswers.getContent();
        resultVO.setStatus("0");
        resultVO.setObject(answerList);
    } catch (Exception e) {
        logger.error(e.getMessage());
        resultVO.setStatus("1");
        resultVO.setError_msg(e.getMessage());
    }
    return resultVO;
}


@RequestMapping("/getEditAnswer")
public String getEditAnswer(HttpServletRequest request,Model model){
    // 查出当前登录用户
    PtuUser ptuUser = userService.findOne(request);
    String userStatusStr = CheckUserUtils.checkUserStatus(ptuUser.getUserStatus());
    model.addAttribute("date", DateUtils.getCurrentDate());
    model.addAttribute("currentUser", ptuUser);
    model.addAttribute("userStatusStr", userStatusStr);
    return "/answer/editAnswer";
}


@RequestMapping("/getAnswerPage")
public String getAnswerPage(String answerId,Model model,HttpServletRequest request){
    if (StringUtils.isEmpty(answerId)) {
        throw new Exception("传入的id为空");
    }
    logger.info("查询的问答id为:" + answerId);
    PtuAnswer ptuAnswer = answerService.queryAnswerById(answerId);
    ptuAnswer.setAnswerDate(DateUtils.date2ViewType(ptuAnswer.getAnswerDate()));
    // 查出当前登录的用户
    PtuUser user = userService.findOne(request);
    // 查出当前问答作者
    PtuUser ptuUser = userService.findByUserId(ptuAnswer.getUserId());
    String userStatusStr = CheckUserUtils.checkUserStatus(ptuUser.getUserStatus());
    model.addAttribute("currentUser", ptuUser);
    model.addAttribute("userStatusStr", userStatusStr);
    model.addAttribute("ptuAnswer", ptuAnswer);
    model.addAttribute("user", user);
    return "/answer/answerInfo";
}


}