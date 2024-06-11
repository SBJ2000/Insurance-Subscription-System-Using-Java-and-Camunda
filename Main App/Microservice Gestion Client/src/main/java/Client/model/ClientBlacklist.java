package Client.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ClientBlacklist {
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

    public ClientBlacklist() {
    }

    public ClientBlacklist(Long clientID, String nom, String prenom, String cin, double revenuMensuel, String typeEmploi, Date dateNaissance, int classeBonusMalus, String besoinAssurance) {
        ClientID = clientID;
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

    public void setClientID(Long clientID) {
        ClientID = clientID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getCin() {
        return Cin;
    }

    public void setCin(String cin) {
        Cin = cin;
    }

    public double getRevenuMensuel() {
        return RevenuMensuel;
    }

    public void setRevenuMensuel(double revenuMensuel) {
        RevenuMensuel = revenuMensuel;
    }

    public String getTypeEmploi() {
        return TypeEmploi;
    }

    public void setTypeEmploi(String typeEmploi) {
        TypeEmploi = typeEmploi;
    }

    public Date getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        DateNaissance = dateNaissance;
    }

    public int getClasseBonusMalus() {
        return ClasseBonusMalus;
    }

    public void setClasseBonusMalus(int classeBonusMalus) {
        ClasseBonusMalus = classeBonusMalus;
    }

    public String getBesoinAssurance() {
        return BesoinAssurance;
    }

    public void setBesoinAssurance(String besoinAssurance) {
        BesoinAssurance = besoinAssurance;
    }
}
