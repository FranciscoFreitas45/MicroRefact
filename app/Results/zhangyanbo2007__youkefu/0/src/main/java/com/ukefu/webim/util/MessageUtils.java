package com.ukefu.webim.util;
 import java.util.Date;
import org.apache.commons.lang.StringUtils;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.client.NettyClients;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.AgentUserTaskRepository;
import com.ukefu.webim.service.repository.ChatMessageRepository;
import com.ukefu.webim.service.repository.ConsultInviteRepository;
import com.ukefu.webim.util.server.message.ChatMessage;
import com.ukefu.webim.web.model.AgentUser;
import com.ukefu.webim.web.model.AgentUserTask;
import com.ukefu.webim.web.model.AiUser;
import com.ukefu.webim.web.model.CousultInvite;
import com.ukefu.webim.web.model.MessageOutContent;
public class MessageUtils {


public ChatMessage uploadFile(String url,int size,String name,String channel,String userid,String username,String appid,String orgi,String attachid){
    return createMessage(url, size, name, channel, UKDataContext.MediaTypeEnum.FILE.toString(), userid, username, appid, orgi, attachid);
}


public ChatMessage uploadVoice(String url,int size,String name,String channel,String userid,String username,String appid,String orgi,String attachid,int duration){
    return createMessage(url, size, name, channel, UKDataContext.MediaTypeEnum.VOICE.toString(), userid, username, appid, orgi, attachid, duration);
}


public ChatMessage createMessage(String message,int length,String name,String channel,String msgtype,String userid,String username,String appid,String orgi,String attachid,String aiid){
    ChatMessage data = new ChatMessage();
    if (!StringUtils.isBlank(userid)) {
        data.setUserid(userid);
        data.setUsername(username);
        data.setTouser(userid);
        data.setAppid(appid);
        data.setOrgi(orgi);
        data.setChannel(channel);
        data.setMessage(message);
        data.setAiid(aiid);
        data.setFilesize(length);
        data.setFilename(name);
        data.setAttachmentid(attachid);
        data.setMsgtype(msgtype);
        data.setType(UKDataContext.MessageTypeEnum.MESSAGE.toString());
        createAiMessage(data, appid, channel, UKDataContext.CallTypeEnum.IN.toString(), UKDataContext.AiItemType.USERINPUT.toString(), msgtype, data.getUserid());
    }
    return data;
}


public void sendMessage(ChatMessage data,MessageOutContent outMessage,String msgtype){
    if (!StringUtils.isBlank(data.getUserid()) && UKDataContext.MessageTypeEnum.MESSAGE.toString().equals(data.getType())) {
        NettyClients.getInstance().sendIMEventMessage(data.getUserid(), UKDataContext.MessageTypeEnum.MESSAGE.toString(), outMessage);
    }
}


public ChatMessage uploadImage(String image,String attachid,int size,String name,String channel,String userid,String username,String appid,String orgi){
    return createMessage(image, size, name, channel, UKDataContext.MediaTypeEnum.IMAGE.toString(), userid, username, appid, orgi, attachid);
}


public MessageOutContent createAiMessage(ChatMessage data,String appid,String channel,String direction,String chatype,String msgtype,String userid){
    MessageOutContent outMessage = new MessageOutContent();
    outMessage.setMessage(data.getMessage());
    outMessage.setMessageType(msgtype);
    outMessage.setCalltype(direction);
    outMessage.setAgentUser(null);
    outMessage.setSnsAccount(null);
    outMessage.setDuration(data.getDuration());
    if (!StringUtils.isBlank(userid)) {
        data.setUserid(userid);
        data.setTouser(userid);
        data.setAgentuser(userid);
        data.setChatype(chatype);
        data.setChannel(channel);
        data.setAppid(data.getAppid());
        data.setOrgi(data.getOrgi());
        data.setMsgtype(msgtype);
        // agentUser作为 session id
        data.setUsession(data.getUserid());
        data.setCalltype(direction);
        if (data.isQuickagent()) {
            outMessage.setQuickagent(true);
        }
        outMessage.setContextid(data.getContextid());
        outMessage.setFromUser(data.getUserid());
        outMessage.setToUser(data.getTouser());
        outMessage.setChannelMessage(data);
        outMessage.setNickName(data.getUsername());
        outMessage.setCreatetime(data.getCreatetime());
        outMessage.setTopic(!StringUtils.isBlank(data.getTopicid()));
        if (!StringUtils.isBlank(data.getSuggestmsg())) {
            outMessage.setSuggest(data.getSuggest());
        }
        data.setUpdatetime(System.currentTimeMillis());
        /**
         * 保存消息
         */
        if (UKDataContext.MessageTypeEnum.MESSAGE.toString().equals(data.getType())) {
            UKDataContext.getContext().getBean(ChatMessageRepository.class).save(data);
        }
        outMessage.setId(data.getId());
        AgentUser agentUser = (AgentUser) CacheHelper.getAgentUserCacheBean().getCacheObject(userid, UKDataContext.SYSTEM_ORGI);
        if (agentUser != null && !StringUtils.isBlank(agentUser.getAgentno())) {
            // 将消息发送给 坐席
            if (UKDataContext.CallTypeEnum.OUT.toString().equals(direction)) {
                data.setUserid(agentUser.getAgentno());
            }
            NettyClients.getInstance().sendAgentEventMessage(agentUser.getAgentno(), UKDataContext.MessageTypeEnum.MESSAGE.toString(), data);
        }
    }
    return outMessage;
}


}