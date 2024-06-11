package OffreAssurance.service;

import OffreAssurance.model.OffreAssurance;
import OffreAssurance.repository.OffreAssuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreAssuranceService {

    @Autowired
    private OffreAssuranceRepository offreAssuranceRepository;

    public List<OffreAssurance> getAllOffres() {
        return offreAssuranceRepository.findAll();
    }

    public OffreAssurance getOffreById(Long id) {
        return offreAssuranceRepository.findById(id).orElse(null);
    }

    public OffreAssurance addOffre(OffreAssurance offreAssurance) {
        return offreAssuranceRepository.save(offreAssurance);
    }

    public OffreAssurance updateOffre(Long id, OffreAssurance offreAssurance) {
        if (offreAssuranceRepository.existsById(id)) {
            offreAssurance.setOffreID(id);
            return offreAssuranceRepository.save(offreAssurance);
        }
        return null;
    }

    public void deleteOffre(Long id) {
        offreAssuranceRepository.deleteById(id);
    }
    public Long findOffreIdByAmountAndType(double amount, String typeAssurance) {
        List<OffreAssurance> offres = getAllOffres();

        for (OffreAssurance offre : offres) {
            if (offre.getPrimeAssurance() <= 0.2 * amount && offre.getTypeAssurance().equals(typeAssurance)) {
                return offre.getOffreID();
            }
        }

        // Retourner null ou une valeur spécifique si aucune offre ne satisfait le critère
        return null;
    }
}
