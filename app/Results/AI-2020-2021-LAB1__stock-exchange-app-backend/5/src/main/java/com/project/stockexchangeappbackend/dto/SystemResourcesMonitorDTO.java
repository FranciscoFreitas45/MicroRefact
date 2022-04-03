package com.project.stockexchangeappbackend.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "System Resources Info object.")
public class SystemResourcesMonitorDTO {

@ApiModelProperty(notes = "The system resources info's id.")
 private  Long id;

@ApiModelProperty(notes = "The system resources info's timestamp.")
 private  OffsetDateTime timestamp;

@ApiModelProperty(notes = "The system resources info's CPU usage.")
 private  Double cpuUsage;

@ApiModelProperty(notes = "The system resources info's RAM usage.")
 private  Double memoryUsage;

@ApiModelProperty(notes = "The system resources info's RAM used in bytes.")
 private  Long memoryUsed;


}