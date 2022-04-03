package hei2017.Request;
import hei2017.DTO.Sprint;
public interface SprintRequest {

   public Sprint getStorySprint(Long id);
   public void setStorySprint(Sprint storySprint,Long id);
}