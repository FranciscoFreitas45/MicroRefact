package com.circle.controller.weixin;
 import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.InputSource;
import com.circle.pojo.user.User;
import com.circle.service.login.ILoginService;
import com.circle.service.user.IUserService;
import com.circle.utils.HttpKit;
import com.circle.utils.MD5Util;
import com.circle.utils.XMLSerializer;
import com.xwtec.xwserver.constant.ConstantBusiKeys;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.CommonUtil;
import com.xwtec.xwserver.util.StringUtil;
import com.xwtec.xwserver.util.json.JSONObject;
import com.Interface.ILoginService;
import com.Interface.IUserService;
import com.DTO.JSONObject;
import com.DTO.User;
@Controller
@RequestMapping("weixin")
public class WeixinController {

 public  Logger logger;

 private  String ORDER_URL;

 private  String QUERY_ORDER_URL;

 private  String ACCESSTOKEN_URL;

 private  String RESUND_URL;

 private  String RESUND_QUERY_URL;

 private  String APP_ID;

 private  String APPSECRET;

 private  String MCH_ID;

 private  String SIGN_KEY;

 private  String CERT;

 private  String NOTIFY_URL;

 public  String ACCESS_TOKEN_KEY;

 public  String TICKET_KEY;

@Resource
 private ILoginService loginService;

@Resource
 private IUserService userService;


public JSONObject queryUserInfo(String accessToken,String openId){
    JSONObject js = new JSONObject();
    try {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN");
        if (!StringUtil.isEmpty(jsonStr)) {
            JSONObject json = JSONObject.fromObject(jsonStr);
            js = json;
        } else {
            js.put("errcode", 40029);
            js.put("errmsg", "invalid code");
        }
    } catch (Exception e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    /*js.put("openid", "omQhCtx2dKBVOd3qu3PGmnlpu71c");
		js.put("nickname", "牛b");
		js.put("sex", 1);
		js.put("language", "zh_CN");
		js.put("headimgurl", "http://wx.qlogo.cn/mmopen/JXw9pQTKn17vKpzc6ePicNeJPUufibv6ZyO7X83OynJ3D77yOHib0Cvic2chJVlv4uX7rV4C7iaScXiaXFlfG0mntQYw/0");
		js.put("country","中国香港");*/
    return js;
}


public String generateInviteCode(){
    String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
    String code = "";
    while (code.length() < 5) {
        if (Math.random() < 0.5)
            code += (int) (Math.random() * 10);
        else
            code += letter[(int) (Math.random() * 26)];
    }
    User user = userService.getUserByInviteCode(code);
    if (user != null)
        generateInviteCode();
    return code;
}


public String createSign(Map<String,String> params){
    Set<String> keysSet = params.keySet();
    Object[] keys = keysSet.toArray();
    // 给参数排序，然后拼接生成签名的字符串
    Arrays.sort(keys);
    StringBuffer temp = new StringBuffer();
    boolean first = true;
    for (Object key : keys) {
        if (first) {
            first = false;
        } else {
            temp.append("&");
        }
        temp.append(key).append("=");
        Object value = params.get(key);
        String valueString = "";
        if (null != value) {
            valueString = value.toString();
        }
        temp.append(valueString);
    }
    String key = SIGN_KEY;
    temp.append("&key=" + key);
    // logger.info("[WeixinController.createSign] stringSign:" + temp);
    String signValue = MD5Util.MD5Encode(temp.toString(), "UTF-8").toUpperCase();
    // logger.info("[WeixinController.createSign] sign:" + signValue);
    return signValue;
}


public String getIp(HttpServletRequest request){
    if (request == null)
        return "";
    String ip = request.getHeader("X-Requested-For");
    if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("X-Forwarded-For");
    }
    if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
    }
    if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
    }
    return ip;
}


@RequestMapping("refundorder.action")
public ModelAndView refundOrder(ModelAndView mav,HttpServletRequest request,String outTradeNo){
    logger.info("[WeixinController.order] outTradeNo:" + outTradeNo);
    try {
        String nonceStr = createNonceStr();
        String outRefundNo = createNonceStr();
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", APP_ID);
        params.put("mch_id", MCH_ID);
        params.put("nonce_str", nonceStr);
        params.put("out_trade_no", outTradeNo);
        params.put("out_refund_no", outRefundNo);
        params.put("total_fee", "1");
        params.put("refund_fee", "1");
        params.put("op_user_id", MCH_ID);
        String param = "<xml>" + "<appid>" + APP_ID + "</appid>" + "<mch_id>" + MCH_ID + "</mch_id>" + "<nonce_str>" + nonceStr + "</nonce_str>" + "<out_trade_no>" + "5809632147852" + "</out_trade_no>" + "<out_refund_no>" + "58096321478521000" + "</out_refund_no>" + "<total_fee>" + 1 + "</total_fee>" + "<refund_fee>" + 1 + "</refund_fee>" + "<op_user_id>" + MCH_ID + "</op_user_id>" + "<sign>" + createSign(params) + "</sign>" + "</xml>";
        logger.info("[WeixinController.refundOrder] refund order param:" + param);
        // 指定读取证书格式为PKCS12
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        // 读取本机存放的PKCS12证书文件
        FileInputStream instream = new FileInputStream(new File(CERT));
        try {
            // 指定PKCS12的密码(商户ID)
            keyStore.load(instream, MCH_ID.toCharArray());
        } finally {
            instream.close();
        }
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, MCH_ID.toCharArray()).build();
        // Allow TLSv1 protocol only
        // 指定TLS版本
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        // 设置httpclient的SSLSocketFactory
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            HttpPost httpPost = new HttpPost(RESUND_URL);
            StringEntity reqEntity = new StringEntity(param);
            // 设置类型
            reqEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                logger.info("[WeixinController.refundOrder] response StatusLine:" + response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        System.out.println(text);
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    } catch (Exception e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    mav.setViewName("/weixin/order_info.jsp");
    return mav;
}


@RequestMapping("queryrefundorder.action")
public ModelAndView queryRefundOrder(ModelAndView mav,HttpServletRequest request,HttpServletResponse response,String outTradeNo){
    logger.info("[WeixinController.queryRefundOrder] outTradeNo:" + outTradeNo);
    try {
        String nonceStr = createNonceStr();
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", APP_ID);
        params.put("mch_id", MCH_ID);
        params.put("nonce_str", nonceStr);
        params.put("out_trade_no", "5809632147852");
        String param = "<xml>" + "<appid>" + APP_ID + "</appid>" + "<mch_id>" + MCH_ID + "</mch_id>" + "<nonce_str>" + nonceStr + "</nonce_str>" + "<out_trade_no>" + outTradeNo + "</out_trade_no>" + "<sign>" + createSign(params) + "</sign>" + "</xml>";
        logger.info("[WeixinController.queryOrder] query order param:" + param);
        String jsonStr = HttpKit.post(RESUND_QUERY_URL, param);
        logger.info("[WeixinController.queryOrder] query order msg:" + jsonStr);
        XMLSerializer serializer = new XMLSerializer();
        JSONObject jo = JSONObject.fromObject(serializer.read(jsonStr));
        mav.addObject("order_msg", jo);
    } catch (Exception e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    mav.setViewName("/weixin/order_info.jsp");
    return mav;
}


public List<Map<String,String>> parseXmlToList2(String xml){
    List<Map<String, String>> argMapList = new ArrayList<Map<String, String>>();
    Map<String, String> retMap = new HashMap<String, String>();
    try {
        StringReader read = new StringReader(xml);
        // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        // 创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        // 通过输入源构造一个Document
        Document doc = (Document) sb.build(source);
        // 指向根节点
        Element root = doc.getRootElement();
        List<Element> es = root.getChildren();
        if (es != null && es.size() != 0) {
            for (Element element : es) {
                retMap.put(element.getName(), element.getValue());
            }
        }
        argMapList.add(retMap);
    } catch (Exception e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return argMapList;
}


public void main(String[] args){
    String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc329708d079f1e65&redirect_uri=" + URLEncoder.encode("http://www.comefarm.com/mobile/usercenter_order.html") + "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
    System.out.println(url);
}


public String getAccessToken(){
    String accessTokenBody = "";
    try {
        // 获取access_token URL地址
        String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APPSECRET;
        logger.info("[WeixinController.getAccessToken] getAccessTokenUrl:" + getAccessTokenUrl);
        // 获取请求access_token URL返回
        accessTokenBody = HttpKit.get(getAccessTokenUrl);
        logger.info("[WeixinController.getAccessToken] get access_token msg:" + accessTokenBody);
    } catch (Exception e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return accessTokenBody;
}


@RequestMapping("queryorder.action")
public JSONObject queryOrder(String outTradeNo){
    logger.info("[WeixinController.queryOrder] outTradeNo:" + outTradeNo);
    try {
        String appId = APP_ID;
        String mchId = MCH_ID;
        String nonceStr = createNonceStr();
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appId);
        params.put("mch_id", mchId);
        params.put("nonce_str", nonceStr);
        params.put("out_trade_no", outTradeNo);
        String param = "<xml>" + "<appid>" + appId + "</appid>" + "<mch_id>" + mchId + "</mch_id>" + "<nonce_str>" + nonceStr + "</nonce_str>" + "<out_trade_no>" + outTradeNo + "</out_trade_no>" + "<sign>" + createSign(params) + "</sign>" + "</xml> ";
        // logger.info("[WeixinController.queryOrder] query order param:" + param);
        String jsonStr = HttpKit.post(QUERY_ORDER_URL, param);
        // logger.info("[WeixinController.queryOrder] query order msg:" + jsonStr);
        // System.out.println("[WeixinController.queryOrder  原始报文] query order msg:" + jsonStr);
        XMLSerializer serializer = new XMLSerializer();
        JSONObject jo = JSONObject.fromObject(serializer.read(jsonStr));
        System.out.println("[QUERY ORDER STATUS]: " + jo.toString());
        return jo;
    } catch (Exception e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return null;
    }
}


public String createNonceStr(){
    String nonceStr = MD5Util.MD5Encode(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssS") + new Random().nextInt(), "UTF-8");
    return nonceStr;
}


@RequestMapping("getuserinfo.action")
@ResponseBody
public JSONObject getWeixinOpenId(String code,HttpServletRequest request){
    logger.info("[WeixinController.getWeixinOpenId] code:" + code);
    HttpSession session = request.getSession();
    JSONObject js = new JSONObject();
    User user = null;
    try {
        user = (User) session.getAttribute(ConstantBusiKeys.SessionKey.SYS_USER);
        if (user == null) {
            String jsonStr = HttpKit.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID + "&secret=" + APPSECRET + "&code=" + code + "&grant_type=authorization_code");
            logger.info("[WeixinController.getWeixinOpenId] get access_token msg:" + jsonStr);
            if (!StringUtil.isEmpty(jsonStr)) {
                JSONObject json = JSONObject.fromObject(jsonStr);
                if (json.has("errcode")) {
                    // {"errcode":40029,"errmsg":"invalid code"}
                    js = json;
                } else {
                    String openid = json.getString("openid");
                    if (!StringUtil.isEmpty(openid)) {
                        // 根据openid查询用户信息
                        user = loginService.login(openid);
                        // 如果用户信息不为空，直接返回用户信息
                        if (user != null) {
                            session.setAttribute(ConstantBusiKeys.SessionKey.SYS_USER, user);
                            // 记录最后登录质检
                            try {
                                loginService.updateUserLastLoginTimerByWeixin(openid);
                            } catch (SPTException e) {
                                logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
                            }
                        // 如果用户信息为空，注册用户信息
                        } else {
                            js = queryUserInfo(json.getString("access_token"), openid);
                            User registUser = new User();
                            registUser.setWeixinOpenid(js.getString("openid"));
                            registUser.setNickname(js.getString("nickname"));
                            registUser.setWeixinName(js.getString("nickname"));
                            registUser.setWeixinImage(js.getString("headimgurl"));
                            registUser.setName(js.getString("nickname"));
                            registUser.setInviteUserId(95);
                            String inviteCode = generateInviteCode();
                            registUser.setInviteCode(inviteCode);
                            /**
                             * 添加微信用户信息
                             */
                            userService.addWeixinUser(registUser);
                            /**
                             * 查询微信用户信息
                             */
                            User userinfo = loginService.login(openid);
                            session.setAttribute(ConstantBusiKeys.SessionKey.SYS_USER, userinfo);
                        }
                    }
                }
            } else {
                js.put("errcode", 40029);
                js.put("errmsg", "invalid code");
            }
        }
        user = (User) session.getAttribute(ConstantBusiKeys.SessionKey.SYS_USER);
        js = JSONObject.fromObject(user);
    } catch (Exception e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return js;
}


public JSONObject toPay(String prepay_id){
    logger.info("[WeixinController.toPayt] prepay_id:" + prepay_id);
    JSONObject jsonReturn = new JSONObject();
    try {
        String appid = APP_ID;
        String timeStamp = System.currentTimeMillis() + "";
        String nonceStr = createNonceStr();
        String package2 = "prepay_id=" + prepay_id;
        Map<String, String> params = new HashMap<String, String>();
        params.put("appId", appid);
        params.put("timeStamp", timeStamp);
        params.put("nonceStr", nonceStr);
        params.put("package", package2);
        params.put("signType", "MD5");
        jsonReturn.put("appid", appid);
        jsonReturn.put("timeStamp", timeStamp);
        jsonReturn.put("nonceStr", nonceStr);
        jsonReturn.put("package", package2);
        jsonReturn.put("paySign", createSign(params));
    } catch (Exception e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    // mav.setViewName("/weixin/pay.jsp");
    return jsonReturn;
}


public String getTicket(String accessToken){
    logger.info("[WeixinController.getTicket] accessToken:" + accessToken);
    String ticketBody = "";
    try {
        // 获取公众号用于调用微信JS接口的临时票据URL
        String getTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
        logger.info("[WeixinController.getTicket] getTicketUrl:" + getTicketUrl);
        // 请求URL获取返回值
        ticketBody = HttpKit.get(getTicketUrl);
        logger.info("[WeixinController.getTicket] get ticket msg:" + ticketBody);
    } catch (Exception e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return ticketBody;
}


public JSONObject order(String inputTotalFee,String inputOutTradeNo,String openid,String inputBody){
    logger.info("[WeixinController.order] body:" + inputBody + ",outTradeNo:" + inputOutTradeNo + ",totalFee:" + inputTotalFee);
    JSONObject jsonReturn = new JSONObject();
    try {
        String appId = APP_ID;
        String mchId = MCH_ID;
        String nonceStr = createNonceStr();
        String body = inputBody;
        String outTradeNo = inputOutTradeNo;
        String totalFee = inputTotalFee;
        // getIp(request);
        String spbillCreateIp = "127.0.0.1";
        String notifyUrl = NOTIFY_URL;
        String tradeType = "JSAPI";
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appId);
        params.put("mch_id", mchId);
        params.put("nonce_str", nonceStr);
        params.put("body", body);
        params.put("out_trade_no", outTradeNo);
        params.put("total_fee", totalFee);
        params.put("spbill_create_ip", spbillCreateIp);
        params.put("notify_url", notifyUrl);
        params.put("trade_type", tradeType);
        params.put("openid", openid);
        String param = "<xml>" + "<appid>" + appId + "</appid>" + "<body>" + body + "</body>" + "<mch_id>" + mchId + "</mch_id>" + "<nonce_str>" + nonceStr + "</nonce_str>" + "<notify_url>" + notifyUrl + "</notify_url>" + "<openid>" + openid + "</openid>" + "<out_trade_no>" + outTradeNo + "</out_trade_no>" + "<spbill_create_ip>" + spbillCreateIp + "</spbill_create_ip>" + "<total_fee>" + totalFee + "</total_fee>" + "<trade_type>" + tradeType + "</trade_type>" + "<sign>" + createSign(params) + "</sign>" + "</xml> ";
        // logger.info("[WeixinController.order] order param:" + param);
        String jsonStr = HttpKit.post(ORDER_URL, param);
        logger.info("[WeixinController.order] order msg:" + jsonStr);
        XMLSerializer serializer = new XMLSerializer();
        JSONObject jo = JSONObject.fromObject(serializer.read(jsonStr));
        jsonReturn = jo;
    } catch (Exception e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    // mav.setViewName("/weixin/to_order.jsp");
    return jsonReturn;
}


}