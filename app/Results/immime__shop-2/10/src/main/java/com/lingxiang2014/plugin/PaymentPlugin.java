package com.lingxiang2014.plugin;
 import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.lingxiang2014.Setting;
import com.lingxiang2014.entity.PluginConfig;
import com.lingxiang2014.service.PluginConfigService;
import com.lingxiang2014.util.SettingUtils;
public class PaymentPlugin implements Comparable<PaymentPlugin>{

 public  String PAYMENT_NAME_ATTRIBUTE_NAME;

 public  String FEE_TYPE_ATTRIBUTE_NAME;

 public  String FEE_ATTRIBUTE_NAME;

 public  String LOGO_ATTRIBUTE_NAME;

 public  String DESCRIPTION_ATTRIBUTE_NAME;

@Resource(name = "pluginConfigServiceImpl")
 private  PluginConfigService pluginConfigService;


public String getNotifyMessage(String sn,NotifyMethod notifyMethod,HttpServletRequest request)


public String getName()


public String getAuthor()


public Map<String,Object> getParameterMap(String sn,String description,HttpServletRequest request)


public String getId(){
    return getClass().getAnnotation(Component.class).value();
}


public String getSiteUrl()


public int compareTo(PaymentPlugin paymentPlugin){
    return new CompareToBuilder().append(getOrder(), paymentPlugin.getOrder()).append(getId(), paymentPlugin.getId()).toComparison();
}


public String getDescription(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getAttribute(DESCRIPTION_ATTRIBUTE_NAME) : null;
}


public String getAttribute(String name){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getAttribute(name) : null;
}


public String post(String url,Map<String,Object> parameterMap){
    Assert.hasText(url);
    String result = null;
    HttpClient httpClient = new DefaultHttpClient();
    try {
        HttpPost httpPost = new HttpPost(url);
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
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        HttpResponse httpResponse = httpClient.execute(httpPost);
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


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
}


public Integer getTimeout()


public BigDecimal calculateAmount(BigDecimal amount){
    return amount.add(calculateFee(amount)).setScale(2, RoundingMode.UP);
}


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


public String joinValue(Map<String,Object> map,String prefix,String suffix,String separator,boolean ignoreEmptyValue,String ignoreKeys){
    List<String> list = new ArrayList<String>();
    if (map != null) {
        for (Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = ConvertUtils.convert(entry.getValue());
            if (StringUtils.isNotEmpty(key) && !ArrayUtils.contains(ignoreKeys, key) && (!ignoreEmptyValue || StringUtils.isNotEmpty(value))) {
                list.add(value != null ? value : "");
            }
        }
    }
    return (prefix != null ? prefix : "") + StringUtils.join(list, separator) + (suffix != null ? suffix : "");
}


public String getLogo(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getAttribute(LOGO_ATTRIBUTE_NAME) : null;
}


public String getInstallUrl()


public PluginConfig getPluginConfig(){
    return pluginConfigService.findByPluginId(getId());
}


public String joinKeyValue(Map<String,Object> map,String prefix,String suffix,String separator,boolean ignoreEmptyValue,String ignoreKeys){
    List<String> list = new ArrayList<String>();
    if (map != null) {
        for (Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = ConvertUtils.convert(entry.getValue());
            if (StringUtils.isNotEmpty(key) && !ArrayUtils.contains(ignoreKeys, key) && (!ignoreEmptyValue || StringUtils.isNotEmpty(value))) {
                list.add(key + "=" + (value != null ? value : ""));
            }
        }
    }
    return (prefix != null ? prefix : "") + StringUtils.join(list, separator) + (suffix != null ? suffix : "");
}


public RequestMethod getRequestMethod()


public boolean getIsEnabled(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getIsEnabled() : false;
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


@Override
public boolean equals(Object obj){
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    if (this == obj) {
        return true;
    }
    PaymentPlugin other = (PaymentPlugin) obj;
    return new EqualsBuilder().append(getId(), other.getId()).isEquals();
}


public String getPaymentName(){
    PluginConfig pluginConfig = getPluginConfig();
    return pluginConfig != null ? pluginConfig.getAttribute(PAYMENT_NAME_ATTRIBUTE_NAME) : null;
}


public boolean verifyNotify(String sn,NotifyMethod notifyMethod,HttpServletRequest request)


public String getNotifyUrl(String sn,NotifyMethod notifyMethod){
    Setting setting = SettingUtils.get();
    if (notifyMethod == null) {
        return setting.getSiteUrl() + "/payment/notify/" + NotifyMethod.general + "/" + sn + ".jhtml";
    }
    return setting.getSiteUrl() + "/payment/notify/" + notifyMethod + "/" + sn + ".jhtml";
}


}