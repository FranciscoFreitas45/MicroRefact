package pl.szymanski.sharelibrary.requests;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class CreateRequirementRequest {

 private  Long exchangeId;

 private  Long userId;

@JsonCreator
public CreateRequirementRequest(@JsonProperty(value = "exchangeId", required = true) Long exchangeId, @JsonProperty(value = "userId", required = true) Long userId) {
    this.exchangeId = exchangeId;
    this.userId = userId;
}
}