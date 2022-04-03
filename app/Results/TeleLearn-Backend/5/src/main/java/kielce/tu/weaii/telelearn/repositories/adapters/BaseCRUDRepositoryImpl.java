package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.repositories.ports.BaseCRUDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class BaseCRUDRepositoryImpl implements BaseCRUDRepository<E>{

 private  JpaRepository<E,Long> repository;


@Override
public List<E> getAll(){
    return repository.findAll();
}


@Override
public Optional<E> getById(Long id){
    return repository.findById(id);
}


@Override
public E save(E entity){
    return repository.saveAndFlush(entity);
}


@Override
public void delete(E entity){
    repository.delete(entity);
}


}