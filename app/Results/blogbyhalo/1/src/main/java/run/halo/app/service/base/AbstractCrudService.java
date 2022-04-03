package run.halo.app.service.base;
 import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import run.halo.app.exception.NotFoundException;
import run.halo.app.repository.base.BaseRepository;
@Slf4j
public class AbstractCrudService implements CrudService<DOMAIN, ID>{

 private  String domainName;

 private  BaseRepository<DOMAIN,ID> repository;

protected AbstractCrudService(BaseRepository<DOMAIN, ID> repository) {
    this.repository = repository;
    // Get domain name
    @SuppressWarnings("unchecked")
    Class<DOMAIN> domainClass = (Class<DOMAIN>) fetchType(0);
    domainName = domainClass.getSimpleName();
}
@Override
public List<DOMAIN> createInBatch(Collection<DOMAIN> domains){
    return CollectionUtils.isEmpty(domains) ? Collections.emptyList() : repository.saveAll(domains);
}


@Override
public List<DOMAIN> updateInBatch(Collection<DOMAIN> domains){
    return CollectionUtils.isEmpty(domains) ? Collections.emptyList() : repository.saveAll(domains);
}


@Override
public DOMAIN removeById(ID id){
    // Get non null domain by id
    DOMAIN domain = getById(id);
    // Remove it
    remove(domain);
    // return the deleted domain
    return domain;
}


@Override
public void removeInBatch(Collection<ID> ids){
    if (CollectionUtils.isEmpty(ids)) {
        log.debug(domainName + " id collection is empty");
        return;
    }
    repository.deleteByIdIn(ids);
}


@Override
public long count(){
    return repository.count();
}


@Override
public DOMAIN update(DOMAIN domain){
    Assert.notNull(domain, domainName + " data must not be null");
    return repository.saveAndFlush(domain);
}


@Override
public void mustExistById(ID id){
    if (!existsById(id)) {
        throw new NotFoundException(domainName + " was not exist");
    }
}


public Type fetchType(int index){
    Assert.isTrue(index >= 0 && index <= 1, "type index must be between 0 to 1");
    return ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[index];
}


@Override
public List<DOMAIN> listAllByIds(Collection<ID> ids,Sort sort){
    Assert.notNull(sort, "Sort info must not be null");
    return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : repository.findAllByIdIn(ids, sort);
}


@Override
public DOMAIN removeByIdOfNullable(ID id){
    return fetchById(id).map(domain -> {
        remove(domain);
        return domain;
    }).orElse(null);
}


@Override
public void remove(DOMAIN domain){
    Assert.notNull(domain, domainName + " data must not be null");
    repository.delete(domain);
}


@Override
public boolean existsById(ID id){
    Assert.notNull(id, domainName + " id must not be null");
    return repository.existsById(id);
}


@Override
public void removeAll(){
    repository.deleteAll();
}


@Override
public void flush(){
    repository.flush();
}


@Override
public DOMAIN getById(ID id){
    return fetchById(id).orElseThrow(() -> new NotFoundException(domainName + " was not found or has been deleted"));
}


@Override
public DOMAIN getByIdOfNullable(ID id){
    return fetchById(id).orElse(null);
}


@Override
public DOMAIN create(DOMAIN domain){
    Assert.notNull(domain, domainName + " data must not be null");
    return repository.save(domain);
}


@Override
public Optional<DOMAIN> fetchById(ID id){
    Assert.notNull(id, domainName + " id must not be null");
    return repository.findById(id);
}


@Override
public Page<DOMAIN> listAll(Pageable pageable){
    Assert.notNull(pageable, "Pageable info must not be null");
    return repository.findAll(pageable);
}


}