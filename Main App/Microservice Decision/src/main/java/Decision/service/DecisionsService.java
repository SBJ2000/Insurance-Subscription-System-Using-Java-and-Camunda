package Decision.service;

import Decision.model.Decision;
import Decision.repository.DecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DecisionsService {
    @Autowired
    private DecisionRepository decisionRepository;

    public List<Decision> getAllDecisions() {
        return decisionRepository.findAll();
    }

    public Decision getDecisionById(Long idDecision) {
        Optional<Decision> optionalDecision = decisionRepository.findById(idDecision);
        return optionalDecision.orElse(null);
    }

    public Decision addDecision(Decision decision) {
        return decisionRepository.save(decision);
    }

    public Decision updateDecision(Long idDecision, Decision decision) {
        if (decisionRepository.existsById(idDecision)) {
            decision.setIdDecision(idDecision);
            return decisionRepository.save(decision);
        }
        return null;
    }

    public void deleteDecision(Long idDecision) {
        decisionRepository.deleteById(idDecision);
    }
}
