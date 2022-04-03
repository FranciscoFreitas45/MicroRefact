package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.OperationalDetail;
public interface OperationalDetailRequest {

   public Set<OperationalDetail> getOperationalDetails(Long id);
   public void setOperationalDetails(Set<OperationalDetail> operationalDetails,Long id);
}