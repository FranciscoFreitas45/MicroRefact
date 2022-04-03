package com.github.haseoo.courier.servicedata.places.PolishPostalCodeApiData;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import java.util.List;
@Value
public class Numeracja {

 private  String form;

 private  String to;

 private  String parity;

public Numeracja(@JsonProperty(value = "od") String form, @JsonProperty(value = "do") String to, @JsonProperty(value = "parzystosc") String parity) {
    this.form = form;
    this.to = to;
    this.parity = parity;
}
}