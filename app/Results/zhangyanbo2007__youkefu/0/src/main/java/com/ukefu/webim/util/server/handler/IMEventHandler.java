package com.ukefu.webim.util.server.handler;
 import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.client.NettyClients;
import com.ukefu.webim.service.acd.ServiceQuene;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.impl.AgentUserService;
import com.ukefu.webim.service.repository.AgentServiceRepository;
import com.ukefu.webim.service.repository.ConsultInviteRepository;
import com.ukefu.webim.util.MessageUtils;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.util.server.message.AgentStatusMessage;
import com.ukefu.webim.util.server.message.ChatMessage;
import com.ukefu.webim.util.server.message.NewRequestMessage;
import com.ukefu.webim.web.model.AgentService;
import com.ukefu.webim.web.model.AgentUser;
import com.ukefu.webim.web.model.Contacts;
import com.ukefu.webim.web.model.CousultInvite;
import com.ukefu.webim.web.model.IMClient;
import com.ukefu.webim.web.model.MessageOutContent;
public class IMEventHandler {

 protected  SocketIOServer server;

@Autowired
public IMEventHandler(SocketIOServer server) {
    this.server = server;
}
@OnConnect
public void onConnect(SocketIOClient client){
}


@OnDisconnect
public void onDisconnect(SocketIOClient client){
    IMClient im = client.get("im");
    if (im != null && im.getUser() != null) {
        try {
            /**
             * 用户主动断开服务
             */
            ServiceQuene.serviceFinish((AgentUser) CacheHelper.getAgentUserCacheBean().getCacheObject(im.getUser(), UKDataContext.SYSTEM_ORGI), im.getOrgi(), UKDataContext.EndByType.USER.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        NettyClients.getInstance().removeIMEventClient(im.getUser(), UKTools.getContextID(client.getSessionId().toString()));
    }
}


@OnEvent(value = "message")
public void onEvent(SocketIOClient client,AckRequest request,ChatMessage data){
    if (data.getType() == null) {
        data.setType("message");
    }
    /**
     * 以下代码主要用于检查 访客端的字数限制
     */
    CousultInvite invite = OnlineUserUtils.cousult(data.getAppid(), data.getOrgi(), UKDataContext.getContext().getBean(ConsultInviteRepository.class));
    if (invite != null && invite.getMaxwordsnum() > 0) {
        if (!StringUtils.isBlank(data.getMessage()) && data.getMessage().length() > invite.getMaxwordsnum()) {
            data.setMessage(data.getMessage().substring(0, invite.getMaxwordsnum()));
        }
    } else if (!StringUtils.isBlank(data.getMessage()) && data.getMessage().length() > 300) {
        data.setMessage(data.getMessage().substring(0, 300));
    }
    /**
     * 处理表情
     */
    data.setMessage(UKTools.processEmoti(data.getMessage()));
    MessageUtils.createMessage(data, UKDataContext.MediaTypeEnum.TEXT.toString(), data.getUserid());
}


}