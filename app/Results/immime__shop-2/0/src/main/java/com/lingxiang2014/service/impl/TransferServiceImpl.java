package com.lingxiang2014.service.impl;
 import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.TransferDao;
import com.lingxiang2014.entity.Transfer;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Transfer.Method;
import com.lingxiang2014.service.TransferService;
@Service("transferServiceImpl")
public class TransferServiceImpl extends BaseServiceImpl<Transfer, Long>implements TransferService{

@Resource(name = "transferDaoImpl")
 private  TransferDao transferDao;


@Resource(name = "transferDaoImpl")
public void setBaseDao(TransferDao transferDao){
    super.setBaseDao(transferDao);
}


@Transactional(readOnly = true)
public Page<Transfer> findPage(Member fromMember,Member toMember,Method method,Pageable pageable){
    return transferDao.findPage(fromMember, toMember, method, pageable);
}


}