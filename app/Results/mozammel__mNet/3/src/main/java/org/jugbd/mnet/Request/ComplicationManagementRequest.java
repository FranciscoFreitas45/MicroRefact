package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.ComplicationManagement;
public interface ComplicationManagementRequest {

   public ComplicationManagement getComplicationManagement(Long id);
   public void setComplicationManagement(ComplicationManagement complicationManagement,Long id);
}