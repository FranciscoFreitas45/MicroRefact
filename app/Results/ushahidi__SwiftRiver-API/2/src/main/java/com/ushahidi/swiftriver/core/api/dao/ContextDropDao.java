package com.ushahidi.swiftriver.core.api.dao;
 import java.util.List;
import com.ushahidi.swiftriver.core.model.Account;
import com.ushahidi.swiftriver.core.model.Drop;
public interface ContextDropDao {


public void populateMetadata(List<Drop> drops,Account queryingAccount)
;

}