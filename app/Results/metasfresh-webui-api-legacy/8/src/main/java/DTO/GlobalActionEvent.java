package DTO;
 import com.google.common.base.Strings;
import de.metas.process.ProcessExecutionResult;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
public class GlobalActionEvent {

 private GlobalActionType type;

 private String payload;

 private  String SEPARATOR;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public ProcessExecutionResult.DisplayQRCode toDisplayQRCodeProcessResult(){
    return ProcessExecutionResult.DisplayQRCode.builder().code(toQRCodeString()).build();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toDisplayQRCodeProcessResult"))

ProcessExecutionResult.DisplayQRCode aux = restTemplate.getForObject(builder.toUriString(),ProcessExecutionResult.DisplayQRCode.class);
return aux;
}


}