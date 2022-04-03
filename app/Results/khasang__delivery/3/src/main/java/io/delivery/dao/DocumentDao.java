package io.delivery.dao;
 import io.delivery.entity.Document;
import java.util.List;
public interface DocumentDao extends BasicDao<Document>{


public List<Document> findByName(String name)
;

}