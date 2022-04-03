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
import edu.nju.careerbridge.youth.blservice.StatisticsBLService;
import java.util.List;
import java.util.Map;
@Api(value = "统计模块", description = "统计相关接口")
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

@Autowired
 private  StatisticsBLService statisticsBLService;


@ApiOperation(value = "得到所查公司的关键词", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "companyName", value = "公司名", required = true, dataType = "String") })
@PostMapping("/company/keywords")
public List<CompanyDescOutputBean> getCompanyKeywords(String param){
    JSONObject jo = new JSONObject();
    Map<String, String> m = (Map<String, String>) jo.parse(param);
    return statisticsBLService.getCompanyKeywords(m.get("companyName"));
}


@ResponseBody
@RequestMapping(value = "/job/keywords/{jobId}", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
public List<JobDescOutputBean> getJobKeywordsByJobId(String jobId){
    return statisticsBLService.getJobKeywordsByJobId(jobId);
}


}