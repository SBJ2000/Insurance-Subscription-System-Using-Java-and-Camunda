package Scoring.controller;

import Scoring.model.Scoring;
import Scoring.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/scoring")
public class ScoringController {

    @Autowired
    private ScoringService scoringService;

    @GetMapping
    public List<Scoring> getAllScorings() {
        return scoringService.getAllScorings();
    }

    @GetMapping("/{scoringID}")
    public Scoring getScoringById(@PathVariable Long scoringID) {
        return scoringService.getScoringById(scoringID);
    }

    @PostMapping("/add-scoring/{idDemande}")
    public int addScoring(@PathVariable Long idDemande, Scoring scoring) {
        // Appel à l'API pour récupérer l'ID du client associé à la demande
        Long idClient = getClientIdByDemandeId(idDemande);
        String prenomClient = getprenomClientByidClient(idClient);

        // Vérification si le client est dans la blacklist
        boolean isClientInBlacklist = isClientInBlacklist(prenomClient);

        if (isClientInBlacklist) {
            // Si le client est dans la blacklist, le score est mis à 0
            scoring.setScore(0);
            scoring.setReferenceDemande(idDemande);
            scoring.setEtatscore("Rouge");
        } else {
            // Appel à l'API pour récupérer la ClasseBonusMalus du client
            int classeBonusMalus = getClasseBonusMalusByClientId(idClient);

            // Remplir le score avec la ClasseBonusMalus
            scoring.setScore(classeBonusMalus);
            scoring.setReferenceDemande(idDemande);
            if(classeBonusMalus<=5){
                scoring.setEtatscore("Vert");
            }
            else if (classeBonusMalus<=5){
                scoring.setEtatscore("Rouge");
            }
        }

        // Ajouter le scoring avec le score calculé
         scoringService.addScoring(scoring);
        return scoring.getScore();
    }

    public String getprenomClientByidClient(Long idClient) {
        // Define the URL
        String apiUrl = "http://localhost:8084/Client/prenom/" + idClient;
        RestTemplate restTemplate = new RestTemplate();
        // Make the GET request
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        // Check if the request was successful (HTTP 200 OK)
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Return the response body (prenom)
            return responseEntity.getBody();
        } else {
            // Handle error cases, for example, log an error and return null
            // You might want to throw an exception or handle it based on your use case
            System.err.println("Error retrieving prenom for client with ID: " + idClient);
            return null;
        }
    }

    @GetMapping("/etatscore/{idDemande}")
    public String getEtatScoreByDemandeId(@PathVariable Long idDemande) {
        // Appel à votre service pour obtenir l'état du score en fonction de l'ID de la demande
        String etatScore = scoringService.getEtatScoreByDemandeId(idDemande);

        return etatScore;
    }

    // Méthode pour récupérer l'ID du client associé à la demande
    private Long getClientIdByDemandeId(Long idDemande) {
        // Faire l'appel à http://localhost:8086/demande/client-id/{idDemande}
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8086/demande/client-id/" + idDemande, Long.class);
    }
    // Méthode pour vérifier si le client est dans la blacklist
    private boolean isClientInBlacklist(String prenomClient) {
        // Faire l'appel à http://localhost:8084/client-blacklist/{idClient}
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8084/client-blacklist/exists-by-prenom/" + prenomClient, Boolean.class);
    }
    // Méthode pour récupérer la ClasseBonusMalus du client
    private int getClasseBonusMalusByClientId(Long idClient) {
        // Faire l'appel à http://localhost:8084/Client/classe-bonus-malus/{idClient}
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8084/Client/classe-bonus-malus/" + idClient, Integer.class);
    }
    @PutMapping("/{scoringID}")
    public Scoring updateScoring(@PathVariable Long scoringID, @RequestBody Scoring scoring) {
        return scoringService.updateScoring(scoringID, scoring);
    }

    @DeleteMapping("/{scoringID}")
    public void deleteScoring(@PathVariable Long scoringID) {
        scoringService.deleteScoring(scoringID);
    }
}

