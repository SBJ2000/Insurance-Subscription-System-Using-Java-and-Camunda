package DemandeAssurance.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DemandeAssurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demandeID;

    private Date dateDemande;

    private Long idClient;

    private String typeAssurance;

    private Long idoffreProposee;

    public DemandeAssurance() {
    }

    public DemandeAssurance(Long demandeID, Date dateDemande, Long idClient, String typeAssurance, Long idoffreProposee) {
        this.demandeID = demandeID;
        this.dateDemande = dateDemande;
        this.idClient = idClient;
        this.typeAssurance = typeAssurance;
        this.idoffreProposee = idoffreProposee;
    }

    public Long getDemandeID() {
        return demandeID;
    }

    public void setDemandeID(Long demandeID) {
        this.demandeID = demandeID;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getTypeAssurance() {
        return typeAssurance;
    }

    public void setTypeAssurance(String typeAssurance) {
        this.typeAssurance = typeAssurance;
    }

    public Long getIdoffreProposee() {
        return idoffreProposee;
    }

    public void setIdoffreProposee(Long idoffreProposee) {
        this.idoffreProposee = idoffreProposee;
    }
}
