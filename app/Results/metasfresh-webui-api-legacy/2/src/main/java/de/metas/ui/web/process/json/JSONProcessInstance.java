package de.metas.ui.web.process.json;
 import java.io.Serializable;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.process.IProcessInstanceController;
import de.metas.ui.web.window.datatypes.json.JSONDocumentField;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.GuavaCollectors;
@SuppressWarnings("serial")
public class JSONProcessInstance implements Serializable{

@JsonProperty("pinstanceId")
 private  String pinstanceId;

@JsonProperty("fieldsByName")
 private  Map<String,JSONDocumentField> parametersByName;

@JsonProperty("startProcessDirectly")
 private  boolean startProcessDirectly;


public JSONProcessInstance of(IProcessInstanceController pinstance,JSONOptions jsonOpts){
    return new JSONProcessInstance(pinstance, jsonOpts);
}


}