package com.ushahidi.swiftriver.core.api.dao.impl;
 import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.ClientDao;
import com.ushahidi.swiftriver.core.model.Client;
@Repository
public class JpaClientDao extends AbstractJpaDao<Client>implements ClientDao{


@Override
public Client findByClientId(String clientId){
    String query = "SELECT c FROM Client c WHERE c.clientId = :client_id";
    Client client = null;
    try {
        client = (Client) em.createQuery(query).setParameter("client_id", clientId).getSingleResult();
    } catch (NoResultException e) {
    // Do nothing
    }
    return client;
}


public Set<Client> getClients(long id)

public void setClients(long id,Set<Client> clients)

public void setAccount(long id,Account account)

public void setRoles(long id,Set<Role> roles)

public void setActive(long id,Boolean active)

public void setClientSecret(long id,String clientSecret)

public void setClientId(long id,String clientId)

}