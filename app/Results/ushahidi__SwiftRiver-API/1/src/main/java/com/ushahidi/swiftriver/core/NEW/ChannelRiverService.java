package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaChannelDao;
import com.ushahidi.swiftriver.core.model.Channel;
@Service
public class ChannelRiverService {

@Autowired
 private JpaChannelDao jpachanneldao;


public void setChannels(Long id,List<Channel> channels){
jpachanneldao.setChannels(id,channels);
}


public List<Channel> getChannels(Long id){
return jpachanneldao.getChannels(id);
}


}