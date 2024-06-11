package OffreAssurance.controller;

import OffreAssurance.model.OffreAssurance;
import OffreAssurance.service.OffreAssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offres")
public class OffreAssuranceController {

    @Autowired
    private OffreAssuranceService offreAssuranceService;

    @GetMapping
    public List<OffreAssurance> getAllOffres() {
        return offreAssuranceService.getAllOffres();
    }

    @GetMapping("/{id}")
    public OffreAssurance getOffreById(@PathVariable Long id) {
        return offreAssuranceService.getOffreById(id);
    }
    @PostMapping("/add-offre/{referenceProduit}/{typeAssurance}/{plafondGarantie}/{primeAssurance}/{conditionsParticulieres}")
    public OffreAssurance addOffre(@PathVariable String referenceProduit,
                                   @PathVariable String typeAssurance,
                                   @PathVariable double plafondGarantie,
                                   @PathVariable double primeAssurance,
                                   @PathVariable String conditionsParticulieres) {
        OffreAssurance offreAssurance = new OffreAssurance(referenceProduit, typeAssurance, plafondGarantie, primeAssurance, conditionsParticulieres);
        return offreAssuranceService.addOffre(offreAssurance);
    }


    @PutMapping("/{id}")
    public OffreAssurance updateOffre(@PathVariable Long id, @RequestBody OffreAssurance offreAssurance) {
        return offreAssuranceService.updateOffre(id, offreAssurance);
    }

    @DeleteMapping("/{id}")
    public void deleteOffre(@PathVariable Long id) {
        offreAssuranceService.deleteOffre(id);
    }
    @GetMapping("/find-offre-by-amount-and-type/{amount}/{typeAssurance}")
    public Long findOffreByAmountAndType(@PathVariable double amount, @PathVariable String typeAssurance) {
        return offreAssuranceService.findOffreIdByAmountAndType(amount, typeAssurance);
    }
}
