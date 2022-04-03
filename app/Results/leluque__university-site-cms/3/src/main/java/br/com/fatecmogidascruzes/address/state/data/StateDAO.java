package br.com.fatecmogidascruzes.address.state.data;
 import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fatecmogidascruzes.address.state.State;
import br.com.fatecmogidascruzes.data.DAOImpl;
public interface StateDAO extends JpaRepository<State, Long>, DAOImpl<State, Long>{


public Optional<State> findByEnabledTrueAndAcronym(String acronym)
;

}