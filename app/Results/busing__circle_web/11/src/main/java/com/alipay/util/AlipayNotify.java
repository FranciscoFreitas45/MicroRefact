package com.alipay.util;
 import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import com.alipay.config.AlipayConfig;
import com.alipay.sign.MD5;
public class AlipayNotify {

 private  String HTTPS_VERIFY_URL;


public boolean getSignVeryfy(Map<String,String> Params,String sign){
    // 过滤空值、sign与sign_type参数
    Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
    // 获取待签名字符串
    String preSignStr = AlipayCore.createLinkString(sParaNew);
    // 获得签名验证结果
    boolean isSign = false;
    if (AlipayConfig.sign_type.equals("MD5")) {
        isSign = MD5.verify(preSignStr, sign, AlipayConfig.key, AlipayConfig.input_charset);
    }
    return isSign;
}


public String verifyResponse(String notify_id){
    // 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
    String partner = AlipayConfig.partner;
    String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;
    return checkUrl(veryfy_url);
}


public boolean verify(Map<String,String> params){
    // 判断responsetTxt是否为true，isSign是否为true
    // responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
    // isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
    String responseTxt = "true";
    if (params.get("notify_id") != null) {
        String notify_id = params.get("notify_id");
        responseTxt = verifyResponse(notify_id);
    }
    String sign = "";
    if (params.get("sign") != null) {
        sign = params.get("sign");
    }
    boolean isSign = getSignVeryfy(params, sign);
    // 写日志记录（若要调试，请取消下面两行注释）
    // String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign + "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
    // AlipayCore.logResult(sWord);
    if (isSign && responseTxt.equals("true")) {
        return true;
    } else {
        return false;
    }
}


public String checkUrl(String urlvalue){
    String inputLine = "";
    try {
        URL url = new URL(urlvalue);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        inputLine = in.readLine().toString();
    } catch (Exception e) {
        e.printStackTrace();
        inputLine = "";
    }
    return inputLine;
}


}