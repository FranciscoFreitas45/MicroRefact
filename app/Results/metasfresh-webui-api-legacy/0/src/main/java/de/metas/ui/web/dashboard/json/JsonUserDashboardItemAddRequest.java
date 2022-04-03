package de.metas.ui.web.dashboard.json;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Value;
@Builder
@Value
@JsonDeserialize(builder = JsonUserDashboardItemAddRequest.JsonUserDashboardItemAddRequestBuilder.class)
public class JsonUserDashboardItemAddRequest {

 private int kpiId;

@Default
 private int position;

 private String caption;

 private JSONInterval interval;

 private JSONWhen when;

 private  String esTimeRange;

 private  String esTimeRangeEnd;


}