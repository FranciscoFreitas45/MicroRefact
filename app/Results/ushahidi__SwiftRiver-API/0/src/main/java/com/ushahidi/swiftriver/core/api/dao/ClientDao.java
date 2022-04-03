package com.ushahidi.swiftriver.core.api.dao;
 import com.ushahidi.swiftriver.core.model.Client;
public interface ClientDao extends GenericDao<Client>{


public Client findByClientId(String clientId)
;

}