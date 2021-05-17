import java.io.ByteArrayInputStream;
import java.util.Date;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.offway.athena.domain.PhOrderExpressDetail;
import cn.offway.athena.domain.PhOrderExpressInfo;
import cn.offway.athena.service.PhOrderExpressDetailService;
import cn.offway.athena.service.PhOrderExpressInfoService;
@RestController
@RequestMapping("/notify/sf")
public class NotifyController {

 private  Logger logger;

@Autowired
 private  PhOrderExpressDetailService phOrderExpressDetailService;

@Autowired
 private  PhOrderExpressInfoService phOrderExpressInfoService;


@RequestMapping("/route")
public String route(String xml){
    try {
        logger.info("快递路由推送xml:" + xml);
        // <?xml version='1.0' encoding='UTF-8'?><Request service="RoutePushService" lang="zh-CN"><Body><WaybillRoute id="865038" mailno="444010263979" orderid="QIAO-20180104004" acceptTime="2019-02-12 18:11:30" acceptAddress="深圳市" remark="备注" opCode="80"/></Body></Request>
        SAXReader reader = new SAXReader();
        Document document = reader.read(new ByteArrayInputStream(xml.getBytes()));
        Element response = document.getRootElement();
        Element WaybillRoute = response.element("Body").element("WaybillRoute");
        String id = WaybillRoute.attributeValue("id");
        String mailNo = WaybillRoute.attributeValue("mailno");
        String orderNo = WaybillRoute.attributeValue("orderid");
        // 路由节点产生的时间，格式：YYYY-MM-DD HH24:MM:SS，示例：2012-7-30 09:30:00
        String acceptTime = WaybillRoute.attributeValue("acceptTime");
        // 路由节点发生的城市
        String acceptAddress = WaybillRoute.attributeValue("acceptAddress");
        // 路由节点具体描述
        String remark = WaybillRoute.attributeValue("remark");
        // 路由信息操作码
        String opCode = WaybillRoute.attributeValue("opCode");
        PhOrderExpressDetail phOrderExpressDetail = new PhOrderExpressDetail();
        phOrderExpressDetail.setContent(remark);
        phOrderExpressDetail.setCreateTime(new Date());
        phOrderExpressDetail.setAcceptTime(acceptTime);
        phOrderExpressDetail.setExpressOrderNo(orderNo);
        phOrderExpressDetail.setMailNo(mailNo);
        phOrderExpressDetail.setAcceptAddress(acceptAddress);
        phOrderExpressDetail.setOpCode(opCode);
        phOrderExpressDetailService.save(phOrderExpressDetail);
        PhOrderExpressInfo phOrderExpressInfo = phOrderExpressInfoService.findByExpressOrderNo(orderNo);
        if ("50".equals(opCode)) {
            // 已收取快件
            phOrderExpressInfo.setStatus("3");
            phOrderExpressInfoService.save(phOrderExpressInfo);
        } else if ("80".equals(opCode)) {
            // 已签收
            phOrderExpressInfo.setStatus("4");
            phOrderExpressInfoService.save(phOrderExpressInfo);
        }
        return "<?xml version=\'1.0\' encoding=\'UTF-8\'?><Response service=\'RoutePushService\' lang=\'zh-CN\'><Head>OK</Head></Response>";
    } catch (DocumentException e) {
        e.printStackTrace();
        return "<?xml version=\'1.0\' encoding=\'UTF-8\'?><Response service=\'RoutePushService\' lang=\'zh-CN\'><Head>OK</Head></Response>";
    }
}


@RequestMapping("/state")
public String state(String content){
    logger.info("快递订单状态推送 content:" + content);
    // <Request service="PushOrderState" lang="zh-CN"><orderNo>QIAO-20180104004</orderNo><waybillNo>444010263979</waybillNo><orderStateCode>40001</orderStateCode><orderStateDesc>调度成功,收派员信息</orderStateDesc><empCode>000212</empCode><empPhone>13912345678</empPhone><netCode>755</netCode><lastTime>2019-02-12 18:14:17</lastTime><bookTime>2019-02-12 18:14:17</bookTime><carrierCode>SF</carrierCode></Request>
    SAXReader reader = new SAXReader();
    Document document = reader.read(new ByteArrayInputStream(content.getBytes()));
    Element response = document.getRootElement();
    String orderStateCode = response.elementText("orderStateCode");
    // 05-40003 已正常收件状态
    if ("40001".equals(orderStateCode) || "04-40001".equals(orderStateCode)) {
        // 调度成功
        String orderNo = response.elementText("orderNo");
        String waybillNo = response.elementText("waybillNo");
        String empPhone = response.elementText("empPhone");
        // 最晚上门时间
        String lastTime = response.elementText("lastTime");
        // 客户预约时间
        String bookTime = response.elementText("bookTime");
        PhOrderExpressInfo phOrderExpressInfo = phOrderExpressInfoService.findByExpressOrderNo(orderNo);
        phOrderExpressInfo.setExPhone(empPhone);
        phOrderExpressInfo.setLastTime(lastTime);
        phOrderExpressInfo.setStatus("2");
        phOrderExpressInfoService.save(phOrderExpressInfo);
    }
    return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Response><success>true</success></Response>";
}


}