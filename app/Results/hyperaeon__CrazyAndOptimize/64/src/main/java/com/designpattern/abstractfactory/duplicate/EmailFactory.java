package com.designpattern.abstractfactory.duplicate;
 public class EmailFactory implements Provider{


@Override
public Sender produce(){
    return new EmailSender();
}


}