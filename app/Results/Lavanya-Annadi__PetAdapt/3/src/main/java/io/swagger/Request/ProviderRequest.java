package io.swagger.Request;
import io.swagger.DTO.Provider;
public interface ProviderRequest {

   public void setProvider(Provider provider,Long id);
   public Provider getProvider(Long id);
}