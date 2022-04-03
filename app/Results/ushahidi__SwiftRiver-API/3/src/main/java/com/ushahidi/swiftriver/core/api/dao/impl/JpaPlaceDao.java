package com.ushahidi.swiftriver.core.api.dao.impl;
 import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.PlaceDao;
import com.ushahidi.swiftriver.core.api.dao.SequenceDao;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Place;
import com.ushahidi.swiftriver.core.model.Sequence;
import com.ushahidi.swiftriver.core.util.MD5Util;
@Repository
public class JpaPlaceDao extends AbstractJpaDao<Place>implements PlaceDao{

@Autowired
 private  SequenceDao sequenceDao;

 private  NamedParameterJdbcTemplate namedJdbcTemplate;

 private  JdbcTemplate jdbcTemplate;


public String getHash(Place p){
    String hash = MD5Util.md5Hex(p.getPlaceName() + p.getLongitude().toString() + p.getLatitude().toString());
    return hash;
}


public Map<String,List<int[]>> getNewPlaceIndex(List<Drop> drops){
    Map<String, List<int[]>> newPlaceIndex = new HashMap<String, List<int[]>>();
    // Generate hashes for each new drops i.e. those without an id
    for (int i = 0; i < drops.size(); i++) {
        Drop drop = drops.get(i);
        List<Place> places = drop.getPlaces();
        if (places == null)
            continue;
        for (int j = 0; j < places.size(); j++) {
            Place place = places.get(j);
            // Cleanup the place
            place = formatPlace(place);
            String hash = getHash(place);
            place.setHash(hash);
            // Keep a record of where this hash is in the drop list
            List<int[]> indexes;
            if (newPlaceIndex.containsKey(hash)) {
                indexes = newPlaceIndex.get(hash);
            } else {
                indexes = new ArrayList<int[]>();
                newPlaceIndex.put(hash, indexes);
            }
            // Location of the place in the drops array is an i,j tuple
            int[] loc = { i, j };
            indexes.add(loc);
        }
    }
    return newPlaceIndex;
}


public void batchInsert(Map<String,List<int[]>> newPlaceIndex,List<Drop> drops,Sequence seq){
    final List<String> hashes = new ArrayList<String>();
    hashes.addAll(newPlaceIndex.keySet());
    final long startKey = sequenceDao.getIds(seq, hashes.size());
    String sql = "INSERT INTO places (id, hash, place_name, " + "place_name_canonical, longitude, latitude) " + "VALUES (?,?,?,?,?,?)";
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

        public void setValues(PreparedStatement ps, int i) throws SQLException {
            String hash = hashes.get(i);
            // Update drops with the newly generated id
            for (int[] index : newPlaceIndex.get(hash)) {
                drops.get(index[0]).getPlaces().get(index[1]).setId(startKey + i);
            }
            int[] index = newPlaceIndex.get(hash).get(0);
            Place place = drops.get(index[0]).getPlaces().get(index[1]);
            ps.setLong(1, place.getId());
            ps.setString(2, place.getHash());
            ps.setString(3, place.getPlaceName());
            ps.setString(4, place.getPlaceNameCanonical());
            ps.setFloat(5, place.getLongitude());
            ps.setFloat(6, place.getLatitude());
        }

        public int getBatchSize() {
            return hashes.size();
        }
    });
    // Update the droplet_places table
    insertDropletPlaces(drops);
}


public Place formatPlace(Place place){
    place.setPlaceName(place.getPlaceName().trim());
    place.setPlaceNameCanonical(place.getPlaceName().toLowerCase());
    return place;
}


public void updateNewPlaceIndex(Map<String,List<int[]>> newPlaceIndex,List<Drop> drops){
    // First find and update existing drops with their ids.
    String sql = "SELECT id, hash FROM places WHERE hash IN (:hashes)";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("hashes", newPlaceIndex.keySet());
    List<Map<String, Object>> results = this.namedJdbcTemplate.queryForList(sql, params);
    // Update id for the drops that were found
    for (Map<String, Object> result : results) {
        String hash = (String) result.get("hash");
        Long id = ((Number) result.get("id")).longValue();
        List<int[]> indexes = newPlaceIndex.get(hash);
        for (int[] index : indexes) {
            drops.get(index[0]).getPlaces().get(index[1]).setId(id);
        }
        // Hash is not for a new drop so remove it
        newPlaceIndex.remove(hash);
    }
}


@SuppressWarnings("unchecked")
public Place findByHash(String hash){
    String sql = "FROM Place WHERE hash = :hash";
    List<Place> places = (List<Place>) em.createQuery(sql).setParameter("hash", hash).getResultList();
    return places.isEmpty() ? null : places.get(0);
}


public int getBatchSize(){
    return dropletPlacesList.size();
}


public void setValues(PreparedStatement ps,int i) throws SQLException{
    long[] dropletPlace = dropletPlacesList.get(i);
    ps.setLong(1, dropletPlace[0]);
    ps.setLong(2, dropletPlace[1]);
}


public Place findById(long placeId){
    return this.em.find(Place.class, placeId);
}


@Override
public void getPlaces(List<Drop> drops){
    // Get a lock
    Sequence seq = sequenceDao.findById("places");
    Map<String, List<int[]>> newPlaceIndex = getNewPlaceIndex(drops);
    if (newPlaceIndex.size() > 0) {
        // Find places that already exist in the db
        updateNewPlaceIndex(newPlaceIndex, drops);
        // Insert new place into the db
        batchInsert(newPlaceIndex, drops, seq);
    }
}


public Place create(Place place){
    place = formatPlace(place);
    place.setHash(getHash(place));
    return super.create(place);
}


@Autowired
public void setDataSource(DataSource dataSource){
    this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    this.jdbcTemplate = new JdbcTemplate(dataSource);
}


public void insertDropletPlaces(List<Drop> drops){
    // List of drop IDs in the drops list
    List<Long> dropIds = new ArrayList<Long>();
    // List of places in a drop
    Map<Long, Set<Long>> dropletPlacesMap = new HashMap<Long, Set<Long>>();
    for (Drop drop : drops) {
        if (drop.getPlaces() == null)
            continue;
        dropIds.add(drop.getId());
        for (Place place : drop.getPlaces()) {
            Set<Long> places = null;
            if (dropletPlacesMap.containsKey(drop.getId())) {
                places = dropletPlacesMap.get(drop.getId());
            } else {
                places = new HashSet<Long>();
                dropletPlacesMap.put(drop.getId(), places);
            }
            places.add(place.getId());
        }
    }
    // Find droplet places that already exist in the db
    String sql = "SELECT droplet_id, place_id FROM droplets_places WHERE droplet_id in (:ids)";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("ids", dropIds);
    List<Map<String, Object>> results = this.namedJdbcTemplate.queryForList(sql, params);
    // Remove already existing droplet_places from our Set
    for (Map<String, Object> result : results) {
        long dropletId = ((Number) result.get("droplet_id")).longValue();
        long placeId = ((Number) result.get("place_id")).longValue();
        Set<Long> placeSet = dropletPlacesMap.get(dropletId);
        if (placeSet != null) {
            placeSet.remove(placeId);
        }
    }
    // Insert the remaining items in the set into the db
    sql = "INSERT INTO droplets_places (droplet_id, place_id) VALUES (?,?)";
    final List<long[]> dropletPlacesList = new ArrayList<long[]>();
    for (Long dropletId : dropletPlacesMap.keySet()) {
        for (Long placeId : dropletPlacesMap.get(dropletId)) {
            long[] dropletPlace = { dropletId, placeId };
            dropletPlacesList.add(dropletPlace);
        }
    }
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

        public void setValues(PreparedStatement ps, int i) throws SQLException {
            long[] dropletPlace = dropletPlacesList.get(i);
            ps.setLong(1, dropletPlace[0]);
            ps.setLong(2, dropletPlace[1]);
        }

        public int getBatchSize() {
            return dropletPlacesList.size();
        }
    });
}


public Place getPlace(long id6ANM)

public void setPlace(long id6ANM,Place place)

public Place getPlace(long idEIIX)

public void setPlace(long idEIIX,Place place)

public List<Place> getPlaces(long id)

public void setPlaces(long id,List<Place> places)

public void setPlaceName(long id,String placeName)

public void setLatitude(long id,Float latitude)

public void setLongitude(long id,Float longitude)

public void setPlaceNameCanonical(long id,String placeNameCanonical)

public void setHash(long id,String hash)

public void setId(long id,long id)

}