package Decision.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Decision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDecision;
    private Long referenceDemande;
    private Date date;
    private String statutDecision;

    public Decision() {
    }

    public Decision(Long idDecision, Long referenceDemande, Date date, String statutDecision) {
        this.idDecision = idDecision;
        this.referenceDemande = referenceDemande;
        this.date = date;
        this.statutDecision = statutDecision;
    }

    public Long getIdDecision() {
        return idDecision;
    }

    public void setIdDecision(Long idDecision) {
        this.idDecision = idDecision;
    }

    public Long getReferenceDemande() {
        return referenceDemande;
    }

    public void setReferenceDemande(Long referenceDemande) {
        this.referenceDemande = referenceDemande;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatutDecision() {
        return statutDecision;
    }

    public void setStatutDecision(String statutDecision) {
        this.statutDecision = statutDecision;
    }
}
