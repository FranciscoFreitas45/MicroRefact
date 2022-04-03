package com.project.stockexchangeappbackend.dto;
 import com.project.stockexchangeappbackend.entity.StockIndexValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
@Getter
@Setter
@ApiModel(description = "Stock's index object in database.")
public class StockIndexValueDTO {

@ApiModelProperty(notes = "The date of measurement.")
 private  OffsetDateTime timestamp;

@ApiModelProperty(notes = "The opening value.")
 private  BigDecimal open;

@ApiModelProperty(notes = "The minimum value.")
 private  BigDecimal min;

@ApiModelProperty(notes = "The maximum value.")
 private  BigDecimal max;

@ApiModelProperty(notes = "The closing value.")
 private  BigDecimal close;

public StockIndexValueDTO(List<StockIndexValue> group) {
    if (group.size() > 0) {
        this.timestamp = group.get(0).getTimestamp();
        this.open = group.get(group.size() - 1).getValue();
        this.close = group.get(0).getValue();
        this.min = group.stream().min(Comparator.comparing(StockIndexValue::getValue)).get().getValue();
        this.max = group.stream().max(Comparator.comparing(StockIndexValue::getValue)).get().getValue();
    }
}
}