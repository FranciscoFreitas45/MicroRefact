package com.ushahidi.swiftriver.core.api.dto;
 public class GetRiverCollaboratorDTO extends GetCollaboratorDTO{

 private  GetRiverDTO river;

 private  AccountDTO account;


public GetRiverDTO getRiver(){
    return river;
}


public AccountDTO getAccount(){
    return account;
}


public void setRiver(GetRiverDTO river){
    this.river = river;
}


public void setAccount(AccountDTO account){
    this.account = account;
}


}