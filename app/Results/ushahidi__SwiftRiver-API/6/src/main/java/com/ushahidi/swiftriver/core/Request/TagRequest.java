package com.ushahidi.swiftriver.core.Request;
import com.ushahidi.swiftriver.core.DTO.Tag;
public interface TagRequest {

   public List<Tag> getTags(long id);
   public void setTags(List<Tag> tags,long id);
}