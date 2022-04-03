package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.PictureInformation;
public interface PictureInformationRequest {

   public PictureInformation getPictureInformation(Long id);
   public OutdoorRegister setPictureInformation(PictureInformation pictureInformation,Long id);
}