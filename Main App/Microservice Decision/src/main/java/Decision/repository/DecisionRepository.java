package Decision.repository;

import Decision.model.Decision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionRepository extends JpaRepository<Decision, Long> {

}
