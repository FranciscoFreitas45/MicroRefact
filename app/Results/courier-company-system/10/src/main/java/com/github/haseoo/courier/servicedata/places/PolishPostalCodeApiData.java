package com.github.haseoo.courier.servicedata.places;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import java.util.List;
@Value
public class PolishPostalCodeApiData {

 private  String form;

 private  String to;

 private  String parity;

 private  String postalCode;

 private  String name;

 private  String locality;

 private  String street;

 private  String number;

 private  String commune;

 private  String county;

 private  String voivodeship;

 private  String district;

 private  List<Numeracja> numeration;

@JsonCreator
public PolishPostalCodeApiData(@JsonProperty(value = "kod") String postalCode, @JsonProperty(value = "nazwa") String name, @JsonProperty(value = "miejscowosc") String locality, @JsonProperty(value = "ulica") String street, @JsonProperty(value = "numer") String number, @JsonProperty(value = "gmina") String commune, @JsonProperty(value = "powiat") String county, @JsonProperty(value = "wojewodztwo") String voivodeship, @JsonProperty(value = "dzielnica") String district, @JsonProperty(value = "numeracja") List<Numeracja> numeration) {
    this.postalCode = postalCode;
    this.locality = locality;
    this.name = name;
    this.street = street;
    this.number = number;
    this.commune = commune;
    this.county = county;
    this.voivodeship = voivodeship;
    this.district = district;
    this.numeration = numeration;
}
}