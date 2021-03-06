package com.ushahidi.swiftriver.core.api.dao.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ushahidi.swiftriver.core.api.dao.BucketDao;
import com.ushahidi.swiftriver.core.api.dao.BucketDropDao;
import com.ushahidi.swiftriver.core.api.filter.DropFilter;
import com.ushahidi.swiftriver.core.model.Account;
import com.ushahidi.swiftriver.core.model.Bucket;
import com.ushahidi.swiftriver.core.model.BucketCollaborator;
import com.ushahidi.swiftriver.core.model.BucketComment;
import com.ushahidi.swiftriver.core.model.BucketDrop;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.model.Identity;
import com.ushahidi.swiftriver.core.model.Link;
import com.ushahidi.swiftriver.core.util.TextUtil;
import com.ushahidi.swiftriver.core.Interface.BucketDropDao;
import com.ushahidi.swiftriver.core.DTO.BucketDrop;
import com.ushahidi.swiftriver.core.DTO.DropFilter;
import com.ushahidi.swiftriver.core.DTO.Drop;
import com.ushahidi.swiftriver.core.DTO.Identity;
import com.ushahidi.swiftriver.core.DTO.Link;
@Repository
public class JpaBucketDao extends AbstractJpaDao<Bucket>implements BucketDao{

 final  Logger LOG;

@Autowired
 private  BucketDropDao dropDao;

 private  NamedParameterJdbcTemplate jdbcTemplate;


@SuppressWarnings("unchecked")
public List<BucketCollaborator> getCollaborators(long bucketId){
    String hql = "SELECT b.collaborators FROM Bucket b WHERE b.id = ?1";
    Query query = em.createQuery(hql);
    query.setParameter(1, bucketId);
    return (List<BucketCollaborator>) query.getResultList();
}


public void decreaseDropCount(Bucket bucket){
    int dropCount = bucket.getDropCount() - 1;
    bucket.setDropCount(dropCount);
    this.update(bucket);
}


@SuppressWarnings("unchecked")
public Bucket findBucketByName(Account account,String bucketName){
    String sql = "FROM Bucket b WHERE account = :account AND name = :name";
    Query query = this.em.createQuery(sql);
    query.setParameter("account", account);
    query.setParameter("name", bucketName);
    List<Bucket> results = (List<Bucket>) query.getResultList();
    return results.isEmpty() ? null : results.get(0);
}


public void increaseDropCount(Bucket bucket){
    int dropCount = bucket.getDropCount() + 1;
    bucket.setDropCount(dropCount);
    this.update(bucket);
}


@Override
public Bucket update(Bucket bucket){
    bucket.setBucketNameCanonical(TextUtil.getURLSlug(bucket.getName()));
    return super.update(bucket);
}


public List<Bucket> findAll(String searchTerm,int count,int page){
    String qlString = "SELECT b FROM Bucket b WHERE " + "b.published = 1 " + "AND (b.name LIKE :term OR b.description LIKE :term " + "OR b.bucketNameCanonical LIKE :term)";
    TypedQuery<Bucket> query = em.createQuery(qlString, Bucket.class);
    query.setParameter("term", "%" + searchTerm + "%");
    query.setMaxResults(count);
    query.setFirstResult(count * (page - 1));
    return query.getResultList();
}


public BucketCollaborator addCollaborator(Bucket bucket,Account account,boolean readOnly){
    BucketCollaborator collaborator = new BucketCollaborator();
    collaborator.setBucket(bucket);
    collaborator.setAccount(account);
    collaborator.setReadOnly(readOnly);
    collaborator.setDateAdded(new Date());
    bucket.getCollaborators().add(collaborator);
    this.em.persist(collaborator);
    return collaborator;
}


public void updateCollaborator(BucketCollaborator collaborator){
    this.em.merge(collaborator);
}


public void deleteCollaborator(BucketCollaborator collaborator){
    this.em.remove(collaborator);
}


public boolean addDrop(Bucket bucket,Drop drop){
    BucketDrop bucketDrop = new BucketDrop();
    bucketDrop.setDrop(drop);
    bucketDrop.setBucket(bucket);
    bucketDrop.setDateAdded(new Date());
    bucketDrop.setVeracity(1L);
    this.em.persist(bucketDrop);
    return true;
}


public BucketCollaborator findCollaborator(Long bucketId,Long accountId){
    String sql = "FROM BucketCollaborator bc " + "WHERE bc.account.id = :accountId " + "AND bc.bucket.id =:bucketId";
    Query query = this.em.createQuery(sql);
    query.setParameter("accountId", accountId);
    query.setParameter("bucketId", bucketId);
    BucketCollaborator collaborator = null;
    try {
        collaborator = (BucketCollaborator) query.getSingleResult();
    } catch (Exception e) {
    // Do nothing;
    }
    return collaborator;
}


public BucketDrop findDrop(Long bucketId,Long dropId){
    String sql = "FROM BucketDrop WHERE bucket.id = :bucketId AND drop.id = :dropId";
    TypedQuery<BucketDrop> query = em.createQuery(sql, BucketDrop.class);
    query.setParameter("bucketId", bucketId);
    query.setParameter("dropId", dropId);
    List<BucketDrop> bucketDrops = query.getResultList();
    return bucketDrops.isEmpty() ? null : bucketDrops.get(0);
}


@Override
public Bucket create(Bucket bucket){
    bucket.setBucketNameCanonical(TextUtil.getURLSlug(bucket.getName()));
    bucket.setDropCount(0);
    return super.create(bucket);
}


@Autowired
public void setDataSource(DataSource dataSource){
    jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
}


@Override
public List<Drop> getDrops(Long bucketId,DropFilter filter,int page,int dropCount,Account queryingAccount){
    String sql = "SELECT droplets.id, buckets_droplets.id AS tracking_id, droplet_title, ";
    sql += "droplet_content, droplets.channel, identities.id AS identity_id, identity_name, ";
    sql += "identity_avatar, droplets.droplet_date_pub, droplet_orig_id, ";
    sql += "user_scores.score AS user_score, links.id AS original_url_id, ";
    sql += "links.url AS original_url, comment_count, bucket_droplets_read.buckets_droplets_id AS drop_read ";
    sql += "FROM buckets_droplets ";
    sql += "INNER JOIN droplets ON (buckets_droplets.droplet_id = droplets.id) ";
    sql += "INNER JOIN identities ON (droplets.identity_id = identities.id) ";
    sql += "LEFT JOIN droplet_scores AS user_scores ON (user_scores.droplet_id = droplets.id AND user_scores.user_id = :userId) ";
    sql += "LEFT JOIN links ON (droplets.original_url = links.id) ";
    sql += "LEFT JOIN bucket_droplets_read ON (bucket_droplets_read.buckets_droplets_id = buckets_droplets.id AND bucket_droplets_read.account_id = :accountId) ";
    sql += "WHERE buckets_droplets.droplet_date_added > '1970-01-01 00:00:00' ";
    sql += "AND buckets_droplets.bucket_id = :bucketId ";
    // Check for channel parameter
    if (filter.getChannels() != null && !filter.getChannels().isEmpty()) {
        sql += "AND droplets.channel IN (:channels) ";
    }
    if (filter.getPhotos() != null && filter.getPhotos()) {
        sql += "AND droplets.droplet_image > 0 ";
    }
    if (filter.getRead() != null) {
        if (filter.getRead()) {
            sql += "AND bucket_droplets_read.buckets_droplets_id IS NOT NULL ";
        } else {
            sql += "AND bucket_droplets_read.buckets_droplets_id IS NULL ";
        }
    }
    // Check for since_id
    if (filter.getSinceId() != null) {
        sql += " AND buckets_droplets.id > :since_id ";
    }
    if (filter.getMaxId() != null) {
        sql += " AND buckets_droplets.id <= :max_id ";
    }
    if (filter.getDateFrom() != null) {
        sql += "AND droplets.droplet_date_pub >= :date_from ";
    }
    if (filter.getDateTo() != null) {
        sql += "AND droplets.droplet_date_pub <= :date_to ";
    }
    if (filter.getDropIds() != null && !filter.getDropIds().isEmpty()) {
        sql += "AND `droplets`.`id` IN (:dropIds) ";
    }
    boolean newer = filter.getSinceId() != null;
    if (newer) {
        sql += "ORDER BY buckets_droplets.droplet_date_added ASC ";
    } else {
        sql += "ORDER BY buckets_droplets.droplet_date_added DESC ";
    }
    sql += "LIMIT " + dropCount + " OFFSET " + dropCount * (page - 1);
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("userId", queryingAccount.getOwner().getId());
    params.addValue("accountId", queryingAccount.getId());
    params.addValue("bucketId", bucketId);
    // since_id
    if (filter.getSinceId() != null) {
        params.addValue("since_id", filter.getSinceId());
    }
    // max_id
    if (filter.getMaxId() != null) {
        params.addValue("max_id", filter.getMaxId());
    }
    if (filter.getChannels() != null && !filter.getChannels().isEmpty()) {
        params.addValue("channels", filter.getChannels());
    }
    if (filter.getDateFrom() != null) {
        params.addValue("date_from", filter.getDateFrom());
    }
    if (filter.getDateTo() != null) {
        params.addValue("date_to", filter.getDateTo());
    }
    if (filter.getDropIds() != null && !filter.getDropIds().isEmpty()) {
        params.addValue("dropIds", filter.getDropIds());
    }
    List<Map<String, Object>> results = this.jdbcTemplate.queryForList(sql, params);
    List<Drop> drops = new ArrayList<Drop>();
    for (Map<String, Object> result : results) {
        Drop drop = new Drop();
        // Set the drop properties
        drop.setId(((Number) result.get("id")).longValue());
        drop.setTrackingId(((Number) result.get("tracking_id")).longValue());
        drop.setTitle((String) result.get("droplet_title"));
        drop.setContent((String) result.get("droplet_content"));
        drop.setChannel((String) result.get("channel"));
        drop.setDatePublished((Date) result.get("droplet_date_pub"));
        drop.setOriginalId((String) result.get("droplet_orig_id"));
        drop.setCommentCount(((Number) result.get("comment_count")).intValue());
        drop.setRead((Long) result.get("drop_read") != null);
        Identity identity = new Identity();
        identity.setId(((Number) result.get("identity_id")).longValue());
        identity.setName((String) result.get("identity_name"));
        identity.setAvatar((String) result.get("identity_avatar"));
        drop.setIdentity(identity);
        // Set drop url
        if (result.get("original_url_id") != null) {
            Link originalUrl = new Link();
            originalUrl.setId(((Number) result.get("original_url_id")).longValue());
            originalUrl.setUrl((String) result.get("original_url"));
            drop.setOriginalUrl(originalUrl);
        }
        drops.add(drop);
    }
    if (!drops.isEmpty()) {
        // Populate the metadata
        dropDao.populateMetadata(drops, queryingAccount);
    }
    return drops;
}


public BucketDrop findBucketDrop(Long bucketId,Long dropId){
    String qlString = "FROM BucketDrop WHERE bucket.id = :bucketId AND drop.id = :dropId";
    BucketDrop bucketDrop = null;
    try {
        TypedQuery<BucketDrop> query = em.createQuery(qlString, BucketDrop.class);
        query.setParameter("bucketId", bucketId);
        query.setParameter("dropId", dropId);
        bucketDrop = query.getSingleResult();
    } catch (NoResultException e) {
    }
    return bucketDrop;
}


public boolean deleteDrop(Long id,Long dropId){
    String sql = "DELETE FROM buckets_droplets " + "WHERE bucket_id = :bucketId " + "AND droplet_id = :dropId";
    Query query = this.em.createNativeQuery(sql);
    query.setParameter("bucketId", id);
    query.setParameter("dropId", dropId);
    return query.executeUpdate() == 1;
}


public BucketComment addComment(Bucket bucket,String commentText,Account account){
    BucketComment bucketComment = new BucketComment();
    bucketComment.setBucket(bucket);
    bucketComment.setAccount(account);
    bucketComment.setCommentText(commentText);
    bucketComment.setDateAdded(new Date());
    this.em.persist(bucketComment);
    return bucketComment;
}


public void setBucket(long idFGI4,Bucket bucket)

public Bucket getBucket(long idFGI4)

public List<Bucket> getCollaboratingBuckets(long id)

public List<Bucket> getFollowingBuckets(long id)

public void setCollaboratingBuckets(long id,List<Bucket> collaboratingBuckets)

public void setBuckets(long id,List<Bucket> buckets)

public List<Bucket> getBuckets(long id)

public void setFollowingBuckets(long id,List<Bucket> followingBuckets)

public boolean isPublished(long id)

public void setName(long id,String name)

public void setPublished(long id,boolean published)

public void setAccount(long id,Account account)

public void setDateAdded(long id,Date dateAdded)

public boolean isActive(long id)

public void setActive(long id,boolean active)

public void setReadOnly(long id,boolean readOnly)

public void setId(long id,long id)

}