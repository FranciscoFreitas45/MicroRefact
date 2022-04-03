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
import com.ushahidi.swiftriver.core.api.dao.LinkDao;
import com.ushahidi.swiftriver.core.api.dao.SequenceDao;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Link;
import com.ushahidi.swiftriver.core.model.Sequence;
import com.ushahidi.swiftriver.core.util.MD5Util;
@Repository
public class JpaLinkDao extends AbstractJpaDao<Link>implements LinkDao{

@Autowired
 private  SequenceDao sequenceDao;

 private  NamedParameterJdbcTemplate namedJdbcTemplate;

 private  JdbcTemplate jdbcTemplate;


public Map<String,List<int[]>> getNewLinkIndex(List<Drop> drops){
    Map<String, List<int[]>> newLinkIndex = new HashMap<String, List<int[]>>();
    // Generate hashes for each new drops i.e. those without an id
    for (int i = 0; i < drops.size(); i++) {
        Drop drop = drops.get(i);
        List<Link> links = drop.getLinks();
        if (links == null)
            continue;
        for (int j = 0; j < links.size(); j++) {
            Link link = links.get(j);
            // Cleanup the link
            link.setUrl(link.getUrl().trim());
            String hash = MD5Util.md5Hex(link.getUrl());
            link.setHash(hash);
            // Keep a record of where this hash is in the drop list
            List<int[]> indexes;
            if (newLinkIndex.containsKey(hash)) {
                indexes = newLinkIndex.get(hash);
            } else {
                indexes = new ArrayList<int[]>();
                newLinkIndex.put(hash, indexes);
            }
            // Location of the link in the drops array is an i,j tuple
            int[] loc = { i, j };
            indexes.add(loc);
        }
    }
    return newLinkIndex;
}


public int getBatchSize(){
    return originalUrls.size();
}


public void updateNewLinkIndex(Map<String,List<int[]>> newLinkIndex,List<Drop> drops){
    // First find and update existing drops with their ids.
    String sql = "SELECT id, hash FROM links WHERE hash IN (:hashes)";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("hashes", newLinkIndex.keySet());
    List<Map<String, Object>> results = this.namedJdbcTemplate.queryForList(sql, params);
    // Update id for the drops that were found
    for (Map<String, Object> result : results) {
        String hash = (String) result.get("hash");
        Long id = ((Number) result.get("id")).longValue();
        List<int[]> indexes = newLinkIndex.get(hash);
        for (int[] index : indexes) {
            drops.get(index[0]).getLinks().get(index[1]).setId(id);
        }
        // Hash is not for a new drop so remove it
        newLinkIndex.remove(hash);
    }
}


public void insertDropletLinks(List<Drop> drops){
    // List of drop IDs in the drops list
    List<Long> dropIds = new ArrayList<Long>();
    // List of links in a drop
    Map<Long, Set<Long>> dropletLinksMap = new HashMap<Long, Set<Long>>();
    // List of drops and the link that is their original url
    final List<long[]> originalUrls = new ArrayList<long[]>();
    for (Drop drop : drops) {
        if (drop.getLinks() == null)
            continue;
        dropIds.add(drop.getId());
        for (Link link : drop.getLinks()) {
            Set<Long> links = null;
            if (dropletLinksMap.containsKey(drop.getId())) {
                links = dropletLinksMap.get(drop.getId());
            } else {
                links = new HashSet<Long>();
                dropletLinksMap.put(drop.getId(), links);
            }
            // Is this link the original url?
            if (drop.getOriginalUrl() != null && link.getUrl().equals(drop.getOriginalUrl().getUrl())) {
                long[] originalUrl = { drop.getId(), link.getId() };
                originalUrls.add(originalUrl);
            }
            links.add(link.getId());
        }
    }
    // Find droplet links that already exist in the db
    String sql = "SELECT droplet_id, link_id FROM droplets_links WHERE droplet_id in (:ids)";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("ids", dropIds);
    List<Map<String, Object>> results = this.namedJdbcTemplate.queryForList(sql, params);
    // Remove already existing droplet_links from our Set
    for (Map<String, Object> result : results) {
        long dropletId = ((Number) result.get("droplet_id")).longValue();
        long linkId = ((Number) result.get("link_id")).longValue();
        Set<Long> linkSet = dropletLinksMap.get(dropletId);
        if (linkSet != null) {
            linkSet.remove(linkId);
        }
    }
    // Insert the remaining items in the set into the db
    sql = "INSERT INTO droplets_links (droplet_id, link_id) VALUES (?,?)";
    final List<long[]> dropletLinksList = new ArrayList<long[]>();
    for (Long dropletId : dropletLinksMap.keySet()) {
        for (Long linkId : dropletLinksMap.get(dropletId)) {
            long[] dropletLink = { dropletId, linkId };
            dropletLinksList.add(dropletLink);
        }
    }
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

        public void setValues(PreparedStatement ps, int i) throws SQLException {
            long[] dropletLink = dropletLinksList.get(i);
            ps.setLong(1, dropletLink[0]);
            ps.setLong(2, dropletLink[1]);
        }

        public int getBatchSize() {
            return dropletLinksList.size();
        }
    });
    if (originalUrls.size() > 0) {
        sql = "UPDATE droplets SET original_url = ? WHERE id = ?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                long[] update = originalUrls.get(i);
                ps.setLong(1, update[1]);
                ps.setLong(2, update[0]);
            }

            public int getBatchSize() {
                return originalUrls.size();
            }
        });
    }
}


@Override
public void getLinks(List<Drop> drops){
    // Get a lock
    Sequence seq = sequenceDao.findById("links");
    Map<String, List<int[]>> newLinkIndex = getNewLinkIndex(drops);
    if (newLinkIndex.size() > 0) {
        // Find links that already exist in the db
        updateNewLinkIndex(newLinkIndex, drops);
        // Insert new link into the db
        batchInsert(newLinkIndex, drops, seq);
    }
}


public void setValues(PreparedStatement ps,int i) throws SQLException{
    long[] update = originalUrls.get(i);
    ps.setLong(1, update[1]);
    ps.setLong(2, update[0]);
}


public void batchInsert(Map<String,List<int[]>> newLinkIndex,List<Drop> drops,Sequence seq){
    final List<String> hashes = new ArrayList<String>();
    hashes.addAll(newLinkIndex.keySet());
    final long startKey = sequenceDao.getIds(seq, hashes.size());
    String sql = "INSERT INTO links (id, hash, url) " + "VALUES (?,?,?)";
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

        public void setValues(PreparedStatement ps, int i) throws SQLException {
            String hash = hashes.get(i);
            // Update drops with the newly generated id
            for (int[] index : newLinkIndex.get(hash)) {
                drops.get(index[0]).getLinks().get(index[1]).setId(startKey + i);
            }
            int[] index = newLinkIndex.get(hash).get(0);
            Link link = drops.get(index[0]).getLinks().get(index[1]);
            ps.setLong(1, link.getId());
            ps.setString(2, link.getHash());
            ps.setString(3, link.getUrl());
        }

        public int getBatchSize() {
            return hashes.size();
        }
    });
    // Update the droplet_links table
    insertDropletLinks(drops);
}


@Override
public Link create(Link t){
    t.setHash(MD5Util.md5Hex(t.getUrl()));
    return super.create(t);
}


@Autowired
public void setDataSource(DataSource dataSource){
    this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    this.jdbcTemplate = new JdbcTemplate(dataSource);
}


@SuppressWarnings("unchecked")
public Link findByHash(String hash){
    String sql = "FROM Link WHERE hash = :hash";
    List<Link> links = (List<Link>) em.createQuery(sql).setParameter("hash", hash).getResultList();
    return links.isEmpty() ? null : links.get(0);
}


public void setLink(long idM175,Link link)

public Link getLink(long idM175)

public void setLink(long idOZP4,Link link)

public Link getLink(long idOZP4)

public Link getOriginalUrl(long idLCK8)

public void setOriginalUrl(long idLCK8,Link originalUrl)

public void setUrl(long id,String url)

public void setHash(long id,String hash)

public void setId(long id,long id)

}