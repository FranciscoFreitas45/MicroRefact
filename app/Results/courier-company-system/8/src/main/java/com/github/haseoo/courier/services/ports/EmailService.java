package com.github.haseoo.courier.services.ports;
 import com.github.haseoo.courier.servicedata.parcels.ParcelData;
public interface EmailService {


public void sentNotificationToReceiver(ParcelData parcelData)
;

public void sentNotificationToSender(ParcelData parcelData)
;

public void sentReturnNotification(ParcelData parcelData)
;

}