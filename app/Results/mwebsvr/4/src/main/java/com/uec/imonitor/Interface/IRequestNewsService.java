package com.uec.imonitor.Interface;
public interface IRequestNewsService {

   public List<RequestNewsDetail> listDetailByIds(List<Integer> idList);
}