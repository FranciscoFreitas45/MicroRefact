package com.designpattern.abstractfactory;
 public class SendSmsFactory implements Provider{


@Override
public Sender produce(){
    return new SmsSender();
}


}