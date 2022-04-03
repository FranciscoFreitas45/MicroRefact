package com.github.haseoo.courier.services.ports;
 import java.io.IOException;
public interface PostalCodeHelper {


public void validatePostalCode(String city,String postalCode)
;

public boolean isPostalCodeInCity(String postalCode,String city)
;

}