package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaChannelDao;
import com.ushahidi.swiftriver.core.model.Channel;
@Service
public class ChannelRiverDropService {

@Autowired
 private JpaChannelDao jpachanneldao;


public void setChannel(long idQKZH,Channel channel){
jpachanneldao.setChannel(idQKZH,channel);
}


public Channel getChannel(long idQKZH){
return jpachanneldao.getChannel(idQKZH);
}


}