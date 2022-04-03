package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Channel;
public interface ChannelRequest {

   public void setChannels(List<Channel> channels,Long id);
   public List<Channel> getChannels(Long id);
}