package edu.xr.campusweibo.web.rest;
 import edu.xr.campusweibo.config.Constants;
import edu.xr.campusweibo.domain.Friend;
import edu.xr.campusweibo.domain.MyUser;
import edu.xr.campusweibo.service.FriendService;
import edu.xr.campusweibo.service.MyUserService;
import edu.xr.campusweibo.web.rest.util.ResponseData;
import edu.xr.campusweibo.web.rest.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/weibo")
public class FriendResource {

 private  Logger log;

 private  FriendService friendService;

 private  MyUserService myUserService;


@RequestMapping(value = "/friend/add", method = RequestMethod.POST)
@ResponseBody
public ResponseResult addFriend(HttpServletRequest request,HttpServletResponse response){
    String fnickname = request.getParameter("friendinfo");
    MyUser addfriend = null;
    try {
        addfriend = myUserService.getUserBySchoolcode(fnickname);
    } catch (Exception e) {
        return new ResponseResult(Constants.FAIL_CODE, Constants.FAIL_INFO);
    }
    if (addfriend == null) {
        return new ResponseResult(Constants.FAIL_CODE, Constants.FAIL_INFO);
    }
    Long addfriendId = addfriend.getId();
    String userIdStr = request.getHeader(Constants.REQUEST_HEADER_INFO_KEY);
    if (StringUtils.isEmpty(userIdStr)) {
        return new ResponseResult(Constants.FAIL_CODE, Constants.FAIL_INFO);
    }
    Friend add = new Friend();
    add.setUid(Long.valueOf(userIdStr));
    add.setFuid(addfriendId);
    if (friendService.isExist(add)) {
        try {
            friendService.addFriend(add);
        } catch (Exception e) {
            return new ResponseResult(Constants.FAIL_CODE, Constants.FAIL_INFO);
        }
        return new ResponseResult(Constants.SUCCESS_CODE, Constants.SUCCESS_INFO);
    }
    return new ResponseResult(Constants.FAIL_CODE, Constants.FAIL_INFO);
}


@RequestMapping(value = "/friend/search", method = RequestMethod.POST)
@ResponseBody
public ResponseResult searchFriend(HttpServletRequest request,HttpServletResponse response){
    String id = request.getParameter("friendinfo");
    log.info("搜索好友" + id);
    MyUser searchUser = null;
    try {
        searchUser = myUserService.getUserBySchoolcode(id);
        if (searchUser == null) {
            searchUser = myUserService.getUserById(Long.valueOf(id));
        }
    } catch (Exception e) {
        return new ResponseResult(Constants.FAIL_CODE, Constants.FAIL_INFO);
    }
    if (searchUser != null) {
        return new ResponseData<MyUser>(Constants.SUCCESS_CODE, Constants.SUCCESS_INFO, searchUser);
    }
    return new ResponseResult(Constants.FAIL_CODE, Constants.FAIL_INFO);
}


@RequestMapping(value = "/friend/getAll/{id}", method = RequestMethod.GET)
public ResponseResult getAllFriend(Long id){
    log.info("查找好友列表");
    List<Friend> friendList = null;
    List<MyUser> myUserList = new ArrayList<>();
    try {
        friendList = friendService.getAllFrid(id);
        if (friendList != null) {
            for (Friend f : friendList) {
                MyUser temp = myUserService.getUserById(f.getFuid());
                myUserList.add(temp);
            }
            return new ResponseData<List<MyUser>>(Constants.SUCCESS_CODE, Constants.SUCCESS_INFO, myUserList);
        }
        return new ResponseResult(Constants.FAIL_CODE, Constants.FAIL_INFO);
    } catch (Exception e) {
        return new ResponseResult(Constants.FAIL_CODE, Constants.FAIL_INFO);
    }
}


}