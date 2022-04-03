package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse extends AbstractResponse{

@JsonProperty("error")
 private  String error;

@JsonProperty("error_description")
 private  String errorDescription;


}