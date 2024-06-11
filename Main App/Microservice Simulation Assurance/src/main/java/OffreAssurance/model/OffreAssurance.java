package OffreAssurance.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class OffreAssurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offreID;
    private String referenceProduit;
    private String typeAssurance;
    private double plafondGarantie;
    private double primeAssurance;
    private String conditionsParticulieres;

    public OffreAssurance() {
    }

    public void setOffreID(Long offreID) {
        this.offreID = offreID;
    }

    public OffreAssurance(String referenceProduit, String typeAssurance, double plafondGarantie, double primeAssurance, String conditionsParticulieres) {
        this.referenceProduit = referenceProduit;
        this.typeAssurance = typeAssurance;
        this.plafondGarantie = plafondGarantie;
        this.primeAssurance = primeAssurance;
        this.conditionsParticulieres = conditionsParticulieres;
    }

    public Long getOffreID() {
        return offreID;
    }

    public String getReferenceProduit() {
        return referenceProduit;
    }

    public String getTypeAssurance() {
        return typeAssurance;
    }

    public double getPlafondGarantie() {
        return plafondGarantie;
    }

    public double getPrimeAssurance() {
        return primeAssurance;
    }

    public String getConditionsParticulieres() {
        return conditionsParticulieres;
    }

    public void setReferenceProduit(String referenceProduit) {
        this.referenceProduit = referenceProduit;
    }

    public void setTypeAssurance(String typeAssurance) {
        this.typeAssurance = typeAssurance;
    }

    public void setPlafondGarantie(double plafondGarantie) {
        this.plafondGarantie = plafondGarantie;
    }

    public void setPrimeAssurance(double primeAssurance) {
        this.primeAssurance = primeAssurance;
    }

    public void setConditionsParticulieres(String conditionsParticulieres) {
        this.conditionsParticulieres = conditionsParticulieres;
    }
}
