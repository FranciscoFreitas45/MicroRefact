package com.ushahidi.swiftriver.core.api.dto;
 public class GetBucketCollaboratorDTO extends GetCollaboratorDTO{

 private  GetBucketDTO bucket;

 private  AccountDTO account;


public GetBucketDTO getBucket(){
    return bucket;
}


public AccountDTO getAccount(){
    return account;
}


public void setAccount(AccountDTO account){
    this.account = account;
}


public void setBucket(GetBucketDTO bucket){
    this.bucket = bucket;
}


}