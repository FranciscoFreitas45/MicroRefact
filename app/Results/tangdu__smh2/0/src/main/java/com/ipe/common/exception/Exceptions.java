package com.ipe.common.exception;
 import com.ipe.common.exception.CustException;
import com.ipe.common.exception.ServiceException;
public class Exceptions {


public ServiceException throwServiceException(Exception e){
    return new ServiceException(e);
}


public CustException throwCustException(Exception e){
    return new CustException(e);
}


}