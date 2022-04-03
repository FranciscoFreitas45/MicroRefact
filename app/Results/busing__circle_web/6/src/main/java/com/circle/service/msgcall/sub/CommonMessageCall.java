package com.circle.service.msgcall.sub;
 import com.circle.pojo.msg.MessageBean;
import com.circle.service.msgcall.AMessageCall;
import com.xwtec.xwserver.exception.SPTException;
public class CommonMessageCall extends AMessageCall{


@Override
public boolean secondClickCall(MessageBean messageBean){
    return super.secondClickCall(messageBean);
}


@Override
public boolean firstClickCall(MessageBean messageBean){
    return super.firstClickCall(messageBean);
}


}