package com.example.steam.service;
 import com.example.steam.dao.UserDao;
import com.example.steam.entity.Image;
import com.example.steam.entity.User;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.mq.MQProducer;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.CookieKey;
import com.example.steam.redis.key.EmailKey;
import com.example.steam.redis.key.UserKey;
import com.example.steam.utils;
import com.example.steam.vo.LoginUser;
import com.example.steam.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import com.example.steam.Interface.RedisService;
import com.example.steam.Interface.ImageService;
import com.example.steam.Interface.MQProducer;
import com.example.steam.Interface.FileUploadUtil;
import com.example.steam.DTO.ResultMsg;
@Service
public class UserService {

 private Logger log;

@Value("${server.servlet.session.cookie.max-age}")
 private int cookieMaxAge;

@Autowired
 private UserDao userDao;

@Autowired
 private RedisService redisService;

@Autowired
 private ImageService imageService;

@Autowired
 private ApplicationContext applicationContext;

@Autowired
 private MQProducer mqProducer;

@Autowired
 private FileUploadUtil fileUploadUtil;


public ResultMsg handleEditUser(String email,HttpServletRequest request){
    User user = ((UserService) applicationContext.getBean("userService")).findByEmail(email);
    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
    String nickName = multipartHttpServletRequest.getParameter("nickName");
    String city = multipartHttpServletRequest.getParameter("city");
    String introduction = multipartHttpServletRequest.getParameter("introduction");
    MultipartFile file = multipartHttpServletRequest.getFile("avatar");
    log.error(nickName);
    user.setNickName(nickName);
    if (StringUtils.isNotEmpty(city)) {
        log.error(city);
        user.setProvince(city);
    }
    if (StringUtils.isNotEmpty(introduction)) {
        log.error(introduction);
        user.setIntroduction(introduction);
    }
    if (file != null) {
        String finalImageUrl = fileUploadUtil.handleMultipartFile(file);
        Image image = new Image(finalImageUrl, "avatar", "avatar");
        long newImageId = imageService.addImage(image);
        user.setAvatar(newImageId);
    }
    redisService.set(UserKey.USER_ID, email, user);
    ((UserService) applicationContext.getBean("userService")).updateUser(user);
    return ResultMsg.SUCCESS;
}


public int addUser(User user){
    redisService.set(UserKey.USER_ID, user.getEmail(), user);
    return userDao.addUser(user);
}


public ResultMsg updateFindPassword(String email){
    User user = redisService.get(UserKey.USER_ID, email, User.class);
    if (user == null) {
        return ResultMsg.USER_NO;
    }
    String newPassword = UUIDUtil.randomUUID().substring(0, 7);
    String finalPassword = Md5PassUtil.md5Conver(newPassword, user.getSalt());
    user.setPassword(finalPassword);
    redisService.set(UserKey.USER_ID, email, user);
    ((UserService) applicationContext.getBean("userService")).updateUser(user);
    mqProducer.productEvent(new Event(EventType.FIND_WORD).setEtrMsg(Event.EMAIL, email).setEtrMsg(Event.NEW_WORD, newPassword));
    return ResultMsg.SUCCESS;
}


public ResultMsg handleDeleteUser(String email){
    ((UserService) applicationContext.getBean("userService")).deleteUser(email);
    return ResultMsg.SUCCESS;
}


public void addCookie(HttpServletResponse response,String cookieId,User user,LoginUser loginUr){
    Cookie cookie = new Cookie(StaticField.COOKIE_KEY, cookieId);
    cookie.setMaxAge(cookieMaxAge);
    /**
     * 不同路径下cookie不同
     * 要设置相同的路径，否则会出现一样的cookie
     */
    cookie.setPath("/");
    log.info(cookieMaxAge + "");
    response.addCookie(cookie);
    // UserKey.COOKIE_ID.setExpiredTime(cookieMaxAge);
    LoginUser loginUser;
    if (loginUr != null) {
        loginUser = loginUr;
    } else {
        loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setEmail(user.getEmail());
        loginUser.setIsAdmin(user.getIsAdmin());
        loginUser.setNickName(user.getNickName());
        loginUser.setAvatar(imageService.findImageUrlById(user.getAvatar()));
        loginUser.setCountry(user.getCountry());
        loginUser.setProvince(user.getProvince());
        loginUser.setIntroduction(user.getIntroduction());
        loginUser.setCommentNum(user.getCommentNum());
        loginUser.setBuyGames(user.getBuyGames());
    }
    redisService.set(UserKey.COOKIE_ID, cookieId, loginUser);
    // CookieKey.EMAIL.setExpiredTime(cookieMaxAge);
    redisService.set(CookieKey.EMAIL, loginUser.getEmail(), cookieId);
}


public Cookie findCookie(HttpServletRequest request){
    Cookie[] cookies = request.getCookies();
    if (cookies == null) {
        return null;
    }
    for (int i = 0; i < cookies.length; i++) {
        if (cookies[i].getName().equals(StaticField.COOKIE_KEY)) {
            return cookies[i];
        }
    }
    return null;
}


public UserVo findUserVoByEmail(String email){
    User user = findByEmail(email);
    UserVo userVo = new UserVo();
    userVo.setId(user.getId());
    userVo.setAvatarImage(imageService.findImageUrlById(user.getAvatar()));
    userVo.setLv(user.getLv());
    userVo.setCountry(user.getCountry());
    userVo.setProvince(user.getProvince());
    userVo.setNickName(user.getNickName());
    userVo.setIntroduction(user.getIntroduction());
    return userVo;
}


public ResultMsg handleLogin(String email,String password,HttpServletRequest request,HttpServletResponse response){
    log.error(email + " " + password);
    String cookieId = redisService.get(CookieKey.EMAIL, email, String.class);
    Cookie cookie = findCookie(request);
    if (StringUtils.isNotEmpty(cookieId) && cookie == null) {
        return ResultMsg.HAD_Login;
    }
    /**
     * 此处必须有外部调用，内部调用不会走代理路线
     * 即AOP会失效
     */
    User user = ((UserService) applicationContext.getBean("userService")).findByEmail(email);
    if (user == null) {
        return ResultMsg.NO_EMAIL;
    }
    String finalPass = Md5PassUtil.secondMd5(password, user.getSalt());
    if (!finalPass.equals(user.getPassword())) {
        return ResultMsg.PASS_ERROR;
    }
    cookieIsNullAndCreate(request, response, user);
    return ResultMsg.LOGIN_SUCCESS;
}


public void cookieIsNullAndCreate(HttpServletRequest request,HttpServletResponse response,User user){
    Cookie cookie = findCookie(request);
    String cookieId;
    if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
        cookieId = UUIDUtil.randomUUID();
    } else {
        cookieId = cookie.getValue();
    }
    addCookie(response, cookieId, user, null);
}


public int updateBuyGames(String email){
    User user = redisService.get(UserKey.USER_ID, email, User.class);
    user.setBuyGames(user.getBuyGames() + 1);
    redisService.set(UserKey.USER_ID, email, user);
    return ((UserService) applicationContext.getBean("userService")).updateUser(user);
}


public LoginUser converViewLoginUser(User user){
    if (user != null) {
        return new LoginUser(user, imageService.findImageById(user.getAvatar()));
    }
    return null;
}


public List<UserVo> findAllUser(){
    List<User> userList = userDao.findAllUser();
    List<UserVo> userVoList = new LinkedList<>();
    for (User user : userList) {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setAvatarImage(imageService.findImageUrlById(user.getAvatar()));
        userVo.setLv(user.getLv());
        userVo.setCountry(user.getCountry());
        userVo.setProvince(user.getProvince());
        userVo.setNickName(user.getNickName());
        userVo.setIntroduction(user.getIntroduction());
        userVo.setIsAdmin(user.getIsAdmin());
        userVo.setEmail(user.getEmail());
        userVoList.add(userVo);
    }
    return userVoList;
}


public LoginUser getUserByToken(HttpServletResponse response,String cookieToken){
    if (cookieToken == null) {
        return null;
    }
    LoginUser loginUser = redisService.get(UserKey.COOKIE_ID, cookieToken, LoginUser.class);
    // log.error(loginUser.toString()+" "+2);
    if (loginUser != null) {
        addCookie(response, cookieToken, null, loginUser);
    }
    return loginUser;
}


public int updateUser(User user){
    return userDao.updateUser(user);
}


@Transactional(rollbackFor = Exception.class)
public ResultMsg handleAddNewUser(HttpServletRequest request){
    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
    String email = multipartHttpServletRequest.getParameter("email");
    User user = ((UserService) applicationContext.getBean("userService")).findByEmail(email);
    if (user != null) {
        return ResultMsg.HAD_REGISTER;
    }
    String nickName = multipartHttpServletRequest.getParameter("nickName");
    int isAdmin = Integer.parseInt(multipartHttpServletRequest.getParameter("isAdmin"));
    String password = multipartHttpServletRequest.getParameter("password");
    String city = multipartHttpServletRequest.getParameter("city");
    String introduction = multipartHttpServletRequest.getParameter("introduction");
    MultipartFile file = multipartHttpServletRequest.getFile("avatar");
    String finalImageUrl = fileUploadUtil.handleMultipartFile(file);
    Image image = new Image(finalImageUrl, "avatar", "avatar");
    long newImageId = imageService.addImage(image);
    String salt = UUIDUtil.randomUUID().substring(0, 6);
    String finalPass = Md5PassUtil.secondMd5(password, salt);
    User newUser = new User(nickName, email, salt, finalPass, newImageId, city, introduction, isAdmin);
    ((UserService) applicationContext.getBean("userService")).addUser(newUser);
    return ResultMsg.SUCCESS;
}


public ResultMsg updateAdminStatu(String email){
    User user = ((UserService) applicationContext.getBean("userService")).findByEmail(email);
    if (user.getIsAdmin() == User.ISADMIN) {
        user.setIsAdmin(User.NOADMIN);
    } else {
        user.setIsAdmin(User.ISADMIN);
    }
    redisService.set(UserKey.USER_ID, email, user);
    int result = ((UserService) applicationContext.getBean("userService")).updateUser(user);
    return ResultMsg.SUCCESS(result);
}


public int updateCommnetNum(String email){
    User user = redisService.get(UserKey.USER_ID, email, User.class);
    user.setCommentNum(user.getCommentNum() + 1);
    redisService.set(UserKey.USER_ID, email, user);
    return ((UserService) applicationContext.getBean("userService")).updateUser(user);
}


public ResultMsg handleAdminLogin(String email,String password,HttpServletRequest request){
    User user = ((UserService) applicationContext.getBean("userService")).findByEmail(email);
    User adminUser = redisService.get(UserKey.ADMIN_EMAIL, UserKey.ADMIN_KEY + email, User.class);
    if (user == null) {
        return ResultMsg.NO_EMAIL;
    }
    if (adminUser != null) {
        return ResultMsg.HAD_Login;
    }
    String finalPassword = Md5PassUtil.secondMd5(password, user.getSalt());
    if (!finalPassword.equals(user.getPassword())) {
        return ResultMsg.PASS_ERROR;
    }
    if (user.getIsAdmin() != User.ISADMIN) {
        return ResultMsg.ADMIN_NO;
    }
    request.getSession().setAttribute(StaticField.EMAIL_KEY, email);
    redisService.set(UserKey.ADMIN_EMAIL, UserKey.ADMIN_KEY + email, user);
    AdminUserHoleUtil.addUser(user);
    return ResultMsg.LOGIN_SUCCESS;
}


public ResultMsg handleRegister(String email,String password,String code){
    String verificationCode = redisService.get(EmailKey.VERIFICATION_CODE, email, String.class);
    if (verificationCode == null) {
        return ResultMsg.CODE_INVALID;
    }
    User user = ((UserService) applicationContext.getBean("userService")).findByEmail(email);
    if (user != null) {
        return ResultMsg.HAD_REGISTER;
    }
    if (!code.equals(verificationCode)) {
        return ResultMsg.CODE_ERROR;
    }
    /**
     * 随机昵称，salt值，两次MD5密码加密
     */
    String nickName = UUIDUtil.randomUUID().substring(0, 5);
    String salt = UUIDUtil.randomUUID().substring(0, 6);
    String finalPass = Md5PassUtil.md5Conver(password, salt);
    User newUser = new User(nickName, email, salt, finalPass);
    ((UserService) applicationContext.getBean("userService")).addUser(newUser);
    return ResultMsg.REGISTER_SUCCESS;
}


public ResultMsg handleEditPass(String email,String newPass,String confimPass){
    User user = ((UserService) applicationContext.getBean("userService")).findByEmail(email);
    if (!newPass.equals(confimPass)) {
        return ResultMsg.PASS_NOT_EQUAL;
    }
    String finalPass = Md5PassUtil.secondMd5(newPass, user.getSalt());
    user.setPassword(finalPass);
    redisService.set(UserKey.USER_ID, email, user);
    ((UserService) applicationContext.getBean("userService")).updateUser(user);
    return ResultMsg.SUCCESS;
}


public int deleteUser(String email){
    redisService.del(UserKey.USER_ID, email);
    return userDao.deleteUser(email);
}


public ResultMsg handleLogoutAdmin(String email){
    redisService.del(UserKey.ADMIN_EMAIL, UserKey.ADMIN_KEY + email);
    AdminUserHoleUtil.removeUser(email);
    return ResultMsg.SUCCESS;
}


public User findByEmail(String email){
    User user = redisService.get(UserKey.USER_ID, email, User.class);
    if (user != null) {
        return user;
    }
    user = userDao.findUserByEmail(email);
    if (user != null) {
        redisService.set(UserKey.USER_ID, email, user);
    }
    return user;
}


public ResultMsg handleLogout(String email){
    String cookieId = redisService.get(CookieKey.EMAIL, email, String.class);
    redisService.del(CookieKey.EMAIL, email);
    redisService.del(UserKey.COOKIE_ID, cookieId);
    log.info("已注销" + cookieId);
    return ResultMsg.SUCCESS(cookieId);
}


public int updateNickNameAndCountryAndProvinceAndAvatarAndIntroduction(String email,String nickName,String country,String province,long avatar,String avatarAddress,String introduction){
    User user = redisService.get(UserKey.USER_ID, email, User.class);
    String cookieId = redisService.get(CookieKey.EMAIL, email, String.class);
    LoginUser loginUser = redisService.get(UserKey.COOKIE_ID, cookieId, LoginUser.class);
    user.setNickName(nickName);
    user.setProvince(province);
    user.setCountry(country);
    user.setIntroduction(introduction);
    loginUser.setNickName(nickName);
    loginUser.setIntroduction(introduction);
    loginUser.setProvince(province);
    loginUser.setCountry(country);
    if (avatar != 0 && avatarAddress != null) {
        user.setAvatar(avatar);
        loginUser.setAvatar(avatarAddress);
    }
    redisService.set(UserKey.USER_ID, email, user);
    redisService.set(UserKey.COOKIE_ID, cookieId, loginUser);
    return ((UserService) applicationContext.getBean("userService")).updateUser(user);
}


}