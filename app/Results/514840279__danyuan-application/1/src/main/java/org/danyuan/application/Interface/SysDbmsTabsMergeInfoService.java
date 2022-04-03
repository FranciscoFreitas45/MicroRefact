package org.danyuan.application.Interface;
public interface SysDbmsTabsMergeInfoService {

   public Object findOne(Object Object);
   public List<SysDbmsTabsColsInfo> page1(Pagination<SysDbmsTabsMergeInfo> vo);
   public List<SysDbmsTabsColsInfo> page2(Pagination<SysDbmsTabsMergeInfo> vo);
   public String merge(Pagination<SysDbmsTabsMergeInfo> vo);
   public String loadSql(SysDbmsTabsMergeInfo vo);
}