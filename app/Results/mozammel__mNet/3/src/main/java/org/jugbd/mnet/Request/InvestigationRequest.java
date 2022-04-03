package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.Investigation;
public interface InvestigationRequest {

   public Set<Investigation> getInvestigation(Long id);
   public void setInvestigation(Set<Investigation> investigation,Long id);
}