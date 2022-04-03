package com.nhnent.forward.mybatistojpa.model;
 import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

@JsonSerialize(using = ToStringSerializer.class)
 private  Long itemId;

 private  String itemName;

 private  Long price;


}