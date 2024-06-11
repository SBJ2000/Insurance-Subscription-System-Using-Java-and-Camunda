package Scoring.service;

import Scoring.model.Scoring;
import Scoring.repository.ScoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoringService {

    @Autowired
    private ScoringRepository scoringRepository;

    public List<Scoring> getAllScorings() {
        return scoringRepository.findAll();
    }

    public Scoring getScoringById(Long scoringID) {
        Optional<Scoring> optionalScoring = scoringRepository.findById(scoringID);
        return optionalScoring.orElse(null);
    }

    public Scoring addScoring(Scoring scoring) {
        return scoringRepository.save(scoring);
    }

    public Scoring updateScoring(Long scoringID, Scoring updatedScoring) {
        Optional<Scoring> optionalScoring = scoringRepository.findById(scoringID);

        if (optionalScoring.isPresent()) {
            Scoring existingScoring = optionalScoring.get();
            existingScoring.setReferenceDemande(updatedScoring.getReferenceDemande());
            existingScoring.setScore(updatedScoring.getScore());
            return scoringRepository.save(existingScoring);
        } else {
            return null;
        }
    }
    // ScoringService.java
    public String getEtatScoreByDemandeId(Long idDemande) {
        Scoring scoring = scoringRepository.findByReferenceDemande(idDemande);
        return (scoring != null) ? scoring.getEtatscore() : "Demande non trouvée";
    }

    public void deleteScoring(Long scoringID) {
        scoringRepository.deleteById(scoringID);
    }
}
