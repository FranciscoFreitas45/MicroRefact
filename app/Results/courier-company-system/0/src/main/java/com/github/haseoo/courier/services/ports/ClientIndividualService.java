package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.servicedata.users.clients.ClientIndividualAddData;
import com.github.haseoo.courier.servicedata.users.clients.ClientIndividualData;
import com.github.haseoo.courier.servicedata.users.clients.ClientIndividualEditData;
public interface ClientIndividualService {


public ClientIndividualData add(ClientIndividualAddData addData)
;

public ClientIndividualData getById(Long id)
;

public ClientIndividualData edit(Long id,ClientIndividualEditData editData)
;

}