import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.offway.athena.domain.PhAuth;
import cn.offway.athena.domain.PhCode;
import cn.offway.athena.domain.PhCreditDetail;
import cn.offway.athena.domain.PhUserInfo;
import cn.offway.athena.dto.Template;
import cn.offway.athena.dto.TemplateParam;
import cn.offway.athena.repository.PhAuthRepository;
import cn.offway.athena.service.PhAuthService;
import cn.offway.athena.service.PhCodeService;
import cn.offway.athena.service.PhCreditDetailService;
import cn.offway.athena.service.PhUserInfoService;
import cn.offway.athena.utils.HttpClientUtil;
@Service
public class PhAuthServiceImpl implements cn.offway.athena.service.PhAuthService,PhAuthService{

 private  Logger logger;

@Autowired
 private  PhAuthRepository phAuthRepository;

@Autowired
 private  PhUserInfoService phUserInfoService;

@Autowired
 private  PhCreditDetailService phCreditDetailService;

@Autowired
 private  PhCodeService phCodeService;


@Override
public boolean authUpdate(Long id,String status,String approvalContent,Authentication authentication){
    PhAuth phAuth = findOne(id);
    if (StringUtils.isNotBlank(approvalContent)) {
        phAuth.setApprovalContent(approvalContent);
    }
    phAuth.setStatus(status);
    phAuth.setApproval(new Date());
    phAuth.setApprover(authentication.getName());
    save(phAuth);
    PhUserInfo phUserInfo = phUserInfoService.findByUnionid(phAuth.getUnionid());
    if ("1".equals(status)) {
        phUserInfo.setIsAuth("1");
        phUserInfo.setPhone(phAuth.getPhone());
        phUserInfo.setPosition(phAuth.getPosition());
        phUserInfo.setIdcardObverse(phAuth.getIdcardObverse());
        phUserInfo.setIdcardPositive(phAuth.getIdcardPositive());
        phUserInfo.setRealName(phAuth.getRealName());
        phUserInfo.setInCert(phAuth.getInCert());
        phUserInfo.setCompanyName(phAuth.getCompanyName());
        phUserInfo.setCreditScore(50L);
        phUserInfoService.save(phUserInfo);
        PhCreditDetail creditDetail = new PhCreditDetail();
        creditDetail.setChannel("认证通过");
        creditDetail.setCreateName(phAuth.getApprover());
        creditDetail.setCreateTime(new Date());
        creditDetail.setOrderNo(null);
        creditDetail.setRemark(null);
        creditDetail.setScore(50L);
        creditDetail.setType("0");
        creditDetail.setUnionid(phAuth.getUnionid());
        phCreditDetailService.save(creditDetail);
    }
    String openid = phUserInfo.getMiniopenid();
    String formid = phAuth.getFormId();
    // 模块消息配置
    Template tem = new Template();
    tem.setTemplateId("3XfYDBQWMwRfEsvmRhemNtZVy-j5dFoNPXoCz7t4QwI");
    tem.setFormId(formid);
    tem.setTopColor("#00DD00");
    tem.setToUser(openid);
    tem.setPage("pages/index/index");
    String result = "您的身份审核已通过";
    String content = "恭喜！可以借衣啦！";
    if ("2".equals(status)) {
        result = "您的身份审核未通过";
        content = approvalContent;
        PhCode phCode = phCodeService.findOne(phAuth.getCodeId());
        phCode.setStatus("0");
        phCodeService.save(phCode);
    }
    List<TemplateParam> paras = new ArrayList<TemplateParam>();
    paras.add(new TemplateParam("keyword1", result, "#0044BB"));
    paras.add(new TemplateParam("keyword2", content, "#0044BB"));
    // tem.setEmphasis_keyword("keyword1.DATA");
    tem.setTemplateParamList(paras);
    // 推送模版消息
    sendTemplateMsg(tem, getToken());
    return true;
}


public String getToken(){
    String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx8dac79ed90eb9311&secret=8e2d761b60cd91b7ca8d3883c378fa0d";
    String result = HttpClientUtil.post(requestUrl, "");
    JSONObject jsonObject = JSON.parseObject(result);
    if (jsonObject != null) {
        String access_token = jsonObject.getString("access_token");
        return access_token;
    } else {
        return "";
    }
}


@Override
public PhAuth save(PhAuth phAuth){
    return phAuthRepository.save(phAuth);
}


@Override
public PhAuth findOne(Long id){
    return phAuthRepository.findOne(id);
}


public void sendTemplateMsg(Template template,String token){
    String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
    requestUrl = requestUrl.replace("ACCESS_TOKEN", token);
    String jsonString = template.toJSON();
    String result = HttpClientUtil.post(requestUrl, jsonString);
    JSONObject jsonResult = JSON.parseObject(result);
    if (jsonResult != null) {
        int errorCode = jsonResult.getIntValue("errcode");
        String errorMessage = jsonResult.getString("errmsg");
        if (errorCode == 0) {
            logger.info("模板消息发送成功:" + jsonResult);
        } else {
            logger.info("模板消息发送失败:" + errorCode + "," + errorMessage);
        }
    } else {
        logger.info("模板消息发送失败:" + jsonResult);
    }
}


@Override
public Page<PhAuth> findByPage(String status,String nickName,String unionid,Pageable page){
    return phAuthRepository.findAll(new Specification<PhAuth>() {

        @Override
        public Predicate toPredicate(Root<PhAuth> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(status)) {
                params.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if (StringUtils.isNotBlank(nickName)) {
                params.add(criteriaBuilder.like(root.get("nickname"), "%" + nickName + "%"));
            }
            if (StringUtils.isNotBlank(unionid)) {
                params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, page);
}


@Override
public Predicate toPredicate(Root<PhAuth> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(status)) {
        params.add(criteriaBuilder.equal(root.get("status"), status));
    }
    if (StringUtils.isNotBlank(nickName)) {
        params.add(criteriaBuilder.like(root.get("nickname"), "%" + nickName + "%"));
    }
    if (StringUtils.isNotBlank(unionid)) {
        params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


}