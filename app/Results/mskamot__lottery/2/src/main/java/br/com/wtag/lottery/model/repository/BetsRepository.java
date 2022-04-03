package br.com.wtag.lottery.model.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.wtag.lottery.model.entity.Bets;
public interface BetsRepository extends JpaRepository<Bets, Long>{


public List<Bets> findByEmailOrderByRegisteredAsc(String email)
;

}