package com.Interface;
public interface ICommonDao {

   public int[] batchUpdate(String sql,List<Map<String,?>> paramListMap);
}