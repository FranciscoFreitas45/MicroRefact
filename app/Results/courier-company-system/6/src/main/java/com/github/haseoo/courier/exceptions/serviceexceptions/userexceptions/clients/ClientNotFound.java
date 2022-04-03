package com.github.haseoo.courier.exceptions.serviceexceptions.userexceptions.clients;
 import com.github.haseoo.courier.enums.ClientType;
import com.github.haseoo.courier.exceptions.ResourceNotFoundException;
import com.github.haseoo.courier.exceptions.ExceptionMessages.CLIENT_NOT_FOUND_EXCEPTION_FORMAT;
public class ClientNotFound extends ResourceNotFoundException{

public ClientNotFound(Long id, ClientType clientType) {
    super(String.format(CLIENT_NOT_FOUND_EXCEPTION_FORMAT, clientType, id));
}public ClientNotFound(Long id) {
    super(String.format(CLIENT_NOT_FOUND_EXCEPTION_FORMAT, "", id));
}
}