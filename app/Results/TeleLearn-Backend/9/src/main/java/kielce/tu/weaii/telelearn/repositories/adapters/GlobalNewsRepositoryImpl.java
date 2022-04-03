package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.models.GlobalNews;
import kielce.tu.weaii.telelearn.repositories.jpa.GlobalNewsJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.GlobalNewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class GlobalNewsRepositoryImpl implements GlobalNewsRepository{

 private  GlobalNewsJPARepository jpaRepository;


@Override
public Optional<GlobalNews> getById(Long id){
    return jpaRepository.findById(id);
}


@Override
public Page<GlobalNews> getPage(int pageSize,int pageNo){
    return jpaRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by("publicationDate").descending()));
}


@Override
public GlobalNews save(GlobalNews entity){
    return jpaRepository.save(entity);
}


@Override
public void delete(GlobalNews entity){
    jpaRepository.delete(entity);
}


}