package com.ushahidi.swiftriver.core.solr.repository;
 import java.util.List;
import org.springframework.data.domain.Pageable;
import com.ushahidi.swiftriver.core.api.filter.DropFilter;
import com.ushahidi.swiftriver.core.model.Bucket;
import com.ushahidi.swiftriver.core.model.River;
import com.ushahidi.swiftriver.core.solr.DropDocument;
public interface DropSearchRepository {


public List<DropDocument> findInRiver(Long riverId,DropFilter dropFilter,Pageable pageable)
;

public List<DropDocument> find(String searchTerm,Pageable pageable)
;

public List<DropDocument> findInBucket(Long bucketId,DropFilter dropFilter,Pageable pageable)
;

}