package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.api.dao.impl.JpaClientDao;
import com.ushahidi.swiftriver.core.model.Client;
@Service
public class ClientAccountService {

@Autowired
 private JpaClientDao jpaclientdao;


public Set<Client> getClients(long id){
return jpaclientdao.getClients(id);
}


public void setClients(long id,Set<Client> clients){
jpaclientdao.setClients(id,clients);
}


}