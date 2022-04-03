package com.zammc.service.recharge;
 import com.zammc.domain.recharge.RechargePackageEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
public interface RechargePackageService {


public void queryRechargePackagePage(RechargePackageEntity rechargePackageEntity,PageBean pageBean)
;

public Message editRechargePackage(RechargePackageEntity rechargePackageEntity)
;

public RechargePackageEntity queryRechargePackageById(RechargePackageEntity rechargePackageEntity)
;

public Message addRechargePackage(RechargePackageEntity rechargePackageEntity)
;

public void deletePackage(RechargePackageEntity rechargePackageEntity)
;

}