package com.ushahidi.swiftriver.core.api.dao.impl;
 import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.ChannelDao;
import com.ushahidi.swiftriver.core.model.Channel;
@Repository
public class JpaChannelDao extends AbstractJpaDao<Channel>implements ChannelDao{


public void setChannel(long idQKZH,Channel channel)

public Channel getChannel(long idQKZH)

public void setChannels(Long id,List<Channel> channels)

public List<Channel> getChannels(Long id)

}