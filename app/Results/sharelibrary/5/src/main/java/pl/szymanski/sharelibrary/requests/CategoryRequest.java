package pl.szymanski.sharelibrary.requests;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CategoryRequest {

 private  int id;

 private  String name;

@JsonCreator
public CategoryRequest(@JsonProperty(value = "id") int id, @JsonProperty(value = "name") String name) {
    this.id = id;
    this.name = name;
}
}