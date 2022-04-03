package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.servicedata.users.clients.ClientData;
import java.util.List;
public interface ClientService {


public ClientData getById(Long id)
;

public List<ClientData> getList()
;

}