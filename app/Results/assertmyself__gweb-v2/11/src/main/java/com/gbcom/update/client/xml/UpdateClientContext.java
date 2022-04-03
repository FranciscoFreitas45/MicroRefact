package com.gbcom.update.client.xml;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("UpdateClientContext")
public class UpdateClientContext {

@XStreamAlias("UpdateClient")
 private  UpdateClient updateClient;


public void setUpdateClient(UpdateClient updateClient){
    this.updateClient = updateClient;
}


public UpdateClient getUpdateClient(){
    return updateClient;
}


}