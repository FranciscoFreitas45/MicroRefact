package com.alipay.util;
 import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.NameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import com.alipay.config.AlipayConfig;
import com.alipay.util.httpClient.HttpProtocolHandler;
import com.alipay.util.httpClient.HttpRequest;
import com.alipay.util.httpClient.HttpResponse;
import com.alipay.util.httpClient.HttpResultType;
import com.alipay.sign.MD5;
public class AlipaySubmit {

 private  String ALIPAY_GATEWAY_NEW;


public NameValuePair[] generatNameValuePair(Map<String,String> properties){
    NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
    int i = 0;
    for (Map.Entry<String, String> entry : properties.entrySet()) {
        nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
    }
    return nameValuePair;
}


public String buildRequest(String strParaFileName,String strFilePath,Map<String,String> sParaTemp){
    // 待请求参数数组
    Map<String, String> sPara = buildRequestPara(sParaTemp);
    HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
    HttpRequest request = new HttpRequest(HttpResultType.BYTES);
    // 设置编码集
    request.setCharset(AlipayConfig.input_charset);
    request.setParameters(generatNameValuePair(sPara));
    request.setUrl(ALIPAY_GATEWAY_NEW + "_input_charset=" + AlipayConfig.input_charset);
    HttpResponse response = httpProtocolHandler.execute(request, strParaFileName, strFilePath);
    if (response == null) {
        return null;
    }
    String strResult = response.getStringResult();
    return strResult;
}


public Map<String,String> buildRequestPara(Map<String,String> sParaTemp){
    // 除去数组中的空值和签名参数
    Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
    // 生成签名结果
    String mysign = buildRequestMysign(sPara);
    // 签名结果与签名方式加入请求提交参数组中
    sPara.put("sign", mysign);
    sPara.put("sign_type", AlipayConfig.sign_type);
    return sPara;
}


public String buildRequestMysign(Map<String,String> sPara){
    // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
    String prestr = AlipayCore.createLinkString(sPara);
    String mysign = "";
    if (AlipayConfig.sign_type.equals("MD5")) {
        mysign = MD5.sign(prestr, AlipayConfig.key, AlipayConfig.input_charset);
    }
    return mysign;
}


public String query_timestamp(){
    // 构造访问query_timestamp接口的URL串
    String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayConfig.partner + "&_input_charset" + AlipayConfig.input_charset;
    StringBuffer result = new StringBuffer();
    SAXReader reader = new SAXReader();
    Document doc = reader.read(new URL(strUrl).openStream());
    List<Node> nodeList = doc.selectNodes("//alipay/*");
    for (Node node : nodeList) {
        // 截取部分不需要解析的信息
        if (node.getName().equals("is_success") && node.getText().equals("T")) {
            // 判断是否有成功标示
            List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
            for (Node node1 : nodeList1) {
                result.append(node1.getText());
            }
        }
    }
    return result.toString();
}


}