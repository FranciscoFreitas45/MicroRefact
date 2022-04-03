package com.easyshopping.plugin.tenpayDirect;
 import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import com.easyshopping.entity.Payment;
import com.easyshopping.entity.PluginConfig;
import com.easyshopping.plugin.PaymentPlugin;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;
@Component("tenpayDirectPlugin")
public class TenpayDirectPlugin extends PaymentPlugin{


@Override
public String getVersion(){
    return "1.0";
}


@Override
public String getRequestUrl(){
    return "https://gw.tenpay.com/gateway/pay.htm";
}


@Override
public String getNotifyMessage(String sn,NotifyMethod notifyMethod,HttpServletRequest request){
    if (notifyMethod == NotifyMethod.async) {
        return "Success";
    }
    return null;
}


@Override
public String getName(){
    return "财付通(即时交易)";
}


@Override
public String getAuthor(){
    return "EASY SHOPPING";
}


@Override
public String getInstallUrl(){
    return "tenpay_direct/install.jhtml";
}


@Override
public Map<String,Object> getParameterMap(String sn,String description,HttpServletRequest request){
    PluginConfig pluginConfig = getPluginConfig();
    Payment payment = getPayment(sn);
    Map<String, Object> parameterMap = new HashMap<String, Object>();
    parameterMap.put("trade_mode", "1");
    parameterMap.put("partner", pluginConfig.getAttribute("partner"));
    parameterMap.put("input_charset", "utf-8");
    parameterMap.put("sign_type", "MD5");
    parameterMap.put("return_url", getNotifyUrl(sn, NotifyMethod.sync));
    parameterMap.put("notify_url", getNotifyUrl(sn, NotifyMethod.async));
    parameterMap.put("out_trade_no", sn);
    parameterMap.put("subject", StringUtils.abbreviate(description.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 30));
    parameterMap.put("body", StringUtils.abbreviate(description.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 30));
    parameterMap.put("bank_type", "DEFAULT");
    parameterMap.put("seller_id", pluginConfig.getAttribute("partner"));
    parameterMap.put("total_fee", payment.getAmount().multiply(new BigDecimal(100)).setScale(0).toString());
    parameterMap.put("fee_type", "1");
    parameterMap.put("spbill_create_ip", request.getLocalAddr());
    parameterMap.put("attach", "easyshop");
    parameterMap.put("sign", generateSign(parameterMap));
    return parameterMap;
}


@Override
public String getSiteUrl(){
    return "#";
}


@Override
public RequestMethod getRequestMethod(){
    return RequestMethod.get;
}


@Override
public String getUninstallUrl(){
    return "tenpay_direct/uninstall.jhtml";
}


@Override
public String getSettingUrl(){
    return "tenpay_direct/setting.jhtml";
}


@Override
public String getRequestCharset(){
    return "UTF-8";
}


@Override
public Integer getTimeout(){
    return 21600;
}


@SuppressWarnings("all")
@Override
public boolean verifyNotify(String sn,NotifyMethod notifyMethod,HttpServletRequest request){
    PluginConfig pluginConfig = getPluginConfig();
    Payment payment = getPayment(sn);
    Map readOnlyMap = request.getParameterMap();
    Map writeAbleMap = new HashMap();
    writeAbleMap.putAll(readOnlyMap);
    if (generateSign(writeAbleMap).equals(request.getParameter("sign")) && pluginConfig.getAttribute("partner").equals(request.getParameter("partner")) && sn.equals(request.getParameter("out_trade_no")) && "0".equals(request.getParameter("trade_state")) && payment.getAmount().multiply(new BigDecimal(100)).compareTo(new BigDecimal(request.getParameter("total_fee"))) == 0) {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("input_charset", "utf-8");
            parameterMap.put("sign_type", "MD5");
            parameterMap.put("partner", pluginConfig.getAttribute("partner"));
            parameterMap.put("notify_id", request.getParameter("notify_id"));
            String verifyUrl = "https://gw.tenpay.com/gateway/simpleverifynotifyid.xml?input_charset=utf-8&sign_type=MD5&partner=" + pluginConfig.getAttribute("partner") + "&notify_id=" + request.getParameter("notify_id") + "&sign=" + generateSign(parameterMap);
            Document document = new SAXReader().read(new URL(verifyUrl));
            Node node = document.selectSingleNode("/root/retcode");
            if ("0".equals(node.getText().trim())) {
                return true;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    return false;
}


public String generateSign(Map<String,Object> parameterMap){
    PluginConfig pluginConfig = getPluginConfig();
    return DigestUtils.md5Hex(joinKeyValue(new TreeMap<String, Object>(parameterMap), null, "&key=" + pluginConfig.getAttribute("key"), "&", true, "sign")).toUpperCase();
}


}