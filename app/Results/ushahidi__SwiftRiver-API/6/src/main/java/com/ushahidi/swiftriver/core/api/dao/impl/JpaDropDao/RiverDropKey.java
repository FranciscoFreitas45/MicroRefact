package com.ushahidi.swiftriver.core.api.dao.impl.JpaDropDao;
 import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.DropDao;
import com.ushahidi.swiftriver.core.api.dao.IdentityDao;
import com.ushahidi.swiftriver.core.api.dao.LinkDao;
import com.ushahidi.swiftriver.core.api.dao.MediaDao;
import com.ushahidi.swiftriver.core.api.dao.PlaceDao;
import com.ushahidi.swiftriver.core.api.dao.SequenceDao;
import com.ushahidi.swiftriver.core.api.dao.TagDao;
import com.ushahidi.swiftriver.core.model.Bucket;
import com.ushahidi.swiftriver.core.model.BucketDrop;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Place;
import com.ushahidi.swiftriver.core.model.River;
import com.ushahidi.swiftriver.core.model.RiverTagTrend;
import com.ushahidi.swiftriver.core.model.Sequence;
import com.ushahidi.swiftriver.core.model.Tag;
import com.ushahidi.swiftriver.core.util.MD5Util;
public class RiverDropKey {

 private  Long riverId;

 private  Long dropId;

public RiverDropKey(Long riverId, Long dropId) {
    this.riverId = riverId;
    this.dropId = dropId;
}
@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((dropId == null) ? 0 : dropId.hashCode());
    result = prime * result + ((riverId == null) ? 0 : riverId.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    RiverDropKey other = (RiverDropKey) obj;
    if (dropId == null) {
        if (other.dropId != null)
            return false;
    } else if (!dropId.equals(other.dropId))
        return false;
    if (riverId == null) {
        if (other.riverId != null)
            return false;
    } else if (!riverId.equals(other.riverId))
        return false;
    return true;
}


}