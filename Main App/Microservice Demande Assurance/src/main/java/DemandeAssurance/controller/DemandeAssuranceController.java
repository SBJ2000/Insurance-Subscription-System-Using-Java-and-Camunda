package DemandeAssurance.controller;

import DemandeAssurance.model.DemandeAssurance;
import DemandeAssurance.service.DemandeAssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/demande")
public class DemandeAssuranceController {
    private final String OFFRE_API_URL = "http://localhost:8085/offres/";
    private final String CLIENT_API_URL = "http://localhost:8084/Client/";

    @Autowired
    private DemandeAssuranceService demandeAssuranceService;

    @GetMapping
    public List<DemandeAssurance> getAllDemandes() {
        return demandeAssuranceService.getAllDemandes();
    }

    @GetMapping("/{id}")
    public DemandeAssurance getDemandeById(@PathVariable Long id) {
        return demandeAssuranceService.getDemandeById(id);
    }
    @GetMapping("/client-id/{id}")
    public Long getClientIdByDemandeId(@PathVariable Long id) {
        return demandeAssuranceService.getClientIdByDemandeId(id);
    }
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping("/{idClient}")
    public Long addDemande(@PathVariable Long idClient,
                                        DemandeAssurance demandeAssurance) {
        // Appel à l'API pour récupérer le besoin d'assurance du client
        String besoinAssuranceUrl = "http://localhost:8084/Client/" +"/besoin-assurance/" + idClient  ;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(besoinAssuranceUrl, String.class);
        // Récupérer le besoin d'assurance depuis la réponse
        String typeAssurance = responseEntity.getBody();
        demandeAssurance.setIdClient(idClient);
        // Appel à l'API pour récupérer le revenu mensuel du client
        double revenuMensuel = getRevenuMensuel(idClient);

        // Appel à l'API pour trouver l'offre en fonction du revenu mensuel et du type d'assurance
        Long idOffre = findOffreByAmountAndType(revenuMensuel, typeAssurance);

        // Mettre l'id de l'offre trouvée dans l'attribut offreProposee
        demandeAssurance.setIdoffreProposee(idOffre);
        demandeAssurance.setTypeAssurance(typeAssurance);
        // Définir la date de la demande comme la date actuelle
        demandeAssurance.setDateDemande(getCurrentDate());
        return demandeAssuranceService.addDemande(demandeAssurance);

    }
    private Date getCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    private Long findOffreByAmountAndType(double revenuMensuel, String typeAssurance) {
        // Faire l'appel à http://localhost:8085/offres/find-offre-by-amount-and-type/{revenuMensuel}/{typeAssurance}
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(OFFRE_API_URL + "find-offre-by-amount-and-type/" + revenuMensuel + "/" + typeAssurance, Long.class);
    }
    private double getRevenuMensuel(Long idClient) {
        // Faire l'appel à http://localhost:8084/Client/{idClient}/revenu-mensuel
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(CLIENT_API_URL + idClient + "/revenu-mensuel", Double.class);
    }

    @PutMapping("/{id}")
    public DemandeAssurance updateDemande(@PathVariable Long id, @RequestBody DemandeAssurance demandeAssurance) {
        return demandeAssuranceService.updateDemande(id, demandeAssurance);
    }

    @DeleteMapping("/{id}")
    public void deleteDemande(@PathVariable Long id) {
        demandeAssuranceService.deleteDemande(id);
    }
}
