package com.ushahidi.swiftriver.core.api.dao.impl;
 import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.IdentityDao;
import com.ushahidi.swiftriver.core.api.dao.SequenceDao;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Identity;
import com.ushahidi.swiftriver.core.model.Sequence;
import com.ushahidi.swiftriver.core.util.MD5Util;
import com.ushahidi.swiftriver.core.Interface.SequenceDao;
@Repository
public class JpaIdentityDao extends AbstractJpaDao<Identity>implements IdentityDao{

@Autowired
 private  SequenceDao sequenceDao;

 private  NamedParameterJdbcTemplate namedJdbcTemplate;

 private  JdbcTemplate jdbcTemplate;


public int getBatchSize(){
    return hashes.size();
}


public void updateNewIdentityIndex(Map<String,List<Integer>> newIdentityIndex,List<Drop> drops){
    // First find and update existing drops with their ids.
    String sql = "SELECT id, hash FROM identities WHERE hash IN (:hashes)";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("hashes", newIdentityIndex.keySet());
    List<Map<String, Object>> results = this.namedJdbcTemplate.queryForList(sql, params);
    // Update id for the drops that were found
    for (Map<String, Object> result : results) {
        String hash = (String) result.get("hash");
        Long id = ((BigInteger) result.get("id")).longValue();
        List<Integer> indexes = newIdentityIndex.get(hash);
        for (Integer index : indexes) {
            drops.get(index).getIdentity().setId(id);
        }
        // Hash is not for a new drop so remove it
        newIdentityIndex.remove(hash);
    }
}


public void setValues(PreparedStatement ps,int i) throws SQLException{
    String hash = hashes.get(i);
    // Update drops with the newly generated id
    for (int index : newIdentityIndex.get(hash)) {
        drops.get(index).getIdentity().setId(startKey + i);
    }
    Drop drop = drops.get(newIdentityIndex.get(hash).get(0));
    ps.setLong(1, drop.getIdentity().getId());
    ps.setString(2, drop.getIdentity().getHash());
    ps.setString(3, drop.getChannel());
    ps.setString(4, drop.getIdentity().getOriginId());
    ps.setString(5, drop.getIdentity().getUsername());
    ps.setString(6, drop.getIdentity().getName());
    ps.setString(7, drop.getIdentity().getAvatar());
}


public Map<String,List<Integer>> getNewIdentityIndex(List<Drop> drops){
    Map<String, List<Integer>> newIdentityIndex = new HashMap<String, List<Integer>>();
    // Generate hashes for each new drops i.e. those without an id
    int i = 0;
    for (Drop drop : drops) {
        if (drop.getIdentity().getId() > 0)
            continue;
        String hash = MD5Util.md5Hex(drop.getChannel() + drop.getIdentity().getOriginId());
        drop.getIdentity().setHash(hash);
        // Keep a record of where this hash is in the drop list
        List<Integer> indexes;
        if (newIdentityIndex.containsKey(hash)) {
            indexes = newIdentityIndex.get(hash);
        } else {
            indexes = new ArrayList<Integer>();
            newIdentityIndex.put(hash, indexes);
        }
        indexes.add(i++);
    }
    return newIdentityIndex;
}


public void batchInsert(Map<String,List<Integer>> newIdentityIndex,List<Drop> drops,Sequence seq){
    final List<String> hashes = new ArrayList<String>();
    hashes.addAll(newIdentityIndex.keySet());
    final long startKey = sequenceDao.getIds(seq, hashes.size());
    String sql = "INSERT INTO identities (id, hash, channel, " + "identity_orig_id, identity_username, " + "identity_name, identity_avatar) " + "VALUES (?,?,?,?,?,?,?)";
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

        public void setValues(PreparedStatement ps, int i) throws SQLException {
            String hash = hashes.get(i);
            // Update drops with the newly generated id
            for (int index : newIdentityIndex.get(hash)) {
                drops.get(index).getIdentity().setId(startKey + i);
            }
            Drop drop = drops.get(newIdentityIndex.get(hash).get(0));
            ps.setLong(1, drop.getIdentity().getId());
            ps.setString(2, drop.getIdentity().getHash());
            ps.setString(3, drop.getChannel());
            ps.setString(4, drop.getIdentity().getOriginId());
            ps.setString(5, drop.getIdentity().getUsername());
            ps.setString(6, drop.getIdentity().getName());
            ps.setString(7, drop.getIdentity().getAvatar());
        }

        public int getBatchSize() {
            return hashes.size();
        }
    });
}


@Autowired
public void setDataSource(DataSource dataSource){
    this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    this.jdbcTemplate = new JdbcTemplate(dataSource);
}


@Override
public void getIdentities(List<Drop> drops){
    // Get a lock
    Sequence seq = sequenceDao.findById("identities");
    Map<String, List<Integer>> newIdentityIndex = getNewIdentityIndex(drops);
    if (newIdentityIndex.size() > 0) {
        // Find identities that already exist in the db
        updateNewIdentityIndex(newIdentityIndex, drops);
        // Insert new identities into the db
        batchInsert(newIdentityIndex, drops, seq);
    }
}


public void setIdentity(long idYKYP,Identity identity)

public Identity getIdentity(long idYKYP)

public void setName(long id,String name)

public void setAvatar(long id,String avatar)

}