package es.us.isa.ideas.app.services;
 import java.util.Collection;
import javax.inject.Inject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.us.isa.ideas.app.entities.Tag;
import es.us.isa.ideas.app.repositories.TagRepository;
@Service
@Transactional
public class TagService extends BusinessService<Tag>{

@Inject
 private  TagRepository tagRepository;


public Collection<Tag> findByWorkspaceName(String workspaceName,String username){
    return tagRepository.findByWorkspaceName(workspaceName, username);
}


public Collection<Tag> findTagsInUse(){
    return tagRepository.findTagsInUse();
}


@Override
public JpaRepository<Tag,Integer> getRepository(){
    return tagRepository;
}


public Tag findByName(String tagName){
    return tagRepository.findByName(tagName);
}


public Collection<Tag> findAll(){
    return tagRepository.findAll();
}


}