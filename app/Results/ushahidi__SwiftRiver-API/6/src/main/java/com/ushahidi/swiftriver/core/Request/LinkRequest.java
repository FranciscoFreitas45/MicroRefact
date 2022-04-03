package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Link;
public interface LinkRequest {

   public Link getOriginalUrl(long idLCK8);
   public void setOriginalUrl(Link originalUrl,long idLCK8);
}