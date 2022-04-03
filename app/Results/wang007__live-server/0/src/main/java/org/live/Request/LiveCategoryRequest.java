package org.live.Request;
import org.live.DTO.LiveCategory;
public interface LiveCategoryRequest {

   public LiveCategory getCategory(String idHCR5);
   public void setCategory(LiveCategory category,String idHCR5);
}