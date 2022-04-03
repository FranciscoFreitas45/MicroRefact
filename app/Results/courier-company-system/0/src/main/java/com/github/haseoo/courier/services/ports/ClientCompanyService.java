package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.servicedata.users.clients.ClientCompanyAddData;
import com.github.haseoo.courier.servicedata.users.clients.ClientCompanyData;
import com.github.haseoo.courier.servicedata.users.clients.ClientCompanyEditData;
public interface ClientCompanyService {


public ClientCompanyData add(ClientCompanyAddData addData)
;

public ClientCompanyData getById(Long id)
;

public ClientCompanyData edit(Long id,ClientCompanyEditData editData)
;

}