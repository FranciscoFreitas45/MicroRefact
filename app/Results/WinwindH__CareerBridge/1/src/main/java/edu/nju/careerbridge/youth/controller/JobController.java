package edu.nju.careerbridge.youth.controller;
 import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.nju.careerbridge.youth.bean.ResultMessageBean;
import edu.nju.careerbridge.youth.blservice.JobBLService;
import java.util.Map;
@Api(value = "职业模块", description = "职业相关接口")
@RestController
@RequestMapping("/job")
public class JobController {

@Autowired
 private  JobBLService jobService;

@Autowired
public JobController(JobBLService jobService) {
    this.jobService = jobService;
}
@ApiOperation(value = "不喜欢职位", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"), @ApiImplicitParam(name = "jobId", value = "职位id", required = true, dataType = "String") })
@PostMapping("/dislike")
public ResultMessageBean dislikeJob(String param){
    JSONObject jo = new JSONObject();
    Map<String, Object> m = (Map<String, Object>) jo.parse(param);
    System.out.println("dislike " + (String) m.get("phone") + " " + (String) m.get("jobId"));
    return jobService.dislikeJob((String) m.get("phone"), (String) m.get("jobId"));
}


@ApiOperation(value = "收藏职位", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"), @ApiImplicitParam(name = "jobId", value = "职位id", required = true, dataType = "String") })
@PostMapping("/like/cancel")
public ResultMessageBean cancelLikeJob(String param){
    JSONObject jo = new JSONObject();
    Map<String, Object> m = (Map<String, Object>) jo.parse(param);
    System.out.println("cancel " + (String) m.get("phone") + " " + (String) m.get("jobId"));
    return jobService.cancelLikeJob((String) m.get("phone"), (String) m.get("jobId"));
}


@ApiOperation(value = "收藏职位", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"), @ApiImplicitParam(name = "jobId", value = "职位id", required = true, dataType = "String") })
@PostMapping("/like")
public ResultMessageBean likeJob(String param){
    JSONObject jo = new JSONObject();
    Map<String, Object> m = (Map<String, Object>) jo.parse(param);
    System.out.println("like " + (String) m.get("phone") + " " + (String) m.get("jobId"));
    return jobService.likeJob((String) m.get("phone"), (String) m.get("jobId"));
}


@ApiOperation(value = "浏览职位", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"), @ApiImplicitParam(name = "jobId", value = "职位id", required = true, dataType = "String") })
@PostMapping("/scan")
public ResultMessageBean scanJob(String param){
    JSONObject jo = new JSONObject();
    Map<String, Object> m = (Map<String, Object>) jo.parse(param);
    System.out.println("scan " + m.get("phone") + " " + m.get("jobId"));
    return jobService.scanJob(m.get("phone").toString(), m.get("jobId").toString());
}


@ApiOperation(value = "用户收藏职业列表", notes = "可能状态码：0,1,9<br>登录成功返回签名")
@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String") })
@PostMapping("/like/display")
public ResultMessageBean getLikedJob(String phone,String jobId){
    return jobService.scanJob(phone, jobId);
}


}