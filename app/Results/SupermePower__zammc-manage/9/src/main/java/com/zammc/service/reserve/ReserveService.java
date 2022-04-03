package com.zammc.service.reserve;
 import com.zammc.domain.reserve.ReserveEntity;
import com.zammc.page.PageBean;
public interface ReserveService {


public void queryReservePage(ReserveEntity reserveEntity,PageBean pageBean)
;

public void deleteReserve(ReserveEntity reserveEntity)
;

}