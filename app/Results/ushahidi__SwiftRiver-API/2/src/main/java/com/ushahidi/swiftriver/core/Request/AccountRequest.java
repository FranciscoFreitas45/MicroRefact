package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Account;
public interface AccountRequest {

   public Account getAccount(long idXN57);
   public void setAccount(Account account,long idXN57);
}