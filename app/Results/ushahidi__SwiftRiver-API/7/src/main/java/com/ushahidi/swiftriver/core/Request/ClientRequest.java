package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Client;
public interface ClientRequest {

   public Set<Client> getClients(long id);
   public void setClients(Set<Client> clients,long id);
}