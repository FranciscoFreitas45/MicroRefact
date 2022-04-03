package kielce.tu.weaii.telelearn.repositories.jpa;
 import kielce.tu.weaii.telelearn.models.GlobalNews;
import org.springframework.data.repository.PagingAndSortingRepository;
public interface GlobalNewsJPARepository extends PagingAndSortingRepository<GlobalNews, Long>{


}