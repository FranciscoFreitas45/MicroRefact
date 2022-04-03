package com.qidian.hcm.module.center.service;
 import com.qidian.hcm.common.config.HCMConfig;
import com.qidian.hcm.common.constants.RedisPrefixConstant;
import com.qidian.hcm.common.constants.SystemConstant;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.interceptor.TenantThreadHelper;
import com.qidian.hcm.common.jwt.Jwt;
import com.qidian.hcm.common.jwt.JwtUserInfo;
import com.qidian.hcm.common.redis.RedisService;
import com.qidian.hcm.common.utils.CommonUtils;
import com.qidian.hcm.common.utils.HttpClientUtil;
import com.qidian.hcm.module.center.config.TenantDataSourceProvider;
import com.qidian.hcm.module.center.dto.LoginResultDTO;
import com.qidian.hcm.module.center.entity.CompanyGroup;
import com.qidian.hcm.module.center.entity.GroupConfig;
import com.qidian.hcm.module.center.entity.User;
import com.qidian.hcm.module.center.repository.GroupConfigRepository;
import com.qidian.hcm.module.center.repository.UserRepository;
import com.qidian.hcm.module.center.request;
import com.qidian.hcm.module.employee.request.InitCompanyAdminRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.Interface.HCMConfig;
@Service
@Slf4j
public class UserService {

@Autowired
 private  HCMConfig hcmConfig;

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  CompanyGroupService companyGroupService;

@Autowired
 private  GroupConfigRepository groupConfigRepository;

@Autowired
 private  TenantDataSourceProvider tenantDataSourceProvider;

@Autowired
 private  RedisService redisService;


public User addNotActiveUser(AddNotActiveUserRequest request){
    Optional<User> userOptional = userRepository.findByPhone(request.getPhone());
    User user;
    GroupConfig groupConfig = groupConfigRepository.findByTenantName(request.getTenantName());
    if (userOptional.isPresent()) {
        user = userOptional.get();
        user.setGroupId(groupConfig.getGroupId());
    } else {
        user = new User(request.getUserName(), request.getPhone(), groupConfig.getGroupId());
        user.setStatus(SystemConstant.YES_INTEGER);
    }
    user = userRepository.save(user);
    return user;
}


public void updateUserPhone(UpdateUserPhoneRequest request){
    User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new BizException(USER_NOT_EXIST));
    user.setPhone(request.getPhone());
    userRepository.save(user);
}


public void updateBaseInfo(BaseInfoRequest request,Long userId){
    User user = userRepository.findById(userId).orElseThrow(() -> new BizException(USER_NOT_EXIST));
    user.setEmail(request.getEmail());
    user.setPassword(CommonUtils.encoderByMd5(request.getPassword()));
    userRepository.save(user);
}


public LoginResultDTO loginByVerification(SMSLoginRequest request){
    User user = userRepository.findByPhone(request.getPhone()).orElseThrow(() -> new BizException(USER_NOT_EXIST));
    String key = RedisPrefixConstant.VERIFICATION_CODE + request.getPhone();
    Object verificationCode = redisService.get(key);
    // 查不到会报空指针
    if (StringUtils.isEmpty(verificationCode)) {
        throw new BizException(VERIFICATION_CODE_OVER_DUE);
    } else if (!request.getCode().equals(verificationCode)) {
        throw new BizException(WRONG_VERIFICATION_CODE);
    }
    LoginResultDTO loginResultDTO = new LoginResultDTO();
    // 如果用户密码为空，表示未激活
    loginResultDTO.setIsActive(!StringUtils.isEmpty(user.getPassword()));
    JwtUserInfo jwtUserInfo = new JwtUserInfo();
    jwtUserInfo.setId(user.getId());
    GroupConfig groupConfig = groupConfigRepository.findByGroupId(user.getGroupId());
    if (groupConfig == null) {
        throw new BizException(DATASOURCE_NOT_FOUND);
    }
    jwtUserInfo.setTenantName(groupConfig.getTenantName());
    String token = Jwt.createToken(jwtUserInfo);
    loginResultDTO.setToken(token);
    loginResultDTO.setUserId(user.getId());
    // 登陆成功之后移除redis中存储的验证码
    redisService.remove(key);
    return loginResultDTO;
}


@Transactional
public void destroy(){
    tenantDataSourceProvider.dropDataBase(TenantThreadHelper.getToken().getId(), TenantThreadHelper.getToken().getTenantName());
}


@Transactional
public void createUser(RegisterRequest request){
    String tenantName = UUID.randomUUID().toString().substring(0, 8);
    // 保存公司信息
    CompanyGroup companyGroup = companyGroupService.createGroup(request, tenantName);
    Optional<User> userOptional = userRepository.findByPhone(request.getPhone());
    if (userOptional.isPresent()) {
        throw new BizException(DUPLICATE_USER);
    }
    // 保存用户
    User user = new User(request.getUsername(), request.getPassword(), request.getPhone(), companyGroup.getId());
    user.setStatus(SystemConstant.YES_INTEGER);
    user.setPassword(CommonUtils.encoderByMd5(user.getPassword()));
    userRepository.save(user);
    // 需要切换数据保存信息
    JwtUserInfo jwtUserInfo = new JwtUserInfo(user.getId(), tenantName);
    String token = Jwt.createToken(jwtUserInfo);
    InitCompanyAdminRequest createCompanyRequest = new InitCompanyAdminRequest();
    BeanUtils.copyProperties(request, createCompanyRequest);
    createCompanyRequest.setUserId(user.getId());
    HttpClientUtil.doPostObject(hcmConfig.getTenantUrl() + "api/user/init", createCompanyRequest, token);
}


public String loginByPassword(LoginRequest loginRequest){
    User user = userRepository.findByPhoneOrEmail(loginRequest.getUsername(), loginRequest.getUsername());
    if (user == null) {
        throw new BizException(USER_NOT_EXIST);
    }
    if (!user.getPassword().equals(CommonUtils.encoderByMd5(loginRequest.getPassword()))) {
        throw new BizException(WRONG_PASSWORD);
    }
    JwtUserInfo jwtUserInfo = new JwtUserInfo();
    jwtUserInfo.setId(user.getId());
    GroupConfig groupConfig = groupConfigRepository.findByGroupId(user.getGroupId());
    if (groupConfig == null) {
        throw new BizException(TENANT_CONFIG_ERROR);
    }
    jwtUserInfo.setTenantName(groupConfig.getTenantName());
    return Jwt.createToken(jwtUserInfo);
}


@Transactional
public void register(RegisterRequest request){
    User user = userRepository.findByUsername(request.getUsername());
    if (null != user) {
        throw new BizException(DUPLICATE_USER);
    }
    createUser(request);
}


}