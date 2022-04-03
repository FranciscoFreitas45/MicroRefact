package org.live.Request;
import org.live.DTO.Anchor;
public interface AnchorRequest {

   public Anchor getAnchor(String idFK1I);
   public void setAnchor(Anchor anchor,String idFK1I);
}