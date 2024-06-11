package Client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ClientID;
    private String Nom;
    private String Prenom;
    private String Cin;
    private double RevenuMensuel;
    private String TypeEmploi;
    private Date DateNaissance;
    private int ClasseBonusMalus;
    private String BesoinAssurance;

    public Client() {
    }

    public Client(String nom, String prenom, String cin, double revenuMensuel, String typeEmploi, Date dateNaissance, int classeBonusMalus, String besoinAssurance) {
        Nom = nom;
        Prenom = prenom;
        Cin = cin;
        RevenuMensuel = revenuMensuel;
        TypeEmploi = typeEmploi;
        DateNaissance = dateNaissance;
        ClasseBonusMalus = classeBonusMalus;
        BesoinAssurance = besoinAssurance;
    }

    public Long getClientID() {
        return ClientID;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getCin() {
        return Cin;
    }

    public double getRevenuMensuel() {
        return RevenuMensuel;
    }

    public String getTypeEmploi() {
        return TypeEmploi;
    }

    public Date getDateNaissance() {
        return DateNaissance;
    }

    public int getClasseBonusMalus() {
        return ClasseBonusMalus;
    }

    public String getBesoinAssurance() {
        return BesoinAssurance;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public void setCin(String cin) {
        Cin = cin;
    }

    public void setRevenuMensuel(double revenuMensuel) {
        RevenuMensuel = revenuMensuel;
    }

    public void setTypeEmploi(String typeEmploi) {
        TypeEmploi = typeEmploi;
    }

    public void setDateNaissance(Date dateNaissance) {
        DateNaissance = dateNaissance;
    }

    public void setClasseBonusMalus(int classeBonusMalus) {
        ClasseBonusMalus = classeBonusMalus;
    }

    public void setBesoinAssurance(String besoinAssurance) {
        BesoinAssurance = besoinAssurance;
    }
}
