package de.metas.ui.web.devtools;
 import lombok.Builder;
import lombok.ToString;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Builder
@ToString
public class JSONMigrationScriptsInfo {

 private  boolean enabled;

 private  String migrationScriptDirectory;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@// 
ApiModelProperty(// 
allowEmptyValue = true, value = "The file name of the SQL script to which the system currently records.")
 private  String currentScript;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@// 
ApiModelProperty(// 
allowEmptyValue = true, value = "The file names of recorded SQL scripts.")
 private  List<String> scripts;


}