package pl.szymanski.sharelibrary.requests;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ExecuteExchangeRequest {

 private  Long exchangeId;

 private  Long withUserId;

 private  Long forBookId;

@JsonCreator
public ExecuteExchangeRequest(@JsonProperty(value = "exchangeId", required = true) Long exchangeId, @JsonProperty(value = "withUserId", required = true) Long withUserId, @JsonProperty(value = "forBookId") Long forBookId) {
    this.exchangeId = exchangeId;
    this.withUserId = withUserId;
    this.forBookId = forBookId;
}
}