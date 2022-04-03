package com.ushahidi.swiftriver.core.solr.repository;
 import java.util.List;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import com.ushahidi.swiftriver.core.solr.DropDocument;
public interface DropDocumentRepository extends DropSearchRepository, SolrCrudRepository<DropDocument, String>{


@Query(value = "id:(?0)")
public List<DropDocument> findAll(List<String> ids)
;

}