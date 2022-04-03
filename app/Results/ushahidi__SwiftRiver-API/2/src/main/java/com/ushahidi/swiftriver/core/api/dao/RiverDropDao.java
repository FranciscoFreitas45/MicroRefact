package com.ushahidi.swiftriver.core.api.dao;
 import com.ushahidi.swiftriver.core.model.Account;
import com.ushahidi.swiftriver.core.model.Link;
import com.ushahidi.swiftriver.core.model.Place;
import com.ushahidi.swiftriver.core.model.River;
import com.ushahidi.swiftriver.core.model.RiverDrop;
import com.ushahidi.swiftriver.core.model.RiverDropComment;
import com.ushahidi.swiftriver.core.model.RiverDropForm;
import com.ushahidi.swiftriver.core.model.RiverDropLink;
import com.ushahidi.swiftriver.core.model.RiverDropPlace;
import com.ushahidi.swiftriver.core.model.RiverDropTag;
import com.ushahidi.swiftriver.core.model.Tag;
public interface RiverDropDao extends ContextDropDao, GenericDao<RiverDrop>{


public RiverDropForm findForm(Long dropId,Long formId)
;

public RiverDropTag findTag(RiverDrop riverDrop,Tag tag)
;

public RiverDropPlace findPlace(RiverDrop riverDrop,Place place)
;

public boolean deleteComment(Long commentId)
;

public boolean isRead(RiverDrop riverDrop,Account account)
;

public void addTag(RiverDrop riverDrop,Tag tag)
;

public boolean deleteLink(RiverDrop riverDrop,Link link)
;

public boolean deleteTag(RiverDrop riverDrop,Tag tag)
;

public RiverDropLink findLink(RiverDrop riverDrop,Link link)
;

public boolean deletePlace(RiverDrop riverDrop,Place place)
;

public void addPlace(RiverDrop riverDrop,Place place)
;

public void addLink(RiverDrop riverDrop,Link link)
;

public RiverDropComment addComment(RiverDrop riverDrop,Account account,String commentText)
;

}