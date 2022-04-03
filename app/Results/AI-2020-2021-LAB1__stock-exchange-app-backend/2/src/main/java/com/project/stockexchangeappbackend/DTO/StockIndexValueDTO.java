package com.project.stockexchangeappbackend.DTO;
 import com.project.stockexchangeappbackend.entity.StockIndexValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
public class StockIndexValueDTO {

 private  OffsetDateTime timestamp;

 private  BigDecimal open;

 private  BigDecimal min;

 private  BigDecimal max;

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