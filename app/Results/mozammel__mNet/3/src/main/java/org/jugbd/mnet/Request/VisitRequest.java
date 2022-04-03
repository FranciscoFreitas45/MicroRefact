package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.Visit;
public interface VisitRequest {

   public Set<Visit> getVisits(Long id);
   public OutdoorRegister setVisits(Set<Visit> visits,Long id);
}