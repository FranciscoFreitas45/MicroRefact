package cn.gson.oasys.Request;
import cn.gson.oasys.DTO.Position;
public interface PositionRequest {

   public void setPosition(Position position,Long id);
   public Position getPosition(Long id);
}