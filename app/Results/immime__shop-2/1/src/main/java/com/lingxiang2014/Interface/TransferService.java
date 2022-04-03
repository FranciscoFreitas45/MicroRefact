package com.lingxiang2014.Interface;
public interface TransferService {

   public Page<Transfer> findPage(Member fromMember,Member toMember,Method method,Pageable pageable);
}