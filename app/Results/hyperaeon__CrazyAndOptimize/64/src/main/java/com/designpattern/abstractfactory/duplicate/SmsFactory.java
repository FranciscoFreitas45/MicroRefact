package com.designpattern.abstractfactory.duplicate;
 public class SmsFactory implements Provider{


@Override
public Sender produce(){
    return new SmsSender();
}


}