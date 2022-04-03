package com.zis.Interface;
import java.util.*;
public interface StorageRepoInfoDao {

   public List<StorageRepoInfo> findByOwnerIdOrderByGmtCreateAsc(Integer ownerId);
}