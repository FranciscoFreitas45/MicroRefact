package com.designpattern.abstractfactory;
 public class SendClokerFactory implements Provider{


@Override
public Sender produce(){
    return new ClokerSender();
}


}