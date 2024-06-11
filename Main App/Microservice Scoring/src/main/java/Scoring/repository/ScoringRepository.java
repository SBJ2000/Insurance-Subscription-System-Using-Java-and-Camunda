package Scoring.repository;

import Scoring.model.Scoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoringRepository extends JpaRepository<Scoring, Long> {
    Scoring findByReferenceDemande(Long referenceDemande);

}
