import java.io.ByteArrayInputStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.sf.csim.express.service.CallExpressServiceTools;
import cn.offway.athena.dto.sf.ReqAddOrder;
import cn.offway.athena.utils.JsonResult;
@Service
public class SfExpressService {

 private  Logger logger;

@Value("${sf.url}")
 private  String url;

@Value("${sf.client-code}")
 private  String clientCode;

@Value("${sf.checkword}")
 private  String checkword;

 private  String REQ_ADD_ORDER;


@SuppressWarnings("static-access")
public JsonResult addOrder(ReqAddOrder addOrder){
    try {
        String req = REQ_ADD_ORDER;
        req = req.replaceAll("CLIENTCODE", clientCode);
        req = req.replaceAll("ORDERID", addOrder.getOrder_id());
        req = req.replaceAll("J_CONTACT", addOrder.getJ_contact());
        req = req.replaceAll("J_TEL", addOrder.getJ_tel());
        req = req.replaceAll("J_PROVINCE", addOrder.getJ_province());
        req = req.replaceAll("J_CITY", addOrder.getJ_city());
        req = req.replaceAll("J_COUNTY", addOrder.getJ_county());
        req = req.replaceAll("J_ADDRESS", addOrder.getJ_address());
        req = req.replaceAll("D_CONTACT", addOrder.getD_contact());
        req = req.replaceAll("D_TEL", addOrder.getD_tel());
        req = req.replaceAll("D_PROVINCE", null == addOrder.getD_province() ? "" : addOrder.getD_province());
        req = req.replaceAll("D_CITY", null == addOrder.getD_city() ? "" : addOrder.getD_city());
        req = req.replaceAll("D_COUNTY", null == addOrder.getD_county() ? "" : addOrder.getD_county());
        req = req.replaceAll("D_ADDRESS", addOrder.getD_address());
        // 付款方式：1:寄方付2:收方付3:第三方付
        req = req.replaceAll("PAY_METHOD", addOrder.getPay_method());
        req = req.replaceAll("SENDSTARTTIME", addOrder.getSendstarttime());
        req = req.replaceAll("ORDER_SOURCE", addOrder.getOrder_source());
        req = req.replaceAll("REMARK", addOrder.getRemark());
        logger.info("请求报文：" + req);
        CallExpressServiceTools client = CallExpressServiceTools.getInstance();
        String respXml = client.callSfExpressServiceByCSIM(url, req, clientCode, checkword);
        logger.info("响应报文：" + respXml);
        SAXReader reader = new SAXReader();
        Document document = reader.read(new ByteArrayInputStream(respXml.getBytes()));
        Element response = document.getRootElement();
        String head = response.elementText("Head");
        if ("OK".equals(head)) {
            Element orderResponse = response.element("Body").element("OrderResponse");
            String mailno = orderResponse.attributeValue("mailno");
            return new JsonResult("200", null, mailno);
        } else {
            Element error = response.element("ERROR");
            String code = error.attributeValue("code");
            String text = error.getText();
            return new JsonResult(code, text, null);
        }
    } catch (Exception e) {
        e.printStackTrace();
        return new JsonResult("500", "系统错误", null);
    }
}


}