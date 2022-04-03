package edu.nju.careerbridge.youth.controller;
 import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import edu.nju.careerbridge.youth.bean;
import edu.nju.careerbridge.youth.blservice.CompanyBLService;
import java.util.List;
import java.util.Map;
@Api(value = "职业模块", description = "职业相关接口")
@RestController
@RequestMapping("/company")
public class CompanyController {

@Autowired
 private  CompanyBLService companyBLService;


@ApiOperation(value = "收藏职位", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "company", value = "公司名", required = true, dataType = "String") })
@PostMapping("/remark/get")
public CompanyRemarkBean getCompanyRemark(String company){
    return companyBLService.getCompanyRemark(company);
}


@ResponseBody
// @RequestMapping(
// value = "/jobDetail/{jobId}",
// method = RequestMethod.GET,
// produces = {"application/json; charset=UTF-8"})
@PostMapping("/jobDetail")
public JobDetailBean getJobDetailByJobId(String param){
    JSONObject jo = new JSONObject();
    Map<String, String> m = (Map<String, String>) jo.parse(param);
    return companyBLService.getJobDetailByJobId(m.get("jobId"));
}


@ApiOperation(value = "收藏职位", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"), @ApiImplicitParam(name = "company", value = "公司名", required = true, dataType = "String"), @ApiImplicitParam(name = "recomandScore", value = "推荐率", required = true, dataType = "int"), @ApiImplicitParam(name = "futureScore", value = "公司前景看好率", required = true, dataType = "int"), @ApiImplicitParam(name = "ceoScore", value = "ceo支持率", required = true, dataType = "int"), @ApiImplicitParam(name = "remark", value = "评价", required = true, dataType = "String") })
@PostMapping("/remark")
public ResultMessageBean companyRemark(String phone,String company,int recomandScore,int futureScore,int ceoScore,String remark){
    return companyBLService.companyRemark(phone, company, recomandScore, futureScore, ceoScore, remark);
}


@ApiOperation(value = "收藏职位", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "company", value = "公司名", required = true, dataType = "String") })
@PostMapping("/interview/remark/get")
public CompanyInterviewRemarkBean getInterviewRemark(String company){
    return companyBLService.getInterviewRemark(company);
}


@ApiOperation(value = "收藏职位", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"), @ApiImplicitParam(name = "company", value = "公司名", required = true, dataType = "String"), @ApiImplicitParam(name = "result", value = "面试结果", required = true, dataType = "String"), @ApiImplicitParam(name = "difficulty", value = "面试难度", required = true, dataType = "int"), @ApiImplicitParam(name = "feeling", value = "面试感受", required = true, dataType = "int"), @ApiImplicitParam(name = "remark", value = "评价", required = true, dataType = "String") })
@PostMapping("/interview/remark")
public ResultMessageBean interviewRemark(String phone,String company,String result,int difficulty,int feeling,String remark){
    return companyBLService.interviewRemark(phone, company, result, difficulty, feeling, remark);
}


}