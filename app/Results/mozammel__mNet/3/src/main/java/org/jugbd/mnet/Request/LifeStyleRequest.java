package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.LifeStyle;
public interface LifeStyleRequest {

   public void setLifeStyle(LifeStyle lifeStyle,Long id);
   public LifeStyle getLifeStyle(Long id);
}