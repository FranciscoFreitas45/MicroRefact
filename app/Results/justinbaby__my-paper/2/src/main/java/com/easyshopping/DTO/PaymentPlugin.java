package com.easyshopping.DTO;
 import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.easyshopping.Setting;
import com.easyshopping.entity.Payment;
import com.easyshopping.entity.PluginConfig;
import com.easyshopping.service.PaymentService;
import com.easyshopping.service.PluginConfigService;
import com.easyshopping.util.SettingUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
public class PaymentPlugin implements Comparable<PaymentPlugin>{

 public  String PAYMENT_NAME_ATTRIBUTE_NAME;

 public  String FEE_TYPE_ATTRIBUTE_NAME;

 public  String FEE_ATTRIBUTE_NAME;

 public  String LOGO_ATTRIBUTE_NAME;

 public  String DESCRIPTION_ATTRIBUTE_NAME;

 private  PluginConfigService pluginConfigService;

 private  PaymentService paymentService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://16";


public String getNotifyMessage(String sn,NotifyMethod notifyMethod,HttpServletRequest request)


public String getName()


public String getAuthor()


public Map<String,Object> getParameterMap(String sn,String description,HttpServletRequest request)


public String getId(){
    return getClass().getAnnotation(Component.class).value();
}


public String getSiteUrl()


public String getDescription(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getAttribute(DESCRIPTION_ATTRIBUTE_NAME) : null;
}


public String getAttribute(String name){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getAttribute(name) : null;
}


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
}


public Integer getTimeout()


public String get(String url,Map<String,Object> parameterMap){
    Assert.hasText(url);
    String result = null;
    HttpClient httpClient = new DefaultHttpClient();
    try {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (parameterMap != null) {
            for (Entry<String, Object> entry : parameterMap.entrySet()) {
                String name = entry.getKey();
                String value = ConvertUtils.convert(entry.getValue());
                if (StringUtils.isNotEmpty(name)) {
                    nameValuePairs.add(new BasicNameValuePair(name, value));
                }
            }
        }
        HttpGet httpGet = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?") + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        result = EntityUtils.toString(httpEntity);
        EntityUtils.consume(httpEntity);
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        httpClient.getConnectionManager().shutdown();
    }
    return result;
}


public BigDecimal getFee(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? new BigDecimal(pluginConfig.getAttribute(FEE_ATTRIBUTE_NAME)) : null;
}


public String getVersion()


public String getRequestUrl()


public String getLogo(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getAttribute(LOGO_ATTRIBUTE_NAME) : null;
}


public String getInstallUrl()


public PluginConfig getPluginConfig(){
    return pluginConfigService.findByPluginId(getId());
}


public RequestMethod getRequestMethod()


public boolean getIsEnabled(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getIsEnabled() : false;
}


public String getUninstallUrl()


public String getSettingUrl()


public String getRequestCharset()


public FeeType getFeeType(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? FeeType.valueOf(pluginConfig.getAttribute(FEE_TYPE_ATTRIBUTE_NAME)) : null;
}


public boolean getIsInstalled(){
    return pluginConfigService.pluginIdExists(getId());
}


public Integer getOrder(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getOrder() : null;
}


public String getPaymentName(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getAttribute(PAYMENT_NAME_ATTRIBUTE_NAME) : null;
}


public Payment getPayment(String sn){
    return paymentService.findBySn(sn);
}


public String getNotifyUrl(String sn,NotifyMethod notifyMethod){
    Setting setting = SettingUtils.get();
    if (notifyMethod == null) {
        return setting.getSiteUrl() + "/payment/notify/" + NotifyMethod.general + "/" + sn + ".jhtml";
    }
    return setting.getSiteUrl() + "/payment/notify/" + notifyMethod + "/" + sn + ".jhtml";
}


public BigDecimal calculateFee(BigDecimal amount){
    Setting setting = SettingUtils.get();
    BigDecimal fee;
    if (getFeeType() == FeeType.scale) {
        fee = amount.multiply(getFee());
    } else {
        fee = getFee();
    }
    return setting.setScale(fee);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/calculateFee"))

.queryParam("amount",amount)
;
BigDecimal aux = restTemplate.getForObject(builder.toUriString(),BigDecimal.class);
return aux;
}


}