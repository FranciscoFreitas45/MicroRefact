package pl.szymanski.sharelibrary.requests;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class AddExchangeRequest {

 private  Double deposit;

 private  Long bookId;

 private  Long userId;

 private  CoordinatesRequest coordinates;

@JsonCreator
public AddExchangeRequest(@JsonProperty("deposit") Double deposit, @JsonProperty("bookId") Long bookId, @JsonProperty("userId") Long userId, @JsonProperty("coordinates") CoordinatesRequest coordinates) {
    this.deposit = deposit;
    this.bookId = bookId;
    this.userId = userId;
    this.coordinates = coordinates;
}
}