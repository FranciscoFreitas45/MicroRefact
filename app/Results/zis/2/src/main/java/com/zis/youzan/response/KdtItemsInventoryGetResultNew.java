package com.zis.youzan.response;
 import com.fasterxml.jackson.annotation.JsonProperty;
import com.youzan.open.sdk.gen.v1_0_0.model.KdtItemsInventoryGetResult;
public class KdtItemsInventoryGetResultNew extends KdtItemsInventoryGetResult{

@JsonProperty(value = "total_results")
 private  Long totalResults;


public void setTotalResults(Long totalResults){
    this.totalResults = totalResults;
}


public Long getTotalResults(){
    return totalResults;
}


}