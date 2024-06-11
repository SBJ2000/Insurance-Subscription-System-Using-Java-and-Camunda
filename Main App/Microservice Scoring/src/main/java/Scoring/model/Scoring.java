package Scoring.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Scoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoringID;
    private Long referenceDemande;
    private int score;
    private String etatscore;

    public String getEtatscore() {
        return etatscore;
    }

    public void setEtatscore(String etatscore) {
        this.etatscore = etatscore;
    }

    public Scoring() {
    }

    public Scoring(Long scoringID, Long referenceDemande, int score) {
        this.scoringID = scoringID;
        this.referenceDemande = referenceDemande;
        this.score = score;
    }

    public Long getScoringID() {
        return scoringID;
    }

    public void setScoringID(Long scoringID) {
        this.scoringID = scoringID;
    }

    public Long getReferenceDemande() {
        return referenceDemande;
    }

    public void setReferenceDemande(Long referenceDemande) {
        this.referenceDemande = referenceDemande;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
