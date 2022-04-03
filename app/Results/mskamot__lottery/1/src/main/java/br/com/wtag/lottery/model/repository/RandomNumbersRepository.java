package br.com.wtag.lottery.model.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import br.com.wtag.lottery.model.entity.RandomNumbers;
public interface RandomNumbersRepository extends JpaRepository<RandomNumbers, Long>{


}