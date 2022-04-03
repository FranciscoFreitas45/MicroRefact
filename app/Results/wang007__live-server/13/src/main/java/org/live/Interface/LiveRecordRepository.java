package org.live.Interface;
public interface LiveRecordRepository {

   public Object count(Object Object);
   public Page<LiveRecordVo> find(PageRequest pageRequest,Map<String,Object> filter);
}