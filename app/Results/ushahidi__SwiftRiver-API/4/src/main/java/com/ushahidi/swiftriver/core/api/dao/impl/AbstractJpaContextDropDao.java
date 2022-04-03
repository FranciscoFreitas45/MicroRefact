package com.ushahidi.swiftriver.core.api.dao.impl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.ushahidi.swiftriver.core.api.dao.ContextDropDao;
import com.ushahidi.swiftriver.core.api.dao.GenericDao;
import com.ushahidi.swiftriver.core.model.Account;
import com.ushahidi.swiftriver.core.model.Bucket;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Link;
import com.ushahidi.swiftriver.core.model.Media;
import com.ushahidi.swiftriver.core.model.MediaThumbnail;
import com.ushahidi.swiftriver.core.model.Place;
import com.ushahidi.swiftriver.core.model.Tag;
import com.ushahidi.swiftriver.core.DTO.Media;
import com.ushahidi.swiftriver.core.DTO.MediaThumbnail;
public class AbstractJpaContextDropDao extends AbstractJpaDao<T>implements ContextDropDao,GenericDao<T>{

 protected  String tagsQuery;

 protected  String linksQuery;

 protected  String dropImageQuery;

 protected  String mediaQuery;

 protected  String placesQuery;

 protected  String contextDropQuery;

@PersistenceContext
 protected  EntityManager em;

 protected  NamedParameterJdbcTemplate namedJdbcTemplate;


public void populateForms(List<Drop> drops)


public void populateBuckets(List<Drop> drops,Account queryingAccount){
    Map<Long, Integer> dropsIndex = new HashMap<Long, Integer>();
    int i = 0;
    for (Drop drop : drops) {
        dropsIndex.put(drop.getId(), i);
        i++;
    }
    // Query to fetch the buckets
    String sql = "SELECT `buckets_droplets`.`droplet_id` AS `id`, `buckets`.`id` AS `bucket_id`, `buckets`.`bucket_name` ";
    sql += "FROM `buckets` ";
    sql += "INNER JOIN `buckets_droplets` ON (`buckets`.`id` = `buckets_droplets`.`bucket_id`) ";
    sql += "WHERE `buckets_droplets`.`droplet_id` IN (:dropletIds) ";
    sql += "AND `buckets`.`bucket_publish` = 1 ";
    sql += "UNION ALL ";
    sql += "SELECT `buckets_droplets`.`droplet_id` AS `id`, `buckets`.`id` AS `bucket_id`, `buckets`.`bucket_name` ";
    sql += "FROM `buckets` ";
    sql += "INNER JOIN `buckets_droplets` ON (`buckets`.`id` = `buckets_droplets`.`bucket_id`) ";
    sql += "LEFT JOIN `accounts` ON (`buckets`.`account_id` = `accounts`.`id` AND `buckets`.`account_id` = :accountId) ";
    sql += "LEFT JOIN `bucket_collaborators` ON (`bucket_collaborators`.`bucket_id` = `buckets`.`id` AND `bucket_collaborators`.`account_id` = :accountId) ";
    sql += "WHERE `buckets_droplets`.`droplet_id` IN (:dropletIds) ";
    sql += "AND `buckets`.`bucket_publish` = 0 ";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("dropletIds", dropsIndex.keySet());
    params.addValue("accountId", (Long) queryingAccount.getId());
    List<Map<String, Object>> results = this.namedJdbcTemplate.queryForList(sql, params);
    // Group the buckets per drop
    Map<Long, List<Bucket>> dropBucketsMap = new HashMap<Long, List<Bucket>>();
    for (Map<String, Object> row : results) {
        Long bucketDropId = ((Number) row.get("id")).longValue();
        List<Bucket> dropBuckets = dropBucketsMap.get(bucketDropId);
        if (dropBuckets == null) {
            dropBuckets = new ArrayList<Bucket>();
        }
        // Create the bucket
        Bucket bucket = new Bucket();
        bucket.setId(((Number) row.get("bucket_id")).longValue());
        bucket.setName((String) row.get("bucket_name"));
        // Add to the list of buckets for the current drop
        dropBuckets.add(bucket);
        dropBucketsMap.put(bucketDropId, dropBuckets);
    }
    // Populate the buckets for the submitted drops
    for (Map.Entry<Long, List<Bucket>> entry : dropBucketsMap.entrySet()) {
        Long dropId = entry.getKey();
        // Retrieve the drop
        Drop drop = drops.get(dropsIndex.get(dropId));
        drop.setBuckets(entry.getValue());
    }
}


public EntityManager getEm(){
    return em;
}


public void populatePlaces(List<Drop> drops){
    Map<Long, Integer> dropIndex = new HashMap<Long, Integer>();
    int i = 0;
    for (Drop drop : drops) {
        dropIndex.put(drop.getId(), i);
        i++;
    }
    Query query = em.createNativeQuery(placesQuery);
    query.setParameter("drop_ids", dropIndex.keySet());
    // Group the media by drop id
    Map<Long, Place> places = new HashMap<Long, Place>();
    for (Object oRow : query.getResultList()) {
        Object[] r = (Object[]) oRow;
        Long dropId = ((Number) r[0]).longValue();
        Drop drop = drops.get(dropIndex.get(dropId));
        if (drop.getPlaces() == null) {
            drop.setPlaces(new ArrayList<Place>());
        }
        Long placeId = ((Number) r[1]).longValue();
        Place p = places.get(placeId);
        if (p == null) {
            p = new Place();
            p.setId(placeId);
            p.setPlaceName((String) r[2]);
            p.setLatitude(((Number) r[5]).floatValue());
            p.setLongitude(((Number) r[6]).floatValue());
            places.put(placeId, p);
        }
        // Add place to drop
        if (!drop.getPlaces().contains(p)) {
            drop.getPlaces().add(p);
        }
    }
}


public void populateMedia(List<Drop> drops){
    Map<Long, Integer> dropIndex = new HashMap<Long, Integer>();
    int i = 0;
    for (Drop drop : drops) {
        dropIndex.put(drop.getId(), i);
        i++;
    }
    Query query = em.createNativeQuery(dropImageQuery);
    query.setParameter("drop_ids", dropIndex.keySet());
    Map<Long, Long> dropImagesMap = new HashMap<Long, Long>();
    for (Object oRow2 : query.getResultList()) {
        Object[] r2 = (Object[]) oRow2;
        dropImagesMap.put(((Number) r2[0]).longValue(), ((Number) r2[1]).longValue());
    }
    query = em.createNativeQuery(mediaQuery);
    query.setParameter("drop_ids", dropIndex.keySet());
    // Group the media by drop id
    for (Object oRow : query.getResultList()) {
        Object[] r = (Object[]) oRow;
        Long dropId = ((Number) r[0]).longValue();
        Drop drop = drops.get(dropIndex.get(dropId));
        if (drop.getMedia() == null) {
            drop.setMedia(new ArrayList<Media>());
        }
        Long mediaId = ((Number) r[1]).longValue();
        Media m = null;
        for (Media x : drop.getMedia()) {
            if (x.getId() == mediaId) {
                m = x;
            }
        }
        if (m == null) {
            m = new Media();
            m.setId(mediaId);
            m.setUrl((String) r[2]);
            m.setType((String) r[3]);
        }
        // Add thumbnails
        if (r[4] != null) {
            MediaThumbnail mt = new MediaThumbnail();
            mt.setMedia(m);
            mt.setSize((Integer) r[4]);
            mt.setUrl((String) r[5]);
            List<MediaThumbnail> thumbnails = m.getThumbnails();
            if (thumbnails == null) {
                thumbnails = new ArrayList<MediaThumbnail>();
                m.setThumbnails(thumbnails);
            }
            thumbnails.add(mt);
        }
        if (!drop.getMedia().contains(m)) {
            drop.getMedia().add(m);
            // Set the droplet image if any
            Long dropImageId = dropImagesMap.get(drop.getId());
            if (dropImageId != null && dropImageId == m.getId()) {
                drop.setImage(m);
            }
        }
    }
}


@Autowired
public void setDataSource(DataSource dataSource){
    this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
}


public void populateLinks(List<Drop> drops){
    List<Long> dropIds = new ArrayList<Long>();
    for (Drop drop : drops) {
        dropIds.add(drop.getId());
    }
    Query query = em.createNativeQuery(linksQuery);
    query.setParameter("drop_ids", dropIds);
    // Group the links by drop id
    Map<Long, List<Link>> links = new HashMap<Long, List<Link>>();
    for (Object oRow : query.getResultList()) {
        Object[] r = (Object[]) oRow;
        Long dropId = ((Number) r[0]).longValue();
        Link link = new Link();
        link.setId(((Number) r[1]).longValue());
        link.setUrl((String) r[2]);
        List<Link> l = links.get(dropId);
        if (l == null) {
            l = new ArrayList<Link>();
            links.put(dropId, l);
        }
        l.add(link);
    }
    for (Drop drop : drops) {
        List<Link> l = links.get(drop.getId());
        if (l != null) {
            drop.setLinks(l);
        } else {
            drop.setLinks(new ArrayList<Link>());
        }
    }
}


public void populateTags(List<Drop> drops){
    List<Long> dropIds = new ArrayList<Long>();
    for (Drop drop : drops) {
        dropIds.add(drop.getId());
    }
    Query query = em.createNativeQuery(tagsQuery);
    query.setParameter("drop_ids", dropIds);
    // Group the tags by drop id
    Map<Long, List<Tag>> tags = new HashMap<Long, List<Tag>>();
    for (Object oRow : query.getResultList()) {
        Object[] r = (Object[]) oRow;
        Long dropId = ((Number) r[0]).longValue();
        Tag tag = new Tag();
        tag.setId(((Number) r[1]).longValue());
        tag.setTag((String) r[2]);
        tag.setType((String) r[4]);
        List<Tag> t = tags.get(dropId);
        if (t == null) {
            t = new ArrayList<Tag>();
            tags.put(dropId, t);
        }
        t.add(tag);
    }
    for (Drop drop : drops) {
        List<Tag> t = tags.get(drop.getId());
        if (t != null) {
            drop.setTags(t);
        } else {
            drop.setTags(new ArrayList<Tag>());
        }
    }
}


public void setEm(EntityManager em){
    this.em = em;
}


@Override
public void populateMetadata(List<Drop> drops,Account queryingAccount){
    if (drops.size() == 0) {
        return;
    }
    populateTags(drops);
    populateLinks(drops);
    populateMedia(drops);
    populatePlaces(drops);
    populateBuckets(drops, queryingAccount);
    populateForms(drops);
}


}