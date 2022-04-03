package com.ushahidi.swiftriver.core.api.service;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ushahidi.swiftriver.core.api.dao.DropDao;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO;
import com.ushahidi.swiftriver.core.model.Drop;
import com.ushahidi.swiftriver.core.solr.DropDocument;
import com.ushahidi.swiftriver.core.solr.repository.DropDocumentRepository;
import com.ushahidi.swiftriver.core.Interface.DropDocumentRepository;
@Service
public class DropIndexService {

@Resource
 private  DropDocumentRepository repository;

@Autowired
 private  Mapper mapper;

@Autowired
 private  DropDao dropDao;

 private  NamedParameterJdbcTemplate namedJdbcTemplate;

 final  Logger logger;


@Transactional(readOnly = false)
public void addToIndex(Drop drop){
    List<Drop> drops = new ArrayList<Drop>();
    drops.add(drop);
    this.addAllToIndex(drops);
}


public void setRepository(DropDocumentRepository repository){
    this.repository = repository;
}


public void setDropDao(DropDao dropDao){
    this.dropDao = dropDao;
}


@Transactional(readOnly = false)
public void addAllToIndex(List<Drop> drops){
    // Set the places, bucket and river ids
    Map<Long, List<String>> dropPlaces = getDropPlaces(drops);
    populateRiverIds(drops);
    populateBucketIds(drops);
    logger.debug("Performing batch add of drop entities");
    List<DropDocument> documents = new ArrayList<DropDocument>();
    for (Drop drop : drops) {
        DropDocument document = mapper.map(drop, DropDocument.class);
        // Set the places
        document.setGeo(dropPlaces.get(drop.getId()));
        documents.add(document);
    }
    repository.save(documents);
    logger.debug("Successfully added {} drops to the search index", drops.size());
}


@Transactional(readOnly = false)
public void deleteFromIndex(Long dropId){
    logger.debug("Removing drop {} from the search index", dropId);
    repository.delete(dropId.toString());
}


public void populateBucketIds(List<Drop> drops){
    // Store the drop index
    Map<Long, Integer> dropIndex = new HashMap<Long, Integer>();
    List<Long> dropIds = new ArrayList<Long>();
    int index = 0;
    for (Drop drop : drops) {
        dropIds.add(drop.getId());
        dropIndex.put(drop.getId(), index);
        index++;
    }
    String sql = "SELECT `droplet_id`, `bucket_id` " + "FROM `buckets_droplets` WHERE `droplet_id` IN (:dropIds)";
    MapSqlParameterSource paramMap = new MapSqlParameterSource();
    paramMap.addValue("dropIds", dropIds);
    for (Map<String, Object> row : namedJdbcTemplate.queryForList(sql, paramMap)) {
        Long dropId = ((Number) row.get("droplet_id")).longValue();
        Long bucketId = ((Number) row.get("bucket_id")).longValue();
        Drop drop = drops.get(dropIndex.get(dropId));
        if (drop.getBucketIds() == null) {
            drop.setBucketIds(new ArrayList<Long>());
        }
        drop.getBucketIds().add(bucketId);
    }
}


public void setNamedJdbcTemplate(NamedParameterJdbcTemplate template){
    this.namedJdbcTemplate = template;
}


@Transactional
public List<GetDropDTO> findDrops(String searchTerm,int count,int page){
    // Pages are zero-indexed. Therefore, the first page is 0
    page = (page >= 1) ? page - 1 : page;
    // Search the index for drops containing searchTerm
    Pageable pageRequest = new PageRequest(page, count);
    List<DropDocument> dropDocuments = repository.find(searchTerm, pageRequest);
    List<GetDropDTO> drops = new ArrayList<GetDropDTO>();
    // Anything from the search?
    if (dropDocuments.isEmpty()) {
        // Log
        logger.debug(String.format("No documents found containing \"%s\" on page %d", searchTerm, count));
        // Return empty list
        return drops;
    }
    List<Long> dropIds = new ArrayList<Long>();
    for (DropDocument document : dropDocuments) {
        dropIds.add(Long.parseLong(document.getId()));
    }
    // Retrieve drops from the DB and transform to DTO
    for (Drop drop : dropDao.findAll(dropIds)) {
        drops.add(mapper.map(drop, GetDropDTO.class));
    }
    return drops;
}


@Transactional(readOnly = false)
public void deleteAllFromIndex(){
    logger.debug("Flushing search index");
    repository.deleteAll();
}


public Map<Long,List<String>> getDropPlaces(List<Drop> drops){
    Map<Long, List<String>> dropPlaces = new HashMap<Long, List<String>>();
    // Fetch the drop ids
    List<Long> dropIds = new ArrayList<Long>();
    for (Drop drop : drops) {
        dropIds.add(drop.getId());
    }
    // Query to fetch the places associated with the drops
    String sql = "SELECT droplets_places.droplet_id, " + "places.longitude, places.latitude " + "FROM places " + "INNER JOIN droplets_places ON (droplets_places.place_id = places.id) " + "WHERE droplets_places.droplet_id IN (:dropIds)";
    MapSqlParameterSource paramMap = new MapSqlParameterSource();
    paramMap.addValue("dropIds", dropIds);
    for (Map<String, Object> row : namedJdbcTemplate.queryForList(sql, paramMap)) {
        Long dropId = ((Number) row.get("droplet_id")).longValue();
        Float longitude = ((Number) row.get("longitude")).floatValue();
        Float latitude = ((Number) row.get("latitude")).floatValue();
        List<String> places = dropPlaces.get(dropId);
        if (places == null) {
            places = new ArrayList<String>();
        }
        places.add(String.format("%s,%s", latitude, longitude));
        dropPlaces.put(dropId, places);
    }
    return dropPlaces;
}


@Autowired
public void setDataSource(DataSource dataSource){
    this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
}


public void setMapper(Mapper mapper){
    this.mapper = mapper;
}


public void populateRiverIds(List<Drop> drops){
    // Store the drop index
    Map<Long, Integer> dropIndex = new HashMap<Long, Integer>();
    List<Long> dropIds = new ArrayList<Long>();
    int index = 0;
    for (Drop drop : drops) {
        dropIds.add(drop.getId());
        dropIndex.put(drop.getId(), index);
        index++;
    }
    String sql = "SELECT `droplet_id`, `river_id` " + "FROM `rivers_droplets` WHERE `droplet_id` IN (:dropIds)";
    MapSqlParameterSource paramMap = new MapSqlParameterSource();
    paramMap.addValue("dropIds", dropIds);
    for (Map<String, Object> row : namedJdbcTemplate.queryForList(sql, paramMap)) {
        Long dropId = ((Number) row.get("droplet_id")).longValue();
        Long riverId = ((Number) row.get("river_id")).longValue();
        Drop drop = drops.get(dropIndex.get(dropId));
        if (drop.getRiverIds() == null) {
            drop.setRiverIds(new ArrayList<Long>());
        }
        drop.getRiverIds().add(riverId);
    }
}


}