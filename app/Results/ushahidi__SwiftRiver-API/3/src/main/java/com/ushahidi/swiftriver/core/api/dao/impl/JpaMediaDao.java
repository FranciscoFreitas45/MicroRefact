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
import com.ushahidi.swiftriver.core.api.dao.MediaDao;
import com.ushahidi.swiftriver.core.api.dao.SequenceDao;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Media;
import com.ushahidi.swiftriver.core.model.MediaThumbnail;
import com.ushahidi.swiftriver.core.model.Sequence;
import com.ushahidi.swiftriver.core.util.MD5Util;
import com.ushahidi.swiftriver.core.DTO.Drop;
@Repository
public class JpaMediaDao extends AbstractJpaDao<Media>implements MediaDao{

@Autowired
 private  SequenceDao sequenceDao;

 private  NamedParameterJdbcTemplate namedJdbcTemplate;

 private  JdbcTemplate jdbcTemplate;

 private  int thumbnailCount;


public int getBatchSize(){
    return dropImages.size();
}


public void insertDropletMedia(List<Drop> drops){
    // List of drop IDs in the drops list
    List<Long> dropIds = new ArrayList<Long>();
    // List of media in a drop
    Map<Long, Set<Long>> dropletMediaMap = new HashMap<Long, Set<Long>>();
    // List of drops and the media that is the drop image
    final List<long[]> dropImages = new ArrayList<long[]>();
    for (Drop drop : drops) {
        if (drop.getMedia() == null)
            continue;
        dropIds.add(drop.getId());
        for (Media media : drop.getMedia()) {
            Set<Long> m = null;
            if (dropletMediaMap.containsKey(drop.getId())) {
                m = dropletMediaMap.get(drop.getId());
            } else {
                m = new HashSet<Long>();
                dropletMediaMap.put(drop.getId(), m);
            }
            // Is this the drop image?
            if (drop.getImage() != null && media.getUrl().equals(drop.getImage().getUrl())) {
                long[] dropImage = { drop.getId(), media.getId() };
                dropImages.add(dropImage);
            }
            m.add(media.getId());
        }
    }
    // Find droplet media that already exist in the db
    String sql = "SELECT droplet_id, media_id FROM droplets_media WHERE droplet_id in (:ids)";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("ids", dropIds);
    List<Map<String, Object>> results = this.namedJdbcTemplate.queryForList(sql, params);
    // Remove already existing droplet_media from our Set
    for (Map<String, Object> result : results) {
        long dropletId = ((Number) result.get("droplet_id")).longValue();
        long mediaId = ((Number) result.get("media_id")).longValue();
        Set<Long> mediaSet = dropletMediaMap.get(dropletId);
        if (mediaSet != null) {
            mediaSet.remove(mediaId);
        }
    }
    // Insert the remaining items in the set into the db
    sql = "INSERT INTO droplets_media (droplet_id, media_id) VALUES (?,?)";
    final List<long[]> dropletMediaList = new ArrayList<long[]>();
    for (Long dropletId : dropletMediaMap.keySet()) {
        for (Long mediaId : dropletMediaMap.get(dropletId)) {
            long[] dropletMedia = { dropletId, mediaId };
            dropletMediaList.add(dropletMedia);
        }
    }
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

        public void setValues(PreparedStatement ps, int i) throws SQLException {
            long[] dropletMedia = dropletMediaList.get(i);
            ps.setLong(1, dropletMedia[0]);
            ps.setLong(2, dropletMedia[1]);
        }

        public int getBatchSize() {
            return dropletMediaList.size();
        }
    });
    if (dropImages.size() > 0) {
        sql = "UPDATE droplets SET droplet_image = ? WHERE id = ?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                long[] update = dropImages.get(i);
                ps.setLong(1, update[1]);
                ps.setLong(2, update[0]);
            }

            public int getBatchSize() {
                return dropImages.size();
            }
        });
    }
}


public Map<String,List<int[]>> getNewMediaIndex(List<Drop> drops){
    Map<String, List<int[]>> newMediaIndex = new HashMap<String, List<int[]>>();
    // Generate hashes for each new drops i.e. those without an id
    for (int i = 0; i < drops.size(); i++) {
        Drop drop = drops.get(i);
        if (drop.getMedia() == null)
            continue;
        for (int j = 0; j < drop.getMedia().size(); j++) {
            Media media = drop.getMedia().get(j);
            // Cleanup the media
            media.setUrl(media.getUrl().trim());
            String hash = getHash(media);
            media.setHash(hash);
            // Keep a record of where this hash is in the drop list
            List<int[]> indexes;
            if (newMediaIndex.containsKey(hash)) {
                indexes = newMediaIndex.get(hash);
            } else {
                indexes = new ArrayList<int[]>();
                newMediaIndex.put(hash, indexes);
            }
            // Location of the media in the drops array is an i,j tuple
            int[] loc = { i, j };
            indexes.add(loc);
        }
    }
    return newMediaIndex;
}


@Override
public void getMedia(List<Drop> drops){
    // Get a lock
    Sequence seq = sequenceDao.findById("media");
    Map<String, List<int[]>> newMediaIndex = getNewMediaIndex(drops);
    if (newMediaIndex.size() > 0) {
        // Find media that already exist in the db
        updateNewMediaIndex(newMediaIndex, drops);
        // Insert new media into the db
        batchInsert(newMediaIndex, drops, seq);
    }
}


public String getHash(Media m){
    return MD5Util.md5Hex(m.getUrl());
}


public void setValues(PreparedStatement ps,int i) throws SQLException{
    long[] update = dropImages.get(i);
    ps.setLong(1, update[1]);
    ps.setLong(2, update[0]);
}


public void batchInsert(Map<String,List<int[]>> newMediaIndex,List<Drop> drops,Sequence seq){
    final List<String> hashes = new ArrayList<String>();
    hashes.addAll(newMediaIndex.keySet());
    final long startKey = sequenceDao.getIds(seq, hashes.size());
    String sql = "INSERT INTO media (id, hash, url, type) " + "VALUES (?,?,?,?)";
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

        public void setValues(PreparedStatement ps, int i) throws SQLException {
            String hash = hashes.get(i);
            // Update drops with the newly generated id
            for (int[] index : newMediaIndex.get(hash)) {
                drops.get(index[0]).getMedia().get(index[1]).setId(startKey + i);
            }
            int[] index = newMediaIndex.get(hash).get(0);
            Media media = drops.get(index[0]).getMedia().get(index[1]);
            ps.setLong(1, media.getId());
            ps.setString(2, media.getHash());
            ps.setString(3, media.getUrl());
            ps.setString(4, media.getType());
        }

        public int getBatchSize() {
            return hashes.size();
        }
    });
    // Media Thumbnails
    sql = "INSERT INTO media_thumbnails(`media_id`, `size`, `url`) VALUES(?, ?, ?)";
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

        private int thumbnailCount = 0;

        public void setValues(PreparedStatement ps, int i) throws SQLException {
            String hash = hashes.get(i);
            int[] index = newMediaIndex.get(hash).get(0);
            Media media = drops.get(index[0]).getMedia().get(index[1]);
            for (MediaThumbnail thumbnail : media.getThumbnails()) {
                ps.setLong(1, media.getId());
                ps.setInt(2, thumbnail.getSize());
                ps.setString(3, thumbnail.getUrl());
                thumbnailCount++;
            }
        }

        public int getBatchSize() {
            return thumbnailCount;
        }
    });
    // Update the droplet_media table
    insertDropletMedia(drops);
}


@Autowired
public void setDataSource(DataSource dataSource){
    this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    this.jdbcTemplate = new JdbcTemplate(dataSource);
}


public void updateNewMediaIndex(Map<String,List<int[]>> newMediaIndex,List<Drop> drops){
    // First find and update existing drops with their ids.
    String sql = "SELECT id, hash FROM media WHERE hash IN (:hashes)";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("hashes", newMediaIndex.keySet());
    List<Map<String, Object>> results = this.namedJdbcTemplate.queryForList(sql, params);
    // Update id for the drops that were found
    for (Map<String, Object> result : results) {
        String hash = (String) result.get("hash");
        Long id = ((Number) result.get("id")).longValue();
        List<int[]> indexes = newMediaIndex.get(hash);
        for (int[] index : indexes) {
            drops.get(index[0]).getMedia().get(index[1]).setId(id);
        }
        // Hash is not for a new drop so remove it
        newMediaIndex.remove(hash);
    }
}


@SuppressWarnings("unchecked")
public List<Media> findByHash(ArrayList<String> mediaHashes){
    String sql = "FROM Media WHERE hash IN (?1)";
    return (List<Media>) em.createQuery(sql).setParameter(1, sql).getResultList();
}


public Media getImage(long idBTYE)

public void setImage(long idBTYE,Media image)

public void setId(long id,long id)

public void setUrl(long id,String url)

public void setType(long id,String type)

public void setSize(long id,int size)

public void setUrl(long id,String url)

public void setThumbnails(long id,List<MediaThumbnail> thumbnails)

}