package Decision.controller;

import Decision.model.Decision;
import Decision.service.DecisionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/decision")
public class DecisionController {

    @Autowired
    private DecisionsService decisionsService;

    @GetMapping
    public List<Decision> getAllDecisions() {
        return decisionsService.getAllDecisions();
    }

    @GetMapping("/{idDecision}")
    public Decision getDecisionById(@PathVariable Long idDecision) {
        return decisionsService.getDecisionById(idDecision);
    }

    @PostMapping("/{idDemande}")
    public String addDecision(@PathVariable Long idDemande, Decision decision) {
        // Appel à l'API pour récupérer l'état du score en fonction de l'id de la demande
        String etatScore = getEtatScoreByDemandeId(idDemande);

        decision.setReferenceDemande(idDemande);
        decision.setDate(new Date());

        // Remplir l'attribut statutDecision en fonction de l'état du score
        if ("Vert".equals(etatScore)) {
            decision.setStatutDecision("Acceptée");
        } else if ("Rouge".equals(etatScore)) {
            decision.setStatutDecision("Refusée");
        }

        // Ajouter la décision avec les informations remplies
         decisionsService.addDecision(decision);
        return decision.getStatutDecision();
    }
    // Méthode pour récupérer l'état du score en fonction de l'id de la demande
    private String getEtatScoreByDemandeId(Long idDemande) {
        // Faire l'appel à http://localhost:8087/scoring/etatscore/{idDemande}
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8087/scoring/etatscore/" + idDemande, String.class);
    }
    @PutMapping("/{idDecision}")
    public Decision updateDecision(@PathVariable Long idDecision, @RequestBody Decision decision) {
        return decisionsService.updateDecision(idDecision, decision);
    }

    @DeleteMapping("/{idDecision}")
    public void deleteDecision(@PathVariable Long idDecision) {
        decisionsService.deleteDecision(idDecision);
    }
}
