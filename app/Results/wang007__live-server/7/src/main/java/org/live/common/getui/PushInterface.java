package org.live.common.getui;
 import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.PushSingleException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class PushInterface {

 private  Logger LOGGER;

 private  String appID;

 private  String appKey;

 private  String appSecret;

 private  String masterSecret;

 private  String url;

 private  PushInterface instance;

 private  IGtPush push;

private PushInterface() {
    push = new IGtPush(url, appKey, masterSecret);
}
public void pushToSingle(String alias,String title,String content){
    // 发送的目标
    Target target = new Target();
    target.setAlias(alias);
    target.setAppId(appID);
    // 信息
    SingleMessage message = new SingleMessage();
    message.setData(createNotificationTemplate(title, content));
    message.setPushNetWorkType(0);
    message.setOffline(true);
    message.setOfflineExpireTime(24 * 3600 * 1000);
    IPushResult iPushResult;
    try {
        iPushResult = push.pushMessageToSingle(message, target);
    } catch (PushSingleException e) {
        LOGGER.error(e.getMessage(), e);
        iPushResult = push.pushMessageToSingle(message, target, e.getRequestId());
    }
}


public NotificationTemplate createNotificationTemplate(String title,String content){
    NotificationTemplate template = new NotificationTemplate();
    template.setAppId(appID);
    template.setAppkey(appKey);
    template.setTransmissionType(1);
    Style0 style = new Style0();
    // 设置通知栏标题与内容
    style.setTitle(title);
    style.setText(content);
    // 配置通知栏图标
    style.setLogo("icon.png");
    // 配置通知栏网络图标
    style.setLogoUrl("");
    // 设置通知是否响铃，震动，或者可清除
    style.setRing(true);
    style.setVibrate(true);
    style.setClearable(true);
    template.setStyle(style);
    return template;
}


public void main(String[] args){
    PushInterface.getInstance().pushToSingle("201335020231", "测试33次测试33次", "我是内容3321我是内容3321我是内容3321我是内容3321我是内容3321");
}


public PushInterface getInstance(){
    if (instance == null) {
        synchronized (PushInterface.class) {
            if (instance == null) {
                instance = new PushInterface();
            }
        }
    }
    return instance;
}


}