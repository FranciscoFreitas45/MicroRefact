package edu.xr.campusweibo.web.rest;
 import edu.xr.campusweibo.config.Constants;
import edu.xr.campusweibo.domain.Weibo;
import edu.xr.campusweibo.service.FriendService;
import edu.xr.campusweibo.service.MyUserService;
import edu.xr.campusweibo.service.WeiboService;
import edu.xr.campusweibo.service.dto.WeiboDTO;
import edu.xr.campusweibo.web.rest.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.xr.campusweibo.Interface.MyUserService;
import edu.xr.campusweibo.Interface.FriendService;
@RestController
@RequestMapping("/weibo")
public class WeiboResource {

 private  Logger log;

 private  WeiboService weiboService;

 private  MyUserService myUserService;

 private  FriendService friendService;

public WeiboResource(WeiboService weiboService, MyUserService myUserService, FriendService friendService) {
    this.weiboService = weiboService;
    this.myUserService = myUserService;
    this.friendService = friendService;
}
@RequestMapping(value = "/addweibo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseResult createWeibo(Weibo weibo){
    log.info("创建微博" + weibo.toString());
    Weibo nWeibo = weiboService.getWeiboByText(weibo.getText(), weibo.getUid());
    if (nWeibo != null) {
        log.info("此用户微博已存在，请勿重复发表");
        return new ResponseResult(Constants.FAIL_CODE, Constants.FAIL_INFO);
    }
    try {
        weiboService.addWeibo(weibo);
    } catch (Exception e) {
        log.info("发布微博异常");
        return new ResponseResult(Constants.FAIL_CODE, Constants.FAIL_INFO);
    }
    return new ResponseResult(Constants.SUCCESS_CODE, Constants.SUCCESS_INFO);
}


@RequestMapping(value = "/getAll", method = RequestMethod.POST)
@ResponseBody
public Map<String,Object> getAllWeibo(HttpServletRequest request,HttpServletResponse response){
    Map<String, Object> returnMap = new HashMap();
    String userId = request.getHeader(Constants.REQUEST_HEADER_INFO_KEY);
    if (StringUtils.isEmpty(userId)) {
        returnMap.put("returnCode", Constants.FAIL_CODE);
        returnMap.put("returnInfo", Constants.FAIL_INFO);
        return returnMap;
    }
    List<WeiboDTO> weiboDTOList = new ArrayList<>();
    List<Weibo> weiboList = null;
    try {
        weiboList = weiboService.getAllWeibo(Long.valueOf(userId));
        if (weiboList != null) {
            for (int i = 0; i < weiboList.size(); i++) {
                weiboDTOList.add(new WeiboDTO(weiboList.get(i), myUserService.getUserById(Long.valueOf(userId)).getNickname()));
            }
            returnMap.put("returnCode", Constants.SUCCESS_CODE);
            returnMap.put("returnInfo", Constants.SUCCESS_INFO);
            returnMap.put("myweibolist", weiboDTOList);
            returnMap.put("guanzhunum", friendService.getAllFrid(Long.valueOf(userId)).size());
            returnMap.put("fansnum", friendService.getAllFans(Long.valueOf(userId)).size());
            return returnMap;
        } else {
            // 用户没有微博
            returnMap.put("returnCode", Constants.FAIL_CODE);
            returnMap.put("returnInfo", Constants.FAIL_INFO);
            return returnMap;
        }
    } catch (Exception e) {
        returnMap.put("returnCode", Constants.FAIL_CODE);
        returnMap.put("returnInfo", Constants.FAIL_INFO);
        return returnMap;
    }
}


}