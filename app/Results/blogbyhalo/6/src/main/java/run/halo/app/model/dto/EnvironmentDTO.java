package run.halo.app.model.dto;
 import lombok.Data;
import run.halo.app.model.enums.Mode;
@Data
public class EnvironmentDTO {

 private  String database;

 private  Long startTime;

 private  String version;

 private  Mode mode;


}