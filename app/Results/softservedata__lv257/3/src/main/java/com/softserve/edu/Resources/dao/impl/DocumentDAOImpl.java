package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.entity.Document;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public class DocumentDAOImpl extends GenericDAOImpl<Document, Long>{

public DocumentDAOImpl() {
    super(Document.class);
}
@Override
public Optional<Document> findById(Long aLong){
    return super.findById(aLong);
}


@Override
public Document makePersistent(Document instance){
    return super.makePersistent(instance);
}


}