package com.project.stockexchangeappbackend.util.timemeasuring;
 import lombok.Data;
@Data
public class ProcessingTime {

 private  Long databaseOperationExecutionTime;

 private  Long businessLogicExecutionTime;

public ProcessingTime() {
    this.databaseOperationExecutionTime = 0L;
    this.businessLogicExecutionTime = 0L;
}
}